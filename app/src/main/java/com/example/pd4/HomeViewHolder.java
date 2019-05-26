package com.example.pd4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvTitle;
    ImageView iv;

    private ItemClickListener itemClickListener;

    public HomeViewHolder( View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.home_item_title);
        iv = itemView.findViewById(R.id.home_item_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
