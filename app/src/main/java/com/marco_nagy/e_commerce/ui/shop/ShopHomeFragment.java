package com.marco_nagy.e_commerce.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.FragmentShopHomeBinding;
import com.marco_nagy.e_commerce.ui.home.banner.BannerAdapter;
import com.marco_nagy.e_commerce.ui.home.banner.LatestBanner;
import com.marco_nagy.e_commerce.ui.product.ProductActivity;
import com.marco_nagy.e_commerce.ui.product.ProductInterface;
import com.marco_nagy.e_commerce.ui.product.ProductItems;
import com.marco_nagy.e_commerce.ui.product.adapters.ProductsAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ShopHomeFragment extends Fragment {
    FragmentShopHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLatestRecyclerView();
        setProductsRecyclerView();

    }



    public void setLatestRecyclerView() {
        List<LatestBanner> latestItems = new ArrayList<>();
        latestItems.add(new LatestBanner(R.drawable.base, R.string.summer_clothes));
        latestItems.add(new LatestBanner(R.drawable.banner_2, R.string.summer_clothes));
        latestItems.add(new LatestBanner(R.drawable.banner_1, R.string.summer_clothes));
        latestItems.add(new LatestBanner(R.drawable.base, R.string.summer_clothes));
        latestItems.add(new LatestBanner(R.drawable.banner_1, R.string.summer_clothes));
        latestItems.add(new LatestBanner(R.drawable.banner_2, R.string.summer_clothes));

        BannerAdapter bannerAdapter = new BannerAdapter(latestItems, getContext());
        binding.latestRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.latestRV.setAdapter(bannerAdapter);

    }

    public void setProductsRecyclerView() {
        List<ProductItems> productItems = new ArrayList<>();
        productItems.add(new ProductItems(R.drawable.image_1, R.string.floral_dress, 49.99,4.2));
        productItems.add(new ProductItems(R.drawable.image_2, R.string.pattern_dress, 20.58,4.3));
        productItems.add(new ProductItems(R.drawable.image_3, R.string.cotton_dress, 11.00,3.7));
        productItems.add(new ProductItems(R.drawable.image_4, R.string.floral_dress, 49.99,4.8));
        productItems.add(new ProductItems(R.drawable.image_5, R.string.pattern_dress, 20.58,3.7));
        productItems.add(new ProductItems(R.drawable.image_6, R.string.cotton_dress, 11.00,4.9));
        productItems.add(new ProductItems(R.drawable.image_1, R.string.floral_dress, 49.99,4.2));
        productItems.add(new ProductItems(R.drawable.image_2, R.string.pattern_dress, 20.58,4.3));
        productItems.add(new ProductItems(R.drawable.image_3, R.string.cotton_dress, 11.00,3.7));
        productItems.add(new ProductItems(R.drawable.image_4, R.string.floral_dress, 49.99,4.8));
        productItems.add(new ProductItems(R.drawable.image_5, R.string.pattern_dress, 20.58,3.7));
        productItems.add(new ProductItems(R.drawable.image_6, R.string.cotton_dress, 11.00,4.9));



        ProductsAdapter productsAdapter = new ProductsAdapter(productItems, getContext(), productInterface);
        binding.productRV.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.productRV.setAdapter(productsAdapter);

    }

    ProductInterface productInterface = new ProductInterface() {

     
        @Override
        public void onProductClick(ProductItems productItems) {
            Intent intent = new Intent(requireContext(), ProductActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("productItems", productItems);
            intent.putExtras(bundle);
            startActivity(intent);
        }


    };
}