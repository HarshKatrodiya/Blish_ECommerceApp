package com.example.h.blish_onlienflowerandcake;

public class reviewModel {

    String userEmail;
    String userreview;
    String pushkey;

    public reviewModel(String userEmail, String userreview) {
        this.userEmail = userEmail;
        this.userreview = userreview;
    }

    public reviewModel() {
    }

    public String getPushkey() {
        return pushkey;
    }

    public void setPushkey(String pushkey) {
        this.pushkey = pushkey;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserreview() {
        return userreview;
    }

    public void setUserreview(String userreview) {
        this.userreview = userreview;
    }
}
