
package com.marco_nagy.e_commerce.authentecation.login;

import android.util.Patterns;


import com.google.gson.annotations.SerializedName;


public class LoginRequest  {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }
}
