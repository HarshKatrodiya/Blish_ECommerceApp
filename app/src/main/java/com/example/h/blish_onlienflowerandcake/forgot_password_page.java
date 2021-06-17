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
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password_page extends AppCompatActivity implements View.OnClickListener {

    private TextView back_tv_fp;
    private EditText etforgotpassword;
    private Button btnforgotpass;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);
        initview();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initview() {
        back_tv_fp = findViewById(R.id.back_tv_fp);
        etforgotpassword = findViewById(R.id.et_forgot_pass);
        btnforgotpass = findViewById(R.id.fp_btn_forgotpass_page);

        back_tv_fp.setOnClickListener(this);
        btnforgotpass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_tv_fp:
                backtvmethod();
                break;
            case R.id.fp_btn_forgotpass_page:
                clicl_fp_btn();
                break;
        }
    }

    private void clicl_fp_btn() {
        final  String email = etforgotpassword.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(this, "Please Enter Your E-mail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(forgot_password_page.this, "Sent E-mail", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(forgot_password_page.this, "Please Enter Valid E-mail Id", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
    private void backtvmethod() {
        final Intent backtologinpage = new Intent(forgot_password_page.this,login_page.class);
        startActivity(backtologinpage);
    }
}
