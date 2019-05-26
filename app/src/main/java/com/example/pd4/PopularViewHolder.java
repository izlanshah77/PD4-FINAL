package com.example.pd4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView tvTitle, tvPrice;
    ImageView iv;

    private ItemClickListener itemClickListener;

    public PopularViewHolder( View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.product_name);
        iv = itemView.findViewById(R.id.product_image);
        tvPrice = itemView.findViewById(R.id.product_price);

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
