package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class account_setting extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final int PICK_IMG_CODE = 100 ;
    private TextView editprofile_tv;
    private TextView orderpage_tv;
    private TextView review_tv;
    private TextView useremail_tv;
    private TextView user_mobile_number;
    private Uri selectedFileIntent;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

   // private FloatingActionButton floatingActionButton;
    private CircleImageView profileimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_account_settng);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        initview();



        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        updatenavigationhader();

    }

    private void initview() {
        editprofile_tv = findViewById(R.id.tv_edit_profile_page);
        orderpage_tv  = findViewById(R.id.myAccount_orderpage_tv);
        useremail_tv = findViewById(R.id.user_email_accountpage_tv);
        user_mobile_number = findViewById(R.id.user_mobilenumber_account_tv);

        profileimage  = findViewById(R.id.imageview_account_profile);

        editprofile_tv.setOnClickListener(this);
        orderpage_tv.setOnClickListener(this);


        databaseReference
                .child ("Users")
                .child (firebaseAuth.getCurrentUser ( ).getUid ( ))
                .addValueEventListener (new ValueEventListener ( ) {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Usermodel usermodel = dataSnapshot.getValue (Usermodel.class);
                        Glide.with (account_setting.this)
                                .load (usermodel.getImageuser())
                                .apply (new RequestOptions ( ).placeholder (R.drawable.account_user_image2))
                                .into (profileimage);
                        useremail_tv.setText(usermodel.getEmail());
                        user_mobile_number.setText(usermodel.getContact());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_to_cart_menu_account) {
            final Intent intent = new Intent(this,add_to_cart.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home_menu) {
            final Intent intent = new Intent(account_setting.this,Home_page.class);
            startActivity(intent);

        } else if (id == R.id.order_menu) {
            final Intent intent = new Intent(account_setting.this,Order_page.class);
            startActivity(intent);

        } else if (id == R.id.Account_menu){
            final Intent intent = new Intent(account_setting.this,account_setting.class);
            startActivity(intent);

        }else if (id == R.id.Customer_service_menu){

            final Intent intent = new Intent(account_setting.this, Customer_service.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_edit_profile_page:
                gotoeditprofilepage();
                break;
            case R.id.myAccount_orderpage_tv:
                gotoorderpage();
                break;
                case R.id.floatingActionButton:
                    selectimage();
                    break;
        }
    }


    private void selectimage() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        } //creating an intent for file chooser

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMG_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMG_CODE
                && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                File file = new File(data.getData().getPathSegments().toString());
                selectedFileIntent = data.getData();

                InputStream stream = null;
                try {

                    stream = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    profileimage.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //  profileimage.setText(file.getName().substring(0, file.getName().length() - 1));
            } else {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void gotoreviewpage() {
        final  Intent intent = new Intent(this,Rating_screen.class);
        startActivity(intent);
    }

    private void gotoorderpage() {
        final Intent intent = new Intent(account_setting.this,Order_page.class);
        startActivity(intent);
    }

    private void gotoeditprofilepage() {
        final Intent intent = new Intent(this,Edit_profile_page.class);
        startActivity(intent);
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
                .child (AppConstant.FIREBASE_USERS)
                .child (firebaseAuth.getCurrentUser ( ).getUid ( ))
                .addValueEventListener (new ValueEventListener ( ) {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Usermodel usermodel = dataSnapshot.getValue (Usermodel.class);
                        email.setText(usermodel.getEmail());
                        Glide.with (account_setting.this)
                                .load (usermodel.getImageuser())
                                .apply (new RequestOptions ( ).placeholder (R.drawable.account_user_image2))
                                .into (imageuser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
