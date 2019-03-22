package com.ixm.mvvmdemo.model;

public class User {
    String uEmail;

    public User(String uEmail, String uPassword, String uCountry, String uGender) {
        this.uEmail = uEmail;
        this.uPassword = uPassword;
        this.uCountry = uCountry;
        this.uGender = uGender;
    }

    String uPassword;
    String uCountry;

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuCountry() {
        return uCountry;
    }

    public void setuCountry(String uCountry) {
        this.uCountry = uCountry;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }

    String uGender;
}
