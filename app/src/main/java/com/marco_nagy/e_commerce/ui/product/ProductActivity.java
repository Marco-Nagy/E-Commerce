package com.marco_nagy.e_commerce.ui.product;

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

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ActivityProductBinding;
import com.marco_nagy.e_commerce.ui.home.HomeActivity;
import com.marco_nagy.e_commerce.ui.models.DataItem;
import com.marco_nagy.e_commerce.ui.models.ImagesItem;
import com.marco_nagy.e_commerce.ui.product.adapters.SliderAdapter;
import com.marco_nagy.e_commerce.ui.product.fragments.TabDetailsFragment;
import com.marco_nagy.e_commerce.ui.product.fragments.TabProductFragment;
import com.marco_nagy.e_commerce.ui.product.fragments.TabReviewsFragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    TabAdapter adapter;

  //  ProductItems productItems;
    DataItem latestProduct, searchItem;
    List<ImagesItem> images ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);


        latestProduct = (DataItem) getIntent().getSerializableExtra("latestProduct");
        searchItem = (DataItem) getIntent().getSerializableExtra("searchItems");

        if (searchItem != null){
            images = searchItem.getImages();
            binding.productTitle.setText(searchItem.getItemName());
            binding.productPrice.setText(searchItem.getPrice());

        }else {
            images = latestProduct.getImages();
            binding.productTitle.setText(latestProduct.getItemName());
            binding.productPrice.setText(latestProduct.getPrice());
        }

       ;
     //   binding.ratingText.setText(String.valueOf(latestProduct.()));

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

    private void setImageSlider() {
        SliderAdapter sliderAdapter  = new SliderAdapter(images);

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

    public static class TabAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void AddFragment(Fragment fragment, String string) {
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

    private void setTabLayout() {
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TabProductFragment(searchItem,latestProduct), "Product");
        adapter.AddFragment(new TabDetailsFragment(), "Details");
        adapter.AddFragment(new TabReviewsFragment(), "Reviews");
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}
