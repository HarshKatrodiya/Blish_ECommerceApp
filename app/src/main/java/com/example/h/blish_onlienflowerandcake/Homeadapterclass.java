package com.example.h.blish_onlienflowerandcake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class Homeadapterclass extends RecyclerView.Adapter<Homeadapterclass.MyViewhlder> {

    private ArrayList<Productmodel> arrayList;
    ItemClickListener itemClickListener;
    private Context context;


    public Homeadapterclass(ArrayList<Productmodel> arrayList, ItemClickListener itemClickListener, Context context) {
        this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewhlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowlayout_flower, viewGroup, false);
        MyViewhlder myViewhlder = new MyViewhlder(view);
        return myViewhlder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhlder myViewhlder, int i) {

        Productmodel productmodel = arrayList.get(i);
        myViewhlder.setdata(productmodel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewhlder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_productname;
        private TextView tv_productprice;
        private TextView tv_productrealprice;
        private TextView tv_productDiscount;
        private ImageView imageViewhome;
        private Productmodel productmodel;

        public MyViewhlder(@NonNull View itemView) {
            super(itemView);

            imageViewhome = itemView.findViewById(R.id.product_image_home);
            tv_productDiscount = itemView.findViewById(R.id.product_discount);
            tv_productrealprice = itemView.findViewById(R.id.product_realprise);
            tv_productname = itemView.findViewById(R.id.product_name_tv);
            tv_productprice = itemView.findViewById(R.id.product_price_tv);
            itemView.setOnClickListener(this);
        }

        public void setdata(Productmodel productmodel) {
            this.productmodel = productmodel;

            tv_productDiscount.setText(productmodel.getProductDiscount());
            tv_productrealprice.setText(productmodel.getProductrealprice());
            tv_productname.setText(productmodel.getProductname());
            tv_productprice.setText(productmodel.getProductprice());
            Glide.with(context)
                    .load(productmodel.getImageurl())
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(imageViewhome);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(productmodel);
            }
        }



    }
    public void updateList(ArrayList<Productmodel> productmodels) {
        arrayList = new ArrayList<>();
        arrayList.addAll(productmodels);
        notifyDataSetChanged();
    }
}
