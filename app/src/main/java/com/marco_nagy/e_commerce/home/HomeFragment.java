package com.marco_nagy.e_commerce.home;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.databinding.FragmentHomeBinding;
import com.marco_nagy.e_commerce.home.banner.BannerAdapter;
import com.marco_nagy.e_commerce.home.banner.LatestBanner;
import com.marco_nagy.e_commerce.home.category.CategoriesAdapter;
import com.marco_nagy.e_commerce.home.category.CategoryItems;
import com.marco_nagy.e_commerce.home.latest.LatestProduct;
import com.marco_nagy.e_commerce.home.latest.LatestProductInterface;
import com.marco_nagy.e_commerce.home.latest.LatestProductsAdapter;
import com.marco_nagy.e_commerce.home.latest.LatestResponse;
import com.marco_nagy.e_commerce.product.ProductActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    NavController navController;
    FragmentHomeBinding binding;
    LatestProductsAdapter productsAdapter;
    List<LatestProduct> latestProductList = new ArrayList<>();
    private static final String TAG = "HomeFragment";


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setCategoriesRecyclerView();
        setBannerRecyclerView();
        setLatestProductsRecyclerView();
    }

    public void setCategoriesRecyclerView() {
        List<CategoryItems> categories = new ArrayList<>();
        categories.add(new CategoryItems(R.drawable.apparel, R.string.apparel));
        categories.add(new CategoryItems(R.drawable.beauty, R.string.beauty));
        categories.add(new CategoryItems(R.drawable.shoes, R.string.shoes));
        categories.add(new CategoryItems(R.drawable.electronics, R.string.electronics));
        categories.add(new CategoryItems(R.drawable.furniture, R.string.furniture));
        categories.add(new CategoryItems(R.drawable.home_1, R.string.home));
        categories.add(new CategoryItems(R.drawable.stationary, R.string.stationary));
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, getContext());
        binding.categoriesRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.categoriesRV.setAdapter(categoriesAdapter);
    }

    public void setBannerRecyclerView() {
        List<LatestBanner> latestBanner = new ArrayList<>();
        latestBanner.add(new LatestBanner(R.drawable.banner_1, R.string.summer_clothes));
        latestBanner.add(new LatestBanner(R.drawable.banner_2, R.string.summer_clothes));
        latestBanner.add(new LatestBanner(R.drawable.banner_1, R.string.summer_clothes));
        latestBanner.add(new LatestBanner(R.drawable.banner_2, R.string.summer_clothes));
        latestBanner.add(new LatestBanner(R.drawable.banner_1, R.string.summer_clothes));
        latestBanner.add(new LatestBanner(R.drawable.banner_2, R.string.summer_clothes));
        BannerAdapter bannerAdapter = new BannerAdapter(latestBanner, getContext());
        binding.latestRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.latestRV.setAdapter(bannerAdapter);

    }

    public void setLatestProductsRecyclerView() {

        AppNetworkBuilder.getClient().getLatestProducts().enqueue(new Callback<LatestResponse>() {
            @Override
            public void onResponse(@NotNull Call<LatestResponse> call, @NotNull Response<LatestResponse> response) {
                if (!response.isSuccessful()) {
                    assert response.errorBody() != null;
                    LatestResponse message = new Gson().fromJson(response.errorBody().charStream(), LatestResponse.class);
                    Log.i(TAG, "onResponse: " + message.getMessage());

                } else
                    latestProductList = response.body().getData();
                productsAdapter = new LatestProductsAdapter(latestProductList, getContext(), latestProductInterface);
                binding.productRV.setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL, false));
                binding.productRV.setAdapter(productsAdapter);

                Log.i(TAG, "onResponse: " + response.headers().toString());

            }

            @Override
            public void onFailure(Call<LatestResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });


    }

    LatestProductInterface latestProductInterface = latestProduct -> {
        Intent intent = new Intent(requireContext(), ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("latestProduct", latestProduct);
        intent.putExtras(bundle);
        startActivity(intent);
    };
}



