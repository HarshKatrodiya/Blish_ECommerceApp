package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class add_to_cart extends AppCompatActivity implements ItemClickAddToCartRemove, View.OnClickListener {

        private RecyclerView recyclerView;
        private ArrayList<CartModel> cartModelArrayList;
    private TextView totalamount_tv;
    private TextView Done_btn;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private int totalprice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        initview();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();



        cartModelArrayList = new ArrayList<CartModel>();

        final Add_to_cartAdapter add_to_cartAdapter = new Add_to_cartAdapter(cartModelArrayList, this, add_to_cart.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(add_to_cartAdapter);

        databaseReference
                .child(AppConstant.FIREBASE_Addtocart)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        cartModelArrayList.clear();
                        totalprice = 0;
                        for (DataSnapshot cartModels : dataSnapshot.getChildren()) {
                            CartModel cartModel = cartModels.getValue(CartModel.class);
                            cartModel.setPushkey(cartModels.getKey());
                            int price = Integer.parseInt(cartModel.getProductPrice());
                            totalprice = totalprice + price;

                            cartModelArrayList.add(cartModel);
                        }
                        totalamount_tv.setText(totalprice + "");

                        add_to_cartAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

        for (int i = 0; i < cartModelArrayList.size(); i++) {
            int price = Integer.parseInt(cartModelArrayList.get(i).getProductrealprice());
            totalprice = totalprice + price;
            Toast.makeText(this, "" + cartModelArrayList.get(i).getProductrealprice(), Toast.LENGTH_SHORT).show();
        }


    }

    private void initview() {
        recyclerView = findViewById(R.id.rv_add_to_cart);
        Done_btn = findViewById(R.id.addtocart_screen_Done_tv_or_btn);
        totalamount_tv = findViewById(R.id.Total_amount_Textview_addtocart_screen);

        Done_btn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(CartModel cartModel, View view, String pushkey) {

        databaseReference
                .child(AppConstant.FIREBASE_Addtocart)
                .child(firebaseAuth.getCurrentUser().getUid())
                .child(pushkey).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                Log.e("TUMP", databaseReference.getRef() + "");
                if (databaseError != null) {
                    Toast.makeText(add_to_cart.this, "Error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(add_to_cart.this, "Deleted Item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        final String uuid = firebaseAuth.getCurrentUser().getUid();

        final Intent intent = getIntent();
        String productname = intent.getStringExtra("PRODUCTNAME");
        String productprice = intent.getStringExtra("PRODUCTPRICE");
        String productrealprice = intent.getStringExtra("PRODUCTREALPRICE");
        String productimage = intent.getStringExtra("PRODUCTIMG");
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);

        databaseReference
                .child(AppConstant.FIREBASE_Order)
                .child(uuid)
                .push()
                .setValue(new OrderModel(formattedDate, String.valueOf(totalprice), cartModelArrayList),
                        new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference1) {
                                if (databaseError != null) {
                                    Toast.makeText(add_to_cart.this, "Error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {

                                    databaseReference
                                            .child(AppConstant.FIREBASE_Addtocart)
                                            .child(uuid)
                                            .removeValue(new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                    if (databaseError != null) {
                                                        Toast.makeText(add_to_cart.this, "Error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(add_to_cart.this, "Shopping Done", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                }
                                            });
                                }
                            }
                        });


    }
}
