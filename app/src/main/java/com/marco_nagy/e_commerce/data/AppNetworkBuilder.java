package com.marco_nagy.e_commerce.data;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppNetworkBuilder {
    private static Retrofit retrofit;
    public static AppApiServices getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor);
            httpClient.addInterceptor(chain -> {
                Request request = chain.
                        request()
                        .newBuilder()
                        .addHeader("Accept", "application/json; charset=UTF-8")
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
                        .build();
                return chain.proceed(request);
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://ecommerce-api.senior-consultingco.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit.create(AppApiServices.class);
    }

}
