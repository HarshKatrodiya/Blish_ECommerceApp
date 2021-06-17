package com.example.h.blish_onlienflowerandcake;

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
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class Sign_up_page extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMG_CODE = 100;
    private Button signup_btn;
    private EditText emailet;
    private EditText usernameet;
    private EditText passwordet;
    private EditText contactet;
    private EditText adddresset;
    private FirebaseAuth firebaseAuth;

    private FloatingActionButton floatingActionButton;
    private ImageView profileimage;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Uri selectedFileIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        initview();
    }

    private void initview() {
        signup_btn = findViewById(R.id.main_Activity_signin_btnv);
        emailet = findViewById(R.id.E_mail_sign_in_page);
        usernameet = findViewById(R.id.username_sign_in_page);
        passwordet = findViewById(R.id.Password_sign_in_page);
        contactet = findViewById(R.id.Contact_no_sign_in_page);
        adddresset  = findViewById(R.id.Address_sign_in_page);

        floatingActionButton = findViewById(R.id.floatingActionButton_signup_page);
        profileimage  = findViewById(R.id.imageview_signup_profile);

        floatingActionButton.setOnClickListener(this);

        signup_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_Activity_signin_btnv:
                click_signupbtn();
                break;
            case R.id.floatingActionButton_signup_page:
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

    private void insertDataInToDb(Uri uri) {

        databaseReference.child (AppConstant.FIREBASE_USERS)
                .child(firebaseAuth.getCurrentUser().getUid())
                .setValue (new Usermodel (uri.toString ( ),usernameet.getText ().toString (),emailet.getText ().toString (),contactet.getText ().toString ()
                        ,adddresset.getText ().toString (),passwordet.getText ().toString ()), new DatabaseReference.CompletionListener ( ) {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        if (databaseError != null) {
                            Toast.makeText (Sign_up_page.this, "Error" + databaseError.getMessage ( ), Toast.LENGTH_SHORT).show ( );
                        } else {
                            Toast.makeText (Sign_up_page.this, "Data Inserted", Toast.LENGTH_SHORT).show ( );
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

    private void click_signupbtn() {

        final String email = emailet.getText().toString().trim();
        final String password = passwordet.getText().toString().trim();
        final String username = usernameet.getText().toString().trim();
        final String contect = contactet.getText().toString().trim();
        final String address = adddresset.getText().toString().trim();
        final String image = profileimage.toString();

        if (password.isEmpty() || email.isEmpty() || username.isEmpty() || contect.isEmpty() || address.isEmpty() || image.isEmpty()) {
            Toast.makeText(this, "Please Enter Your Data", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please Enter a Valid Email ID", Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.createUserWithEmailAndPassword (email, password)
                    .addOnCompleteListener (new OnCompleteListener <AuthResult> ( ) {
                        @Override
                        public void onComplete(@NonNull Task <AuthResult> task) {
                            if (task.isSuccessful ( )) {
                                Toast.makeText (Sign_up_page.this, "SignUp Success", Toast.LENGTH_SHORT).show ( );
                                //insertUserToDatabase (email_signup, password_signup, username_signup, phonenum, enrollmentno);
                                if (selectedFileIntent != null) {

                                    final StorageReference sRef = storageReference.child ("ProfileImageUser/" + UUID.randomUUID ( ).toString ( ));

                                    sRef.putFile (selectedFileIntent)
                                            .addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot>( ) {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    Toast.makeText (Sign_up_page.this, "Uploaded", Toast.LENGTH_SHORT).show ( );

                                                    sRef.getDownloadUrl ( ).addOnSuccessListener (new OnSuccessListener <Uri> ( ) {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            insertDataInToDb (uri);
                                                        }
                                                    });
                                                }
                                            });
                                }
                                final Intent loginactivity = new Intent (Sign_up_page.this, login_page.class);
                                startActivity (loginactivity);
                            } else {
                                Toast.makeText (Sign_up_page.this, "" + task.getException ( ), Toast.LENGTH_SHORT).show ( );
                            }
                        }
                    });
        }
        }
}

