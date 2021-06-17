package com.example.h.blish_onlienflowerandcake;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ItemClickListener, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {

    private ArrayList<Productmodel> arrayList, arrayList2, secondarraylist;
    public RecyclerView recyclerView1, recyclerView2;
    private LinearLayout haderlayout;

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Homeadapterclass adapterclass;
    private ProgressDialog progressDialog;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        progressDialog = new ProgressDialog(Home_page.this);
        progressDialog.setTitle("Data Retrive");
        progressDialog.setMessage("Data fetching......");
        progressDialog.show();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        initview();

//        usenametv.setText(firebaseAuth.getCurrentUser().getDisplayName());
//        emailtv.setText(firebaseAuth.getCurrentUser().getEmail());

        arrayList = new ArrayList<Productmodel>();

        adapterclass = new Homeadapterclass(arrayList, this, Home_page.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(adapterclass);

        databaseReference.child("Products")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot productModel : dataSnapshot.getChildren()) {
//                            if(arrayList.size()>0)
//                            {
//                                arrayList.clear();
//                            }
                            Productmodel pm = productModel.getValue(Productmodel.class);
                            arrayList.add(pm);
                        }

                        adapterclass.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_page);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homepage);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        updatenavigationhader();
    }

    private void initview() {
        recyclerView1 = findViewById(R.id.rv_1);
        recyclerView2 = findViewById(R.id.rv_2);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homepage);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_bar, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_to_cart_menu) {
            final Intent intent = new Intent(this, add_to_cart.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            firebaseAuth.signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.Home_menu) {
            final Intent intent = new Intent(Home_page.this, Home_page.class);
            startActivity(intent);

        } else if (id == R.id.order_menu) {
            final Intent intent = new Intent(Home_page.this, Order_page.class);
            startActivity(intent);


        } else if (id == R.id.Account_menu) {

            final Intent intent = new Intent(Home_page.this, account_setting.class);
            startActivity(intent);
        } else if (id == R.id.Customer_service_menu) {

            final Intent intent = new Intent(Home_page.this, Customer_service.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homepage);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onItemClick(Productmodel productmodel) {

        final Intent gotoproduct_screenintent = new Intent(this, product_screen.class);
        gotoproduct_screenintent.putExtra("PRODUCTNAME", productmodel.getProductname());
        gotoproduct_screenintent.putExtra("PRODUCTPRICE", productmodel.getProductprice());
        gotoproduct_screenintent.putExtra("PRODUCTREALPRICE", productmodel.getProductrealprice());
        gotoproduct_screenintent.putExtra("PRODUCTDISCOUNT", productmodel.getProductDiscount());
        gotoproduct_screenintent.putExtra("PRODUCTIMG", productmodel.getImageurl());

        gotoproduct_screenintent.putExtra("ALLDISCRIPTION", productmodel.getAlldescription());
        gotoproduct_screenintent.putExtra("SPECIFICATIONINTHEPACKET", productmodel.getSpecificationInthepacket());
        gotoproduct_screenintent.putExtra("SPECIFICATIONCOLOR", productmodel.getSpecificaioncolor());
        gotoproduct_screenintent.putExtra("SPECIFICATIONTYPE", productmodel.getSpecificaionType());
        gotoproduct_screenintent.putExtra("SPECIFICATIONWEIGHT", productmodel.getSpecificaionWeight());
        gotoproduct_screenintent.putExtra("SPECIFICATIONRIBBONCOLOR", productmodel.getSpecificaionRibboncolor());
        gotoproduct_screenintent.putExtra("SPECIFICATIONFLOWERQTY", productmodel.getSpecificaionflowerqty());
        gotoproduct_screenintent.putExtra("MOREINFOGENERICNAME", productmodel.getMoreinfoGenericname());

        startActivity(gotoproduct_screenintent);


    }

    public void updatenavigationhader() {

        final Intent intent = getIntent();
        String image = intent.getStringExtra("IMAGE");
        LinearLayout linearLayout;

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View haderview = navigationView.getHeaderView(0);
        final TextView email = haderview.findViewById(R.id.email_navigation_tv);
        linearLayout = haderview.findViewById(R.id.haderlayout_drawer_home);
        final CircleImageView imageuser = haderview.findViewById(R.id.imageView_haderlayout_drawable);

        databaseReference
                .child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Usermodel usermodel = dataSnapshot.getValue(Usermodel.class);
                        email.setText(usermodel.getEmail());
                        Glide.with(Home_page.this)
                                .load(usermodel.getImageuser())
                                .apply(new RequestOptions().placeholder(R.drawable.account_user_image2))
                                .into(imageuser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent1 = new Intent(Home_page.this, account_setting.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (arrayList.size()>0) {

            ArrayList<Productmodel> productmodelArrayList = new ArrayList<>();

            if (parent.getSelectedItemPosition() == 0) {
                productmodelArrayList.addAll(arrayList);
                adapterclass.updateList(productmodelArrayList);
            } else {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getCategory().equals(parent.getSelectedItem())) {
                        productmodelArrayList.add(arrayList.get(i));
                    }
                }
                adapterclass.updateList(productmodelArrayList);
            }

        }
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String usertext = s.toLowerCase();
        ArrayList<Productmodel> searchArrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getProductname().toLowerCase().contains(usertext) ||
                    arrayList.get(i).getProductname().toLowerCase().contains(usertext)) {
                searchArrayList.add(arrayList.get(i));
            }
        }
        adapterclass.updateList(searchArrayList);
        return true;
    }
}
