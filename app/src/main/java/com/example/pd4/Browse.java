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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Browse extends AppCompatActivity {

    RecyclerView recyclerView;
    private  BrowseAdapter adapter;
    private  RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        final ArrayList<Genres> genres = new ArrayList<>();
        genres.add(new Genres("Rock", R.drawable.rock));
        genres.add(new Genres("Pop", R.drawable.pop));
        genres.add(new Genres("R&B", R.drawable.rnb));
        genres.add(new Genres("Grunge", R.drawable.grunge));
        genres.add(new Genres("Indie Rock", R.drawable.indierock));
        genres.add(new Genres("Hip-Hop", R.drawable.hiphop));
        genres.add(new Genres("Grime", R.drawable.grime));
        genres.add(new Genres("Experimental", R.drawable.experimental));
        genres.add(new Genres("Electronic", R.drawable.electronic));
        genres.add(new Genres("Punk Rock", R.drawable.punkrock));
        genres.add(new Genres("Jazz", R.drawable.jazz));
        genres.add(new Genres("Country/Folk", R.drawable.country));
        genres.add(new Genres("Classical", R.drawable.classical));
        genres.add(new Genres("Musical Theatre", R.drawable.musical));
        genres.add(new Genres("Disco", R.drawable.disco));

        recyclerView = findViewById(R.id.recycler_browse);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BrowseAdapter(genres);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BrowseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(Browse.this, Popular.class);
                startActivity(i);
            }
        });

    }
}
