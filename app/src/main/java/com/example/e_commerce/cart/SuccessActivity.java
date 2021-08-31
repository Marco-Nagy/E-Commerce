package com.example.e_commerce.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ActivitySuccessBinding;
import com.example.e_commerce.home.HomeActivity;
import com.example.e_commerce.product.ProductActivity;

public class SuccessActivity extends AppCompatActivity {
ActivitySuccessBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_success);
        binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCartFragment();
            }
        });
        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void navigateToCartFragment() {

        Intent intent = new Intent(SuccessActivity.this, HomeActivity.class);
        intent.putExtra("FromReservation", "1");
        startActivity(intent);

    }
}