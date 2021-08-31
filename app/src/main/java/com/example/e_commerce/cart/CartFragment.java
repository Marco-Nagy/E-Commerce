package com.example.e_commerce.cart;

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
import com.example.e_commerce.databinding.FragmentCartBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
FragmentCartBinding binding;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecentlyRecyclerView();
        binding.checkoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CheckoutActivity.class));
            }
        });

    }
    public void setRecentlyRecyclerView(){
        List<CartItems> cartItems = new ArrayList<>();
        cartItems.add(new CartItems(R.drawable.women_shoes, R.string.ankle_boots,49.99));
        cartItems.add(new CartItems(R.drawable.backpack, R.string.back_pack,20.58));
        cartItems.add(new CartItems(R.drawable.scarf, R.string.red_scarf,11.00));
        cartItems.add(new CartItems(R.drawable.women_shoes, R.string.ankle_boots,49.99));

        CartAdapter cartAdapter = new CartAdapter(cartItems,getContext());
        binding.cartRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.cartRV.setAdapter(cartAdapter);

    }
}