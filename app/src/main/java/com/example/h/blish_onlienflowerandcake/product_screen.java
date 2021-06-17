package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.awt.font.TextAttribute;

public class product_screen extends AppCompatActivity implements View.OnClickListener {

    private Button alldetailsbtn;
    private int totalamount = 0;

    private TextView Add_to_cart_product_screen_btnortv;
    private TextView productname_tv;
    private TextView productprice_tv;
    private TextView productdiscount_tv;
    private TextView productrealprice_tv;
    private ImageView product_img;

    private FirebaseAuth firebaseAuth;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        initview();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        final Intent intent = getIntent();
        String productname = intent.getStringExtra("PRODUCTNAME");
        String productprice = intent.getStringExtra("PRODUCTPRICE");
        String productrealprice = intent.getStringExtra("PRODUCTREALPRICE");
        String productdiscount = intent.getStringExtra("PRODUCTDISCOUNT");
        String productimage = intent.getStringExtra("PRODUCTIMG");

        productname_tv.setText(productname);
        productprice_tv.setText(productprice);
        productrealprice_tv.setText(productrealprice);
        productdiscount_tv.setText(productdiscount);
        Glide.with(product_screen.this)
                .load(productimage)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(product_img);
    }

    private void initview() {
        alldetailsbtn = findViewById(R.id.product_activity_all_detils_btn);
        productname_tv = findViewById(R.id.product_activity_product_name_tv);
        productprice_tv = findViewById(R.id.product_activity_price_tv);
        product_img = findViewById(R.id.product_activity_ImageView);
        productrealprice_tv = findViewById(R.id.product_page_realprice_tv);
        productdiscount_tv = findViewById(R.id.product_activity_discount_tv);

        Add_to_cart_product_screen_btnortv = findViewById(R.id.product_screen_addtocart_tv_or_btn);

        alldetailsbtn.setOnClickListener(this);
        Add_to_cart_product_screen_btnortv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.product_activity_all_detils_btn:
                alldetailsmethod();
                break;
            case R.id.product_screen_addtocart_tv_or_btn:
                gotoaddtocart_page();
                break;

        }

    }

    private void alldetailsmethod() {

        final Intent intent = getIntent();
        String productname = intent.getStringExtra("PRODUCTNAME");
        String productprice = intent.getStringExtra("PRODUCTPRICE");
        String productrealprice = intent.getStringExtra("PRODUCTREALPRICE");
        String productdiscount = intent.getStringExtra("PRODUCTDISCOUNT");
        String productimage = intent.getStringExtra("PRODUCTIMG");

        String alldiscription = intent.getStringExtra("ALLDISCRIPTION");
        String specificationinthepacket = intent.getStringExtra("SPECIFICATIONINTHEPACKET");
        String specificationcolor = intent.getStringExtra("SPECIFICATIONCOLOR");
        String specificationtype = intent.getStringExtra("SPECIFICATIONTYPE");
        String specificationweight = intent.getStringExtra("SPECIFICATIONWEIGHT");
        String specificationribboncolor = intent.getStringExtra("SPECIFICATIONRIBBONCOLOR");
        String specificationflowerqty = intent.getStringExtra("SPECIFICATIONFLOWERQTY");
        String moreinfogenericname = intent.getStringExtra("MOREINFOGENERICNAME");


        final Intent goto_all_details = new Intent(product_screen.this, product_all_details_new.class);
        goto_all_details.putExtra("PRODUCTNAME", productname);
        goto_all_details.putExtra("PRODUCTPRICE", productprice);
        goto_all_details.putExtra("PRODUCTREALPRICE", productrealprice);
        goto_all_details.putExtra("PRODUCTDISCOUNT", productdiscount);
        goto_all_details.putExtra("PRODUCTIMG", productimage);

        goto_all_details.putExtra("ALLDISCRIPTION", alldiscription);
        goto_all_details.putExtra("SPECIFICATIONINTHEPACKET", specificationinthepacket);
        goto_all_details.putExtra("SPECIFICATIONCOLOR", specificationcolor);
        goto_all_details.putExtra("SPECIFICATIONTYPE", specificationtype);
        goto_all_details.putExtra("SPECIFICATIONWEIGHT", specificationweight);
        goto_all_details.putExtra("SPECIFICATIONRIBBONCOLOR", specificationribboncolor);
        goto_all_details.putExtra("SPECIFICATIONFLOWERQTY", specificationflowerqty);
        goto_all_details.putExtra("MOREINFOGENERICNAME", moreinfogenericname);
        startActivity(goto_all_details);
    }


    private void gotoaddtocart_page() {

        final Intent intent = getIntent();
        String productname = intent.getStringExtra("PRODUCTNAME");
        String productprice = intent.getStringExtra("PRODUCTPRICE");
        String productrealprice = intent.getStringExtra("PRODUCTREALPRICE");
        String productdiscount = intent.getStringExtra("PRODUCTDISCOUNT");
        String productimage = intent.getStringExtra("PRODUCTIMG");

        final Intent goto_all_details = new Intent(product_screen.this, add_to_cart.class);
        goto_all_details.putExtra("PRODUCTNAME", productname);
        goto_all_details.putExtra("PRODUCTPRICE", productprice);
        goto_all_details.putExtra("PRODUCTREALPRICE", productrealprice);
        goto_all_details.putExtra("PRODUCTDISCOUNT", productdiscount);
        goto_all_details.putExtra("PRODUCTIMG", productimage);
        startActivity(goto_all_details);

        databaseReference
                .child(AppConstant.FIREBASE_Addtocart)
                .child(firebaseAuth.getCurrentUser().getUid())
                .push()
                .setValue(new CartModel(productimage
                        , productname,
                        productrealprice,
                        productprice,
                        productdiscount), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        if (databaseError != null) {
                            Toast.makeText(product_screen.this, "Error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(product_screen.this, "Add Cart Done ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

}
