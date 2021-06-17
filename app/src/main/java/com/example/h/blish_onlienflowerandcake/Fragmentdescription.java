package com.example.h.blish_onlienflowerandcake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragmentdescription extends Fragment {

    View v;
    private TextView textView;

    public Fragmentdescription() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.description_details_page,container,false);
        textView=v.findViewById(R.id.Description_Page_details);

        product_all_details_new product_all_details_new= (com.example.h.blish_onlienflowerandcake.product_all_details_new) getActivity();
        textView.setText(product_all_details_new.getDescription());

        return v;


    }
}
