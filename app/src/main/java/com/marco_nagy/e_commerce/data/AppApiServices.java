package com.marco_nagy.e_commerce.data;

import com.marco_nagy.e_commerce.authentecation.login.LoginRequest;
import com.marco_nagy.e_commerce.authentecation.login.LoginResponse;
import com.marco_nagy.e_commerce.authentecation.signup.SignupRequest;
import com.marco_nagy.e_commerce.authentecation.signup.SignupResponse;
import com.marco_nagy.e_commerce.home.latest.LatestResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppApiServices {
    @POST("api/register")
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @GET("api/latest-product")
    Call<LatestResponse> getLatestProducts();
}
