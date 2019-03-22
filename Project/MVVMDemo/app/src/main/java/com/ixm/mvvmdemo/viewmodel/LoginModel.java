package com.ixm.mvvmdemo.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.ixm.mvvmdemo.BR;
import com.ixm.mvvmdemo.model.User;
import com.ixm.mvvmdemo.view.SecondActivity;

public class LoginModel extends BaseObservable {
    User user;

    String successMessage = "Success!!";
    String errorMessage = "Opps! Something Went Wrong!!!";
    String countryError = "Enter Country Name!!";
    String genderError = "Enter Your Gender!!!";

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged( BR.toastMessage );
    }

    @Bindable
    private String toastMessage = null;

    public LoginModel() {
        user = new User( "", "", "", "" );
    }

    public void onLoginClicked() {
       // Context context=view.getContext();
        if (getUserCountry().length() == 0) {
            setToastMessage( countryError );
        } else if (getUserGender().length() == 0) {
            setToastMessage( genderError );
        } else if (isInputDataValid()) {
            setToastMessage( successMessage );

        } else
            setToastMessage( errorMessage );
    }

    public boolean isInputDataValid() {

        return !TextUtils.isEmpty( getUserEmail() ) && Patterns.EMAIL_ADDRESS.matcher( getUserEmail() ).matches() && getUserPassword().length() > 5;
    }

    public String getUserEmail() {
        return user.getuEmail();
    }

    public void setUserEmail(String Email) {
        user.setuEmail( Email );
    }

    public void setUserPassword(String Password) {
        user.setuPassword( Password );
    }

    public void setUserCountry(String Country) {
        user.setuCountry( Country );
    }

    public void setUserGender(String Gender) {
        user.setuGender( Gender );
    }

    public String getUserPassword() {
        return user.getuPassword();
    }

    public String getUserCountry() {
        return user.getuCountry();
    }

    public String getUserGender() {
        return user.getuGender();
    }
}
