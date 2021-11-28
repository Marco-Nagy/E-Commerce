package com.marco_nagy.e_commerce.data;

import com.marco_nagy.e_commerce.authentication.login.LoginRequest;
import com.marco_nagy.e_commerce.authentication.login.LoginResponse;
import com.marco_nagy.e_commerce.authentication.signup.SignupRequest;
import com.marco_nagy.e_commerce.authentication.signup.SignupResponse;
import com.marco_nagy.e_commerce.cart.AddToCartResponse;
import com.marco_nagy.e_commerce.home.latest.LatestResponse;
import com.marco_nagy.e_commerce.search.model.SearchRequest;
import com.marco_nagy.e_commerce.search.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AppApiServices {
    @POST("api/register")
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @GET("api/latest-product")
    Call<LatestResponse> getLatestProducts();
    @FormUrlEncoded
    @POST("api/add-to-cart")
    Call<AddToCartResponse> addToCart(@Field("product_id")String id,
                                      @Header("Authorization")String token);
    @POST("api/search")
    Call<SearchResponse> getSearch(@Body SearchRequest searchRequest);

}
