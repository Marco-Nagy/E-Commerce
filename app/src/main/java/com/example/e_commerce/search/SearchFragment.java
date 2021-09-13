package com.example.e_commerce.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentSearchBinding;
import com.example.e_commerce.product.ProductItems;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecentlyRecyclerView();
        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchResultActivity.class));
            }
        });
    }
    public void setRecentlyRecyclerView(){
        List<ProductItems> productItems = new ArrayList<>();
        productItems.add(new ProductItems(R.drawable.women_shoes, R.string.ankle_boots,49.99,4.1));
        productItems.add(new ProductItems(R.drawable.backpack, R.string.back_pack,20.58,3.5));
        productItems.add(new ProductItems(R.drawable.scarf, R.string.red_scarf,11.00,3.9));
        productItems.add(new ProductItems(R.drawable.women_shoes, R.string.ankle_boots,49.99,4.6));
        productItems.add(new ProductItems(R.drawable.backpack, R.string.back_pack,20.58,4.7));
        productItems.add(new ProductItems(R.drawable.scarf, R.string.red_scarf,11.00,4.2));
        RecentlyAdapter recentlyAdapter = new RecentlyAdapter(productItems,getContext());
        binding.recentlyRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.recentlyRV.setAdapter(recentlyAdapter);

    }
}