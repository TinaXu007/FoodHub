package com.wacmob.foodhub.data.remote.bean;

/**
 * Created by KP on 1/18/2019.
 */

public class ForgotPasswordRequest {
    private String email;
    private String password;

    public ForgotPasswordRequest(String email) {
        this.email = email;
    }

    public ForgotPasswordRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
