package com.example.h.blish_onlienflowerandcake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragmentmoreinfo extends Fragment {

    View v;

    TextView moreinfo;

    public Fragmentmoreinfo() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.moreinfo_details_page, container, false);

        moreinfo = v.findViewById(R.id.moreinfo);

        product_all_details_new product_all_details_new= (com.example.h.blish_onlienflowerandcake.product_all_details_new) getActivity();
        moreinfo.setText(product_all_details_new.getMoreinfo());

        return v;
    }
}
