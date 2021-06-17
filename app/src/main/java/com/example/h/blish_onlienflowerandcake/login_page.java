package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_page extends AppCompatActivity implements View.OnClickListener {

    private TextView forgot_pass;
    private EditText emailet;
    private EditText passwordet;
    private Button login_btn;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        firebaseAuth = FirebaseAuth.getInstance();

        initview();


    }
    private void initview() {
        forgot_pass = findViewById(R.id.login_pass_forgot);
        emailet = findViewById(R.id.email_login_page);
        passwordet = findViewById(R.id.password_login_page);
        login_btn = findViewById(R.id.main_Activity_login_btn);

        forgot_pass.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_pass_forgot:
                forgotpass();
                break;
            case R.id.main_Activity_login_btn:
                gotohomepage();
                break;
        }
    }

    private void gotohomepage() {

        final String email = emailet.getText().toString().trim();
        final String password = passwordet.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please Enter Your Details", Toast.LENGTH_SHORT).show();
        }
        else {

            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(login_page.this, "Log In Successfull", Toast.LENGTH_SHORT).show();
                                gotohomescreen();
                            }
                            else
                            {
                                Toast.makeText(login_page.this, "Enter Your Valid Details", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void gotohomescreen() {


        final Intent gotohomepage = new Intent(login_page.this,Home_page.class);
        startActivity(gotohomepage);
    }

    private void forgotpass() {
        final Intent forgotpasswordp = new Intent(login_page.this,forgot_password_page.class);
        startActivity(forgotpasswordp);
    }
}
