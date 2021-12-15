package com.marco_nagy.e_commerce.data;

import com.marco_nagy.e_commerce.ui.authentication.login.LoginRequest;
import com.marco_nagy.e_commerce.ui.authentication.login.LoginResponse;
import com.marco_nagy.e_commerce.ui.authentication.signup.SignupRequest;
import com.marco_nagy.e_commerce.ui.authentication.signup.SignupResponse;
import com.marco_nagy.e_commerce.ui.cart.addModel.AddResponse;
import com.marco_nagy.e_commerce.ui.cart.addToCartModel.AddToCartRequest;
import com.marco_nagy.e_commerce.ui.cart.addToCartModel.AddToCartResponse;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.GetCartResponse;
import com.marco_nagy.e_commerce.ui.cart.placeOrderModel.PlaceOrderRequest;
import com.marco_nagy.e_commerce.ui.cart.placeOrderModel.PlaceOrderResponse;
import com.marco_nagy.e_commerce.ui.cart.removeModel.RemoveResponse;
import com.marco_nagy.e_commerce.ui.cart.subModel.SubResponse;
import com.marco_nagy.e_commerce.ui.home.latest.LatestResponse;
import com.marco_nagy.e_commerce.ui.search.model.SearchRequest;
import com.marco_nagy.e_commerce.ui.search.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("api/cart")
    Call<GetCartResponse> getCart(@Header("Authorization")String token);

    @GET("api/add-qty/{item_id}")
    Call<AddResponse> getAddQuantity(@Path("item_id") int quantity,
                                     @Header("Authorization")String token);

    @GET("api/sub-qty/{item_id}")
    Call<SubResponse> getSubQuantity(@Path("item_id") int quantity,
                                     @Header("Authorization")String token);

    @POST("api/remove-item/{item_id}")
    Call<RemoveResponse> removeItem(@Path("item_id") int itemId,
                                    @Header("Authorization")String token);

    @POST("api/place-order")
    Call<PlaceOrderResponse> addPlaceOrder(@Body PlaceOrderRequest placeOrderRequest,
                                           @Header("Authorization")String token);

}
