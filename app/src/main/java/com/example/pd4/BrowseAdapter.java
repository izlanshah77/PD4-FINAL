package com.example.pd4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.BrowseViewHolder> {

    private ArrayList<Genres> genreList;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class BrowseViewHolder extends  RecyclerView.ViewHolder{

        public ImageView ivBrowse;
        public TextView tvBrowse;


        public BrowseViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivBrowse = itemView.findViewById(R.id.ivBrowse);
            tvBrowse = itemView.findViewById(R.id.tvBrowse);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }



    public BrowseAdapter(ArrayList<Genres> genres) {
        genreList = genres;
    }

    @NonNull
    @Override
    public BrowseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse_item, viewGroup, false);
        BrowseViewHolder bvh = new BrowseViewHolder(view, listener);
        return bvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseViewHolder browseViewHolder, int i) {
        Genres current = genreList.get(i);

        browseViewHolder.ivBrowse.setImageResource(current.getImage());
        browseViewHolder.tvBrowse.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }
}
