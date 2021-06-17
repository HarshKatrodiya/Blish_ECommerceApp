package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class product_all_details_new extends AppCompatActivity {

    private TextView productname_tv_details;
    private TextView productrealprice_tv_details;
    private TextView productdiscount_tv_details;
    private TextView productprice_tv_details;
    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String description;
    private String specification;
    private String moreinfo;
    private ViewpageAdapter adapter;
    private String color;
    private String type;
    private String weight;
    private String ribboncolor;
    private String flowerqty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_all_details_new);

        initview();
        adapter = new ViewpageAdapter(getSupportFragmentManager());

        adapter.Addfragment(new Fragmentdescription(), "Description");
        adapter.Addfragment(new Fragmentspecification(), "Specification");
        adapter.Addfragment(new Fragmentmoreinfo(), "More Info");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        final Intent intent = getIntent();

        String product_img = intent.getStringExtra("PRODUCTIMG");
        productname_tv_details.setText(intent.getStringExtra("PRODUCTNAME"));
        productrealprice_tv_details.setText(intent.getStringExtra("PRODUCTREALPRICE"));
        productdiscount_tv_details.setText(intent.getStringExtra("PRODUCTDISCOUNT"));
        productprice_tv_details.setText(intent.getStringExtra("PRODUCTPRICE"));
        description = intent.getStringExtra("ALLDISCRIPTION");

        specification = intent.getStringExtra("SPECIFICATIONINTHEPACKET");
        color = intent.getStringExtra("SPECIFICATIONCOLOR");
        type = intent.getStringExtra("SPECIFICATIONTYPE");
        weight = intent.getStringExtra("SPECIFICATIONWEIGHT");
        ribboncolor = intent.getStringExtra("SPECIFICATIONRIBBONCOLOR");
        flowerqty = intent.getStringExtra("SPECIFICATIONFLOWERQTY");

        moreinfo = intent.getStringExtra("MOREINFOGENERICNAME");

        Glide.with(product_all_details_new.this)
                .load(product_img)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(imageView);


    }

    private void initview() {
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_page_id);
        imageView = findViewById(R.id.imageview_details_page);
        productprice_tv_details = findViewById(R.id.product_details_all_product_price_tv);
        productrealprice_tv_details = findViewById(R.id.product_details_all_productrealprice_tv);
        productdiscount_tv_details = findViewById(R.id.product_details_all_productdiscount_tv);
        productname_tv_details = findViewById(R.id.product_details_all_product_name_tv);
    }

    public String getDescription() {
        return description;
    }

    public String[] getSpecification() {
        return new String[]{specification, color, type, weight, ribboncolor, flowerqty};
    }

    public String getMoreinfo() {
        return moreinfo;
    }

}
