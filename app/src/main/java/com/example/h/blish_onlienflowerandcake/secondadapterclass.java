package com.example.h.blish_onlienflowerandcake;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class secondadapterclass extends RecyclerView.Adapter<secondadapterclass.myvieewholser> {

    private ArrayList arrayList1;

    public secondadapterclass(ArrayList arrayList) {
        this.arrayList1 = arrayList;
    }

    @NonNull
    public myvieewholser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowlayout_all, viewGroup ,false);
        myvieewholser myvieewholser = new myvieewholser(view);
        return myvieewholser;
    }

    @Override
    public void onBindViewHolder(@NonNull myvieewholser myvieewholser, int i) {

        Productmodel secondProductmodel = (Productmodel) arrayList1.get(i);
        myvieewholser.setData(secondProductmodel);

    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class myvieewholser extends RecyclerView.ViewHolder{

        TextView productname1;
        TextView productname2;
        TextView productname3;
        Productmodel secondProductmodel;

        public myvieewholser(@NonNull View itemView) {
            super(itemView);
            productname1 = itemView.findViewById(R.id.tv_rowlayutall_productname_1);
            productname2 = itemView.findViewById(R.id.tv_rowlayoutall_productprice_1);
            productname3 = itemView.findViewById(R.id.tv_rowlayotall_3);
        }

        public void setData(Productmodel data) {
            this.secondProductmodel = data;
            productname1.setText(secondProductmodel.getProductname());
            productname2.setText(secondProductmodel.getProductprice());

        }
    }
}
