package com.example.e_commerce.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.e_commerce.CartFragment;
import com.example.e_commerce.HomeActivity;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ActivityProductBinding;
import com.example.e_commerce.home.ProductItems;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;


    ProductItems productItems;
    int[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        productItems = (ProductItems) getIntent().getSerializableExtra("productItems");
        image = new int[]{productItems.getImage(), productItems.getImage(), productItems.getImage()};
        SliderAdapter sliderAdapter = new SliderAdapter(image);
        binding.imageSlider.setSliderAdapter(sliderAdapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        binding.imageSlider.startAutoCycle();
        binding.productTitle.setText(productItems.getTitle());
        binding.productPrice.setText(String.valueOf(productItems.getPrice()));
        binding.ratingText.setText(String.valueOf(productItems.getRate()));
        binding.cartBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                navigateToCartFragment();

            }
        });
        binding.addCartBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                navigateToCartFragment();

            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void navigateToCartFragment() {

        Intent intent = new Intent(ProductActivity.this, HomeActivity.class);
        intent.putExtra("FromReservation", "1");
        startActivity(intent);

    }
}