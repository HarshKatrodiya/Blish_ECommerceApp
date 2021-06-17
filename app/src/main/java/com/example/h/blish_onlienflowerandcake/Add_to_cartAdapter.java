package com.example.h.blish_onlienflowerandcake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class Add_to_cartAdapter extends RecyclerView.Adapter<Add_to_cartAdapter.Myviewholder> {

        private ArrayList<CartModel> add_to_cartArrayList;
    ItemClickAddToCartRemove itemClickAddToCartRemove;
    private Context context;

    public Add_to_cartAdapter(ArrayList<CartModel> add_to_cartArrayList, ItemClickAddToCartRemove itemClickAddToCartRemove, Context context) {
        this.add_to_cartArrayList = add_to_cartArrayList;
        this.itemClickAddToCartRemove = itemClickAddToCartRemove;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_to_cart_row_layout, viewGroup, false);
        Myviewholder myvieewholser = new Myviewholder(view);
        return myvieewholser;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        CartModel cartModel = add_to_cartArrayList.get(i);
        myviewholder.setData(cartModel, i);
    }

    @Override
    public int getItemCount() {
        return add_to_cartArrayList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView product_name;
        private TextView product_realprice;
        private TextView product_price;
        private TextView product_discount;
        private TextView productqty;
        private LinearLayout removebtnortv;

        CartModel cartModel;
        int position;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_addtocart_screen);
            product_name = itemView.findViewById(R.id.add_to_cart_product_name);
            product_realprice = itemView.findViewById(R.id.add_to_cart_product_realprice);
            product_price = itemView.findViewById(R.id.add_to_cart_product_price);
            product_discount = itemView.findViewById(R.id.add_to_cart_product_discount);
            productqty = itemView.findViewById(R.id.add_to_cart_qty_tv);

            removebtnortv = itemView.findViewById(R.id.addtocart_remove_btn_or_tv);

            removebtnortv.setOnClickListener(this);
        }

        public void setData(CartModel data, int i) {
            this.cartModel = data;
            position = i;
            product_name.setText(cartModel.getProductName());
            product_realprice.setText(cartModel.getProductrealprice());
            product_price.setText(cartModel.getProductPrice());
            product_discount.setText(cartModel.getProductdiscount());
            Glide.with(context)
                    .load(data.getImageurl())
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            if (itemClickAddToCartRemove != null) {
                itemClickAddToCartRemove.onItemClick(cartModel, view, cartModel.getPushkey());
            }
        }
    }
}
