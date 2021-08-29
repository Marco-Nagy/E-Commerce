package com.example.e_commerce.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ActivitySearchResultBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    ActivitySearchResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        binding.backBtn.setOnClickListener(v -> finish());
        setRecentlyRecyclerView();
        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.filterBtn.setImageResource(R.drawable.filter);
                showSearchFilterDialog();

            }
        });
    }

    public void setRecentlyRecyclerView() {
        List<RatedItems> ratedItems = new ArrayList<>();
        ratedItems.add(new RatedItems(R.drawable.v_neck_shirt_pink, R.string.v_neck_pi, 49.99, 4.1));
        ratedItems.add(new RatedItems(R.drawable.v_neck_shirt_lim, R.string.v_neck_l, 49.99, 4.5));
        ratedItems.add(new RatedItems(R.drawable.r_neck_shirt, R.string.r_neck, 11.00, 4.6));
        ratedItems.add(new RatedItems(R.drawable.v_neck_polo, R.string.v_neck_p, 49.99, 4.9));
        ratedItems.add(new RatedItems(R.drawable.image_11, R.string.r_neck_bas, 20.58, 3.9));
        ratedItems.add(new RatedItems(R.drawable.image_12, R.string.chemise, 11.00, 3.5));
        RatedAdapter ratedAdapter = new RatedAdapter(ratedItems, this);
        binding.searchRV.setLayoutManager(new GridLayoutManager(this, 2));
        binding.searchRV.setAdapter(ratedAdapter);

    }
    public void showSearchFilterDialog(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(new SearchFilterFragment(), "Filter Dialog")
                .commit();

    }
}
