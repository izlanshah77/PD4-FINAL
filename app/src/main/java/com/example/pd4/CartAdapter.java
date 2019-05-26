package com.example.pd4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tvCartName, tvCartPrice;


    private  ItemClickListener itemClickListener;

    public void setTvCartName(TextView tvCartName) {
        this.tvCartName = tvCartName;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCartName = itemView.findViewById(R.id.cartItemName);
        tvCartPrice = itemView.findViewById(R.id.cartItemPrice);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends  RecyclerView.Adapter<CartViewHolder> {

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, viewGroup, false);
        return  new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        double price = (Double.parseDouble(listData.get(i).getPrice()));
        cartViewHolder.tvCartPrice.setText(fmt.format(price));
        cartViewHolder.tvCartName.setText(listData.get(i).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
