package com.example.e_commerce.shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.product.ProductItems;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentShopProductBinding;
import com.example.e_commerce.product.ProductActivity;
import com.example.e_commerce.product.ProductInterface;
import com.example.e_commerce.product.ProductsAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShopProductFragment extends Fragment {
FragmentShopProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_product, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBagsRecyclerView();
        setJacketsRecyclerView();
    }
    public void setBagsRecyclerView() {
        List<ProductItems> productItems = new ArrayList<>();
        productItems.add(new ProductItems(R.drawable.backpack_yello, R.string.yellow_bag, 19.99,4.2));
        productItems.add(new ProductItems(R.drawable.backpack_blue, R.string.back_pack, 20.58,4.3));
        productItems.add(new ProductItems(R.drawable.backpack_green, R.string.green_bag, 34.00,3.7));
        productItems.add(new ProductItems(R.drawable.backpack_yello, R.string.yellow_bag, 19.99,4.2));
        productItems.add(new ProductItems(R.drawable.backpack_blue, R.string.back_pack, 20.58,4.3));
        productItems.add(new ProductItems(R.drawable.backpack_green, R.string.green_bag, 34.00,3.7));


        ProductsAdapter productsAdapter = new ProductsAdapter(productItems, getContext(), productInterface);
        binding.bagsRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.bagsRV.setAdapter(productsAdapter);

    }
    public void setJacketsRecyclerView() {
        List<ProductItems> productItems = new ArrayList<>();
        productItems.add(new ProductItems(R.drawable.jacket_gray, R.string.grey_jacket, 49.99,4.2));
        productItems.add(new ProductItems(R.drawable.jacket_red, R.string.red_jacket, 38.99,4.3));
        productItems.add(new ProductItems(R.drawable.jacket_blue, R.string.blue_jacket, 45.00,3.7));
        productItems.add(new ProductItems(R.drawable.jacket_gray, R.string.grey_jacket, 49.99,4.2));
        productItems.add(new ProductItems(R.drawable.jacket_red, R.string.red_jacket, 38.99,4.3));
        productItems.add(new ProductItems(R.drawable.jacket_blue, R.string.blue_jacket, 45.00,3.7));


        ProductsAdapter productsAdapter = new ProductsAdapter(productItems, getContext(), productInterface);
        binding.jacketRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.jacketRV.setAdapter(productsAdapter);

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