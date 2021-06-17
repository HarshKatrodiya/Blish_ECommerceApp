package com.example.h.blish_onlienflowerandcake;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_profile_page extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMG_CODE = 100;
    private FloatingActionButton floatingActionButton;
    private EditText username;
    private EditText email;
    private EditText mobile;
    private EditText address;
    private Button Done;
    private CircleImageView profileimage;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private ProgressDialog progressDialog;


    private Uri selectedFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);
        initview();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference
                .child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Usermodel usermodel = dataSnapshot.getValue(Usermodel.class);

                        username.setText(usermodel.getUsername());
                        email.setText(usermodel.getEmail());
                        mobile.setText(usermodel.getContact());
                        address.setText(usermodel.getAddress());
                        Glide.with(getApplicationContext())
                                .load(usermodel.getImageuser())
                                .apply(new RequestOptions().placeholder(R.drawable.account_user_image2))
                                .into(profileimage);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void initview() {

        floatingActionButton = findViewById(R.id.floatingActionButton);
        profileimage  = findViewById(R.id.imageview_account_profile);
        username = findViewById(R.id.edit_profile_username);
        email = findViewById(R.id.edit_profile_email);
        mobile = findViewById(R.id.edit_profile_mobile_et);
        address = findViewById(R.id.edit_profile_Address_et);
        Done = findViewById(R.id.edit_profil_submit_btn);

        floatingActionButton.setOnClickListener(this);
        Done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                case R.id.floatingActionButton:
                    selectimage();
                    break;
            case R.id.edit_profil_submit_btn:
                editprofile();
                break;
        }
    }

    private void editprofile() {


        String newUsername = username.getText().toString();
        String newemail = email.getText().toString();
        String newmobile = mobile.getText().toString();
        String newaddress = address.getText().toString();


        Usermodel usermodel = new Usermodel();
        usermodel.setUsername(newUsername);
        usermodel.setEmail(newemail);
        usermodel.setContact(newmobile);
        usermodel.setAddress(newaddress);

        databaseReference.child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("username").setValue(newUsername);
        finish();
        databaseReference.child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("email").setValue(newemail);
        finish();
        databaseReference.child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("contact").setValue(newmobile);
        finish();
        databaseReference.child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("address").setValue(newaddress);
        finish();

        if (selectedFileIntent != null) {

            final StorageReference sRef = storageReference.child("ProfileImageUser/" + UUID.randomUUID().toString());

            sRef.putFile(selectedFileIntent)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Edit_profile_page.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    insertDataInToDb(uri);
                                    finish();
                                }
                            });

                        }
                    });
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

    private void insertDataInToDb(Uri uri) {

        databaseReference.child(AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("imageuser")
                .setValue(uri.toString(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        if (databaseError != null) {
                            Toast.makeText(Edit_profile_page.this, "Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Edit_profile_page.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                });

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
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!this.isDestroyed()){
                Glide.with(Edit_profile_page.this).pauseRequests();
            }
        }
    }
}
