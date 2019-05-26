package com.example.pd4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Home extends Fragment {

    FirebaseDatabase database;
    DatabaseReference category;

    RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Category, HomeViewHolder> homeAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.activity_home,container,false);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        recyclerView = view.findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        Query query = FirebaseDatabase.getInstance().getReference().child("Category");

        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(query, new SnapshotParser<Category>() {
            @NonNull
            @Override
            public Category parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new Category(snapshot.child("Name").getValue().toString(),snapshot.child("Image").getValue().toString());
            }
        }).build();




        homeAdapter= new FirebaseRecyclerAdapter<Category, HomeViewHolder>(options) {


            @NonNull
            @Override
            public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item,viewGroup,false);
                return new HomeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull HomeViewHolder holder, int position, @NonNull Category model) {
                holder.tvTitle.setText(model.getName());
                Picasso.with(getActivity().getBaseContext()).load(model.getImage()).into(holder.iv);
                final Category clickItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        if(position == 0){
                            Intent intent = new Intent(getActivity().getBaseContext(), Popular.class);
                            intent.putExtra("type", 0);
                            startActivity(intent);
                        }else if(position == 1){
                            Intent intent = new Intent(getActivity().getBaseContext(), Popular.class);
                            intent.putExtra("type", 0);
                            startActivity(intent);
                        }else{
                            Intent i = new Intent(getActivity().getBaseContext(), Browse.class);
                            startActivity(i);
                        }

                    }
                });
            }


        };
        recyclerView.setAdapter(homeAdapter);




        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        homeAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        homeAdapter.stopListening();
    }




}
