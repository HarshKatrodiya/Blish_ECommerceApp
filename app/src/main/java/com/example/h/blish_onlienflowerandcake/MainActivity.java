package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_button;
    private Button signup_button;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null)
        {
            final Intent gotohomescreen=new Intent(this,Home_page.class);
            startActivity(gotohomescreen);
        }

        initview();
    }

    private void initview() {
        login_button= findViewById(R.id.main_Activity_logib_btn);
        signup_button=findViewById(R.id.main_Activity_signup_btn);


        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_Activity_logib_btn:
                login_page();
            break;
            case R.id.main_Activity_signup_btn:
                signup_page();
                break;        }
    }


    private void signup_page() {
        final Intent gotosignuppage = new Intent(MainActivity.this,Sign_up_page.class);
        startActivity(gotosignuppage);
    }

    private void login_page() {
        final Intent gotologinpage= new Intent(MainActivity.this,login_page.class);
        startActivity(gotologinpage);
    }
}
