package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Done_Order_page extends AppCompatActivity {

    private TextView order_id_tv;
    private TextView Total_price_tv_top;
    private TextView product_name_tv;
    private TextView product_qty_tv;
    private TextView product_price_tv;
    private TextView email_tv;
    private TextView mobile_tv;
    private TextView address_tv;
    private ImageView imageView;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private RecyclerView recyclerView;
    private ArrayList<CartModel> cartModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done__order_page);
        initview();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference
                .child("Users")
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Usermodel usermodel = dataSnapshot.getValue(Usermodel.class);
                        email_tv.setText(usermodel.getEmail());
                        mobile_tv.setText(usermodel.getContact());
                        address_tv.setText(usermodel.getAddress());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        cartModelArrayList = new ArrayList<CartModel>();

        final done_order_Adapter done_order_adapter = new done_order_Adapter(cartModelArrayList, Done_Order_page.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(done_order_adapter);


        Intent intent = getIntent();

        final String total = intent.getStringExtra("TOTAL");
        final String orderPushKey = intent.getStringExtra("KEY_ORDERID");
        final String uuid = firebaseAuth.getCurrentUser().getUid();
        order_id_tv.setText(orderPushKey);
        Total_price_tv_top.setText(total);


        databaseReference.child(AppConstant.FIREBASE_Order)
                .child(uuid)
                .child(orderPushKey)
                .child("cartModelArrayList")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot productModel : dataSnapshot.getChildren()) {
                            CartModel cartModel = productModel.getValue(CartModel.class);
                            cartModelArrayList.add(cartModel);
                        }
                        done_order_adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void initview() {
        order_id_tv = findViewById(R.id.view_order_done_details_product_id);
        Total_price_tv_top = findViewById(R.id.view_order_done_details_product_Total_Price);
        product_name_tv = findViewById(R.id.view_order_done_page_product_name);
        product_price_tv = findViewById(R.id.view_order_done_page_product_Price);
        email_tv = findViewById(R.id.view_order_done_details_page_Payment_details_email);
        mobile_tv = findViewById(R.id.view_order_done_details_page_Payment_details_mobile_number);
        address_tv = findViewById(R.id.view_order_done_details_page_Payment_details_User_Address);
        imageView = findViewById(R.id.view_order_done_details_imageview);
        recyclerView = findViewById(R.id.done_order_rv);
    }
}
