package com.marco_nagy.e_commerce.ui.shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ActivityShopBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    ActivityShopBinding binding;
    TabAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_shop);
        binding.backBtn.setOnClickListener(v -> finish());
        setTabLayout();
    }
    private static class TabAdapter extends FragmentPagerAdapter {
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
        adapter.AddFragment(new ShopHomeFragment(),"Home");
        adapter.AddFragment(new ShopProductFragment(),"Product");
        adapter.AddFragment(new ShopReviewFragment(),"Reviews");
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}