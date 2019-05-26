package com.example.pd4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class item_detail extends AppCompatActivity {

    TextView tvTitle;
    TextView tvPrice;
    TextView tvDesc;
    ImageView ivItem;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    String itemID = "";
    FirebaseDatabase database;
    DatabaseReference category;
    PopularItem currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("PopularItem");

        tvTitle = findViewById(R.id.itemName);
        tvPrice = findViewById(R.id.itemPrice);
        tvDesc = findViewById(R.id.item_desc);
        ivItem = findViewById(R.id.imgItem);
        btnCart = findViewById(R.id.btnCart);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        itemID,
                        currentItem.getName(),
                        currentItem.getPrice()
                ));
                Toast.makeText(item_detail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


        if(getIntent() != null){
            itemID = getIntent().getStringExtra("ItemID");
            if (itemID != null){
                getDetailItem(itemID);
            }
        }


    }
    private void getDetailItem(String itemId) {
        category.child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentItem = dataSnapshot.getValue(PopularItem.class);

                //Set Image
                Picasso.with(getBaseContext()).load(currentItem.getImage()).into(ivItem);
                tvPrice.setText(currentItem.getPrice());
                tvTitle.setText(currentItem.getName());
                tvDesc.setText("Artist - " + currentItem.getArtist() + "\n" + currentItem.getTrack_Listing());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
