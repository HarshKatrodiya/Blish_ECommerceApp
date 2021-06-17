package com.example.h.blish_onlienflowerandcake;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class review_Adapter extends RecyclerView.Adapter<review_Adapter.MyviewHolder>{

    ArrayList<reviewModel> reviewModelArrayList;

    public review_Adapter(ArrayList<reviewModel> reviewModelArrayList, Rating_screen rating_screen) {
        this.reviewModelArrayList = reviewModelArrayList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_review, viewGroup, false);
        review_Adapter.MyviewHolder myviewHolder = new review_Adapter.MyviewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {

        reviewModel reviewModel = reviewModelArrayList.get(i);
        myviewHolder.setData(reviewModel, i);

    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        private TextView useremail;
        private TextView userreview;
        reviewModel reviewModel;
        int position;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            useremail = itemView.findViewById(R.id.Useremail_tv);
            userreview = itemView.findViewById(R.id.Userreview_tv);
        }

        public void setData(reviewModel data, int i) {
            this.reviewModel = data;
            position = i;
            useremail.setText(reviewModel.getUserEmail());
            userreview.setText(reviewModel.getUserreview());

        }
    }
}
