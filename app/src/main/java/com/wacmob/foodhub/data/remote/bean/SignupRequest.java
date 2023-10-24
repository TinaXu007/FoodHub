package com.wacmob.foodhub.data.remote.bean;

/**
 * Created by KP on 1/18/2019.
 */

public class SignupRequest {
    private String email;
    private String password;

    public SignupRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignupRequest() {
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
