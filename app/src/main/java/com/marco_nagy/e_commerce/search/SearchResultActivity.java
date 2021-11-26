package com.marco_nagy.e_commerce.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.databinding.ActivitySearchResultBinding;
import com.marco_nagy.e_commerce.home.latest.models.DataItem;
import com.marco_nagy.e_commerce.product.ProductActivity;
import com.marco_nagy.e_commerce.search.model.SearchRequest;
import com.marco_nagy.e_commerce.search.model.SearchResponse;
import com.marco_nagy.e_commerce.ui.search.SearchFilterFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    ActivitySearchResultBinding binding;
    List<DataItem> searchItems;
    private static final String TAG = "SearchResultActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        binding.backBtn.setOnClickListener(v -> finish());

        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.filterBtn.setImageResource(R.drawable.filter);
                showSearchFilterDialog();

            }
        });
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      getSearchResult();
                    }
                },1500);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getSearchResult();
    }
    public void setSearchRecyclerView()  {

        SearchAdapter searchAdapter = new SearchAdapter(searchItems, this, searchInterface);
        binding.searchRV.setLayoutManager(new GridLayoutManager( this, 2));
        binding.searchRV.setAdapter(searchAdapter);
    }

    private void getSearchResult(){
        String search = binding.searchEditText.getText().toString().trim();
        SearchRequest searchRequest = new SearchRequest(search);
        Log.i(TAG, "getSearchResult: "+search);
        if (!(search.isEmpty())){
            AppNetworkBuilder.getClient().getSearch(searchRequest).enqueue(new Callback<SearchResponse>() {
                @Override
                public void onResponse(@NotNull Call<SearchResponse> call, @NotNull Response<SearchResponse> response) {
                    if (response.isSuccessful()){
                        assert response.body() != null;

                        searchItems =response.body().getData();
                        setSearchRecyclerView();
                        Log.i(TAG, "onResponse: "+response.body().getData().toString());

                    }
                }

                @Override
                public void onFailure(@NotNull Call<SearchResponse> call, @NotNull Throwable t) {

                }
            });
        }
    }

    public void showSearchFilterDialog() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(new SearchFilterFragment(), "Filter Dialog")
                .commit();

    }

    final SearchInterface searchInterface = new SearchInterface() {
        @Override
        public void onSearchProductClick(DataItem searchItem) {
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);

            intent.putExtra("searchItems", searchItem);

            startActivity(intent);
        }


    };
}

