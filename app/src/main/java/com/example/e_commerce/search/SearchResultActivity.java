package com.example.e_commerce.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ActivitySearchResultBinding;
import com.example.e_commerce.product.ProductActivity;
import com.example.e_commerce.product.ProductInterface;
import com.example.e_commerce.product.ProductItems;

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
        List<ProductItems> productItems = new ArrayList<>();
        productItems.add(new ProductItems(R.drawable.v_neck_shirt_pink, R.string.v_neck_pi, 49.99, 4.1));
        productItems.add(new ProductItems(R.drawable.v_neck_shirt_lim, R.string.v_neck_l, 49.99, 4.5));
        productItems.add(new ProductItems(R.drawable.r_neck_shirt, R.string.r_neck, 11.00, 4.6));
        productItems.add(new ProductItems(R.drawable.v_neck_polo, R.string.v_neck_p, 49.99, 4.9));
        productItems.add(new ProductItems(R.drawable.image_11, R.string.r_neck_bas, 20.58, 3.9));
        productItems.add(new ProductItems(R.drawable.image_12, R.string.chemise, 11.00, 3.5));
        RatedAdapter ratedAdapter = new RatedAdapter(productItems, this,productInterface);
        binding.searchRV.setLayoutManager(new GridLayoutManager(this, 2));
        binding.searchRV.setAdapter(ratedAdapter);

    }
    public void showSearchFilterDialog(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(new SearchFilterFragment(), "Filter Dialog")
                .commit();

    }
    ProductInterface productInterface = new ProductInterface() {
        @Override
        public void onProductClick(ProductItems productItems) {
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("productItems", productItems);
            intent.putExtras(bundle);
            startActivity(intent);

        }

    };
}
