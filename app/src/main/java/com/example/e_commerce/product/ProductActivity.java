package com.example.e_commerce.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commerce.ProductItems;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ActivityProductBinding;
import com.example.e_commerce.home.HomeActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    TabAdapter adapter;

    ProductItems productItems;

    int[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);

        productItems = (ProductItems) getIntent().getSerializableExtra("productItems");
        image = new int[]{productItems.getImage(), productItems.getImage(), productItems.getImage()};

        binding.productTitle.setText(productItems.getTitle());
        binding.productPrice.setText(String.valueOf(productItems.getPrice()));
        binding.ratingText.setText(String.valueOf(productItems.getRate()));
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
        setImageSlider();
        setTabLayout();
    }
    private void setImageSlider(){

        SliderAdapter sliderAdapter = new SliderAdapter(image);
        binding.imageSlider.setSliderAdapter(sliderAdapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        binding.imageSlider.startAutoCycle();
    }

    private void navigateToCartFragment() {

        Intent intent = new Intent(ProductActivity.this, HomeActivity.class);
        intent.putExtra("FromReservation", "1");
        startActivity(intent);

    }
    private static class TabAdapter extends FragmentPagerAdapter{
        ArrayList<Fragment> fragmentArrayList =new ArrayList<>();
        ArrayList<String> stringArrayList =new ArrayList<>();
        public void AddFragment(Fragment fragment ,String string){
            fragmentArrayList.add(fragment);
            stringArrayList.add(string);

        }

        public TabAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }


        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }
    private void setTabLayout(){
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TabProductFragment(),"Product");
        adapter.AddFragment(new TabDetailsFragment(),"Details");
        adapter.AddFragment(new TabReviewsFragment(),"Reviews");
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
    private void addToCart(){

    }
}
