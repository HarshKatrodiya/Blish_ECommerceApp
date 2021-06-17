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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Myviewholder> {

    private ArrayList<OrderModel> orderModelArrayList;
    private ItemClickOrderListener itemClickListener;
    private Context context;

    public OrderAdapter(ArrayList<OrderModel> orderModelArrayList, ItemClickOrderListener itemClickListener, Context context) {
        this.orderModelArrayList = orderModelArrayList;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    public class Myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView order_total;
        private TextView order_date;
        OrderModel ordermodel;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            order_total = itemView.findViewById(R.id.row_layout_orderpage_total);
            order_date= itemView.findViewById(R.id.row_layout_orderpage_date);
            itemView.setOnClickListener(this);

        }

        public void setdata(OrderModel data) {

            this.ordermodel = data;


            order_total.setText(data.getOrderTotal());
            order_date.setText(data.getOrderDate());
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
            {
                itemClickListener.onItemClick(ordermodel);
            }
        }
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_orderpage, viewGroup, false);
        Myviewholder myvieewholser = new Myviewholder(view);
        return myvieewholser;

    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        OrderModel ordermodel = orderModelArrayList.get(i);
        myviewholder.setdata(ordermodel);
    }

    @Override
    public int getItemCount() {
        return orderModelArrayList.size();
    }
}
