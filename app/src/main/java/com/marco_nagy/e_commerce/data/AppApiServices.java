package com.marco_nagy.e_commerce.data;

import com.marco_nagy.e_commerce.ui.authentication.login.LoginRequest;
import com.marco_nagy.e_commerce.ui.authentication.login.LoginResponse;
import com.marco_nagy.e_commerce.ui.authentication.signup.SignupRequest;
import com.marco_nagy.e_commerce.ui.authentication.signup.SignupResponse;
import com.marco_nagy.e_commerce.ui.cart.AddToCartRequest;
import com.marco_nagy.e_commerce.ui.cart.AddToCartResponse;
import com.marco_nagy.e_commerce.ui.home.latest.LatestResponse;
import com.marco_nagy.e_commerce.ui.search.model.SearchRequest;
import com.marco_nagy.e_commerce.ui.search.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @POST("api/add-to-cart")
    Call<AddToCartResponse> addToCart(@Body AddToCartRequest addToCartRequest,
                                      @Header("Authorization")String token);
    @POST("api/search")
    Call<SearchResponse> getSearch(@Body SearchRequest searchRequest);


}
