package com.example.h.blish_onlienflowerandcake;

import java.util.ArrayList;

public class OrderModel {


    private String pushKey;
    private String orderDate;
    private String orderTotal;
    private ArrayList<CartModel> cartModelArrayList;

    public OrderModel() {
    }


    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public OrderModel(String orderDate, String orderTotal, ArrayList<CartModel> cartModelArrayList) {
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.cartModelArrayList = cartModelArrayList;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public ArrayList<CartModel> getCartModelArrayList() {
        return cartModelArrayList;
    }

    public void setCartModelArrayList(ArrayList<CartModel> cartModelArrayList) {
        this.cartModelArrayList = cartModelArrayList;
    }
}
