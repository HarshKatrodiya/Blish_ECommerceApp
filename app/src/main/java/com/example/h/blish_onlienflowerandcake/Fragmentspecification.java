package com.example.h.blish_onlienflowerandcake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragmentspecification extends Fragment {

    View v;

    TextView inthePacket;
    TextView color;
    TextView type;
    TextView weight;
    TextView rinbboncolor;
    TextView flowerqty;

    public Fragmentspecification() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.specificatio_details_page, container, false);

        inthePacket = v.findViewById(R.id.In_the_Packet_specification_details_page);
        color = v.findViewById(R.id.Color_specification_details_page);
        type = v.findViewById(R.id.Type_specification_details_page);
        weight = v.findViewById(R.id.Weight_specification_details_page);
        rinbboncolor = v.findViewById(R.id.Ribbon_color_specification_details_page);
        flowerqty = v.findViewById(R.id.flower_qty_specification_details_page);


        product_all_details_new product_all_details_new = (com.example.h.blish_onlienflowerandcake.product_all_details_new) getActivity();
        String[] specification = product_all_details_new.getSpecification();


        inthePacket.setText(specification[0]);
        color.setText(specification[1]);
        type.setText(specification[2]);
        weight.setText(specification[3]);
        rinbboncolor.setText(specification[4]);
        flowerqty.setText(specification[5]);

        return v;
    }
}
