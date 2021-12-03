package com.marco_nagy.e_commerce.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.ActivityProductBinding;
import com.marco_nagy.e_commerce.ui.cart.AddToCartRequest;
import com.marco_nagy.e_commerce.ui.cart.AddToCartResponse;
import com.marco_nagy.e_commerce.ui.home.HomeActivity;
import com.marco_nagy.e_commerce.ui.models.ColorItem;
import com.marco_nagy.e_commerce.ui.models.DataItem;
import com.marco_nagy.e_commerce.ui.models.ImagesItem;
import com.marco_nagy.e_commerce.ui.models.SizesItem;
import com.marco_nagy.e_commerce.ui.product.adapters.SliderAdapter;
import com.marco_nagy.e_commerce.ui.product.fragments.TabDetailsFragment;
import com.marco_nagy.e_commerce.ui.product.fragments.TabProductFragment;
import com.marco_nagy.e_commerce.ui.product.fragments.TabReviewsFragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    TabAdapter adapter;

    private static final String TAG = "ProductActivity";
  //  ProductItems productItems;
    DataItem latestProduct, searchItem;
    List<ImagesItem> images ;
    String colorItemSelected;
    int sizesItemSelected;
    int id;
    String token=  SharedPref.read(SharedPref.Token, null);
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
            id= searchItem.getItemId();

        }else {
            images = latestProduct.getImages();
            binding.productTitle.setText(latestProduct.getItemName());
            binding.productPrice.setText(latestProduct.getPrice());
            id = latestProduct.getItemId();
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
               if(colorItemSelected == null){
                   Toast.makeText(ProductActivity.this, "Please Select color", Toast.LENGTH_SHORT).show();
                   return;
               }
              if(sizesItemSelected <=0){
                  Toast.makeText(ProductActivity.this, "Pleas Select size", Toast.LENGTH_SHORT).show();
                  return;
              }
                AddToCartRequest addToCartRequest = new AddToCartRequest(id,sizesItemSelected,colorItemSelected);
                AppNetworkBuilder.getClient().addToCart(addToCartRequest,token).enqueue(new Callback<AddToCartResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<AddToCartResponse> call, @NotNull Response<AddToCartResponse> response) {
                        if (response.isSuccessful()){
                            assert response.body() != null;
                            Toast.makeText(ProductActivity.this, "Product add"+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            navigateToCartFragment();
                        }else {
                            assert response.errorBody() != null;
                            AddToCartResponse message = new Gson().fromJson(response.errorBody().charStream(), AddToCartResponse.class);
                            Toast.makeText(ProductActivity.this, "" + message.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<AddToCartResponse> call, @NotNull Throwable t) {
                        Toast.makeText(ProductActivity.this, "Failure Error => "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


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
        adapter.AddFragment(new TabProductFragment(searchItem,latestProduct,colorInterface,sizeInterface), "Product");
        adapter.AddFragment(new TabDetailsFragment(), "Details");
        adapter.AddFragment(new TabReviewsFragment(), "Reviews");
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    ColorInterface colorInterface = new ColorInterface() {
        @Override
        public void onColorSelect(ColorItem colorItem) {
            colorItemSelected =colorItem.getColorId();
            Log.i(TAG, "onColorSelect: "+colorItem.getColorId());
        }
    };
    SizeInterface sizeInterface = new SizeInterface() {
        @Override
        public void onSizeSelect(SizesItem sizesItem) {
            sizesItemSelected = sizesItem.getId();
            Log.i(TAG, "onSizeSelect: "+sizesItem.getId());
        }
    };
}
