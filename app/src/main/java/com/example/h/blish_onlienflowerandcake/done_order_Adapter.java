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

public class done_order_Adapter extends RecyclerView.Adapter<done_order_Adapter.Myviewholder> {

    private ArrayList<CartModel> orderdone_arraylist;
    private Context context;

    public done_order_Adapter(ArrayList<CartModel> orderdone_arraylist, Context context) {
        this.orderdone_arraylist = orderdone_arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.done_order_page_rowlayout, viewGroup, false);
        done_order_Adapter.Myviewholder myvieewholser = new done_order_Adapter.Myviewholder(view);
        return myvieewholser;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        CartModel cartModel = orderdone_arraylist.get(i);
        myviewholder.setData(cartModel, i);
    }

    @Override
    public int getItemCount() {
        return orderdone_arraylist.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView productname;
        private TextView productprice;

        CartModel cartModel;


        public Myviewholder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.view_order_done_details_imageview);
            productname = itemView.findViewById(R.id.view_order_done_page_product_name);
            productprice = itemView.findViewById(R.id.view_order_done_page_product_Price);
        }

        public void setData(CartModel data, int i) {
            this.cartModel = data;
            productname.setText(cartModel.getProductName());
            productprice.setText(cartModel.getProductPrice());
            Glide.with(context)
                    .load(data.getImageurl())
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(imageView);
        }
    }

}