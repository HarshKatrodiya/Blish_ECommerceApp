package com.example.h.blish_onlienflowerandcake;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rating_screen extends AppCompatActivity{


    private Button DoneBtn;
    private EditText review_et;
    private RecyclerView recyclerView;
    private ArrayList<reviewModel> reviewModelArrayList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_screen);
        initview();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        String review = review_et.getText().toString().trim();
        final review_Adapter review_adapter = new review_Adapter(reviewModelArrayList,Rating_screen.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Rating_screen.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(review_adapter);

        reviewModelArrayList.add(new reviewModel(""+firebaseAuth.getCurrentUser().getEmail() ," Harhs" ));


    }

    private void initview() {

        DoneBtn = findViewById(R.id.Activity_Rating_page_Done);
        review_et= findViewById(R.id.review_page_et);
        recyclerView = findViewById(R.id.reviewpage_rv_);
    }
}
