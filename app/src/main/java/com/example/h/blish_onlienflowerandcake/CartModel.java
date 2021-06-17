package com.example.h.blish_onlienflowerandcake;

public class CartModel {
    private String pushkey;
    private String imageurl;
    private String productName;
    private String productrealprice;
    private String productPrice;
    private String productdiscount;

    public CartModel(String imageurl, String productName, String productrealprice, String productPrice, String productdiscount) {
        this.imageurl = imageurl;
        this.productName = productName;
        this.productrealprice = productrealprice;
        this.productPrice = productPrice;
        this.productdiscount = productdiscount;
    }

    public CartModel() {
    }

    public String getPushkey() {
        return pushkey;
    }

    public void setPushkey(String pushkey) {
        this.pushkey = pushkey;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductrealprice() {
        return productrealprice;
    }

    public void setProductrealprice(String productrealprice) {
        this.productrealprice = productrealprice;
    }

    public String getProductdiscount() {
        return productdiscount;
    }

    public void setProductdiscount(String productdiscount) {
        this.productdiscount = productdiscount;
    }
}
