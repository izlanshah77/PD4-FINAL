package com.example.pd4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView tvTotal;
    Button btnPlace, btnPast;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.activity_cart, container, false);

        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");

        recyclerView = view.findViewById(R.id.cartRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        tvTotal = view.findViewById(R.id.totalPrice);
        btnPlace = view.findViewById(R.id.confirmPlaceOrder);
        btnPast = view.findViewById(R.id.pastOrders);
        
        cart = new Database(view.getContext()).getCarts();
        adapter = new CartAdapter(cart, view.getContext());
        recyclerView.setAdapter(adapter);

        double total = 0;
        for(Order order:cart)
            total += (Double.parseDouble(order.getPrice()));
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        tvTotal.setText(fmt.format(total));

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(view);
            }
        });

        btnPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), PastOrders.class);
                startActivity(intent);
            }
        });



        return view;
    }
    private void showAlertDialog(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Details");
        alertDialog.setMessage("Fill Up The Form");

        /*final EditText edtAddress = new EditText(getActivity());
        edtAddress.setHint("Address");
        final EditText edtPhone = new EditText(getActivity());
        edtPhone.setHint("Phone Number");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        edtPhone.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setView(edtPhone);
        */
        Context context = v.getContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText edtAddress = new EditText(context);
        edtAddress.setHint("Address");
        layout.addView(edtAddress);
        final EditText edtPhone = new EditText(context);
        edtPhone.setHint("Phone Number");
        layout.addView(edtPhone);

        alertDialog.setView(layout);

        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request newRequest = new Request(
                        Common.currentUser.getName(),edtAddress.getText().toString(), tvTotal.getText().toString(), edtPhone.getText().toString(),cart
                );
                request.child(String.valueOf(System.currentTimeMillis())).setValue(newRequest);
                //Delete cart
                new Database(getActivity().getBaseContext()).cleanCart();
                Toast.makeText(getActivity(), "Thank you, Your Order Has Been Placed", Toast.LENGTH_LONG).show();
                recyclerView.setVisibility(View.INVISIBLE);
                tvTotal.setText("0.00");

            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }


}
