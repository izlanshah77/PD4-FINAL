package com.example.pd4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tvOrderId, tvOrderNumber, tvOrderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        tvOrderId = itemView.findViewById(R.id.order_id);
        tvOrderAddress = itemView.findViewById(R.id.order_address);
        tvOrderNumber = itemView.findViewById(R.id.order_phone);

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
