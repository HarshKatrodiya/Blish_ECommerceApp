package com.example.h.blish_onlienflowerandcake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> flower = new ArrayList<String>();
        flower.add("Red Rose");
        flower.add("yellow Rose");
        flower.add("champo");
        flower.add("chameli");
        flower.add("Black Rose");

        List<String> cake = new ArrayList<String>();
        cake.add("vanila");
        cake.add("black berry");
        cake.add("black forest");
        cake.add("Strawberry cake");
        cake.add("chocolate cake");

        List<String> gift = new ArrayList<String>();
        gift.add("toys");
        gift.add("bear");
        gift.add("teddy bear");
        gift.add("ramakda");
        gift.add("doll");

        expandableListDetail.put("FLOWERS", flower);
        expandableListDetail.put("CAKES", cake);
        expandableListDetail.put("GIFTS", gift);
        return expandableListDetail;
    }
}
