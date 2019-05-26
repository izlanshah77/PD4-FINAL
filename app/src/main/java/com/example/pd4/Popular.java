package com.example.pd4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Popular extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;

    RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<PopularItem, PopularViewHolder> popularAdapter;
    TextView toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("PopularItem");
        toolbar = findViewById(R.id.tvToolbar);


        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



            toolbar.setText("POPULAR THIS WEEK");
            Query query = FirebaseDatabase.getInstance().getReference().child("PopularItem");


            FirebaseRecyclerOptions<PopularItem> options = new FirebaseRecyclerOptions.Builder<PopularItem>().setQuery(query, new SnapshotParser<PopularItem>() {
                @NonNull
                @Override
                public PopularItem parseSnapshot(@NonNull DataSnapshot snapshot) {
                    return new PopularItem(snapshot.child("Name").getValue().toString(),snapshot.child("Artist").getValue().toString(), snapshot.child("Image").getValue().toString(), snapshot.child("Price").getValue().toString(), snapshot.child("Track_Listing").getValue().toString());
                }
            }).build();

            popularAdapter = new FirebaseRecyclerAdapter<PopularItem, PopularViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull PopularViewHolder holder, int position, @NonNull final PopularItem model) {
                    holder.tvTitle.setText(model.getName());
                    holder.tvPrice.setText(model.getPrice());
                    Picasso.with(getBaseContext()).load(model.getImage()).into(holder.iv);

                    final PopularItem clickItem = model;
                    holder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(Popular.this, ""+model.getName(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Popular.this, item_detail.class);
                            i.putExtra("ItemID", popularAdapter.getRef(position).getKey());
                            startActivity(i);
                        }
                    });
                }

                @NonNull
                @Override
                public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item,viewGroup,false);
                    return new PopularViewHolder(view);
                }
            };
            recyclerView.setAdapter(popularAdapter);




    }
    @Override
    public void onStart(){
        super.onStart();
        popularAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        popularAdapter.stopListening();
    }
}
