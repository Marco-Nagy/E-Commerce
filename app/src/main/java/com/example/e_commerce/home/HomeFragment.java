package com.example.e_commerce.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentHomeBinding;
import com.example.e_commerce.product.ProductActivity;
import com.example.e_commerce.product.ProductInterface;
import com.example.e_commerce.product.ProductItems;
import com.example.e_commerce.product.ProductsAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    NavController navController;
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setCategoriesRecyclerView();
        setLatestRecyclerView();
        setProductsRecyclerView();
    }

    public void setCategoriesRecyclerView() {
        List<CategoryItems> categories = new ArrayList<>();
        categories.add(new CategoryItems(R.drawable.apparel, R.string.apparel));
        categories.add(new CategoryItems(R.drawable.beauty, R.string.beauty));
        categories.add(new CategoryItems(R.drawable.shoes, R.string.shoes));
        categories.add(new CategoryItems(R.drawable.electronics, R.string.electronics));
        categories.add(new CategoryItems(R.drawable.furniture, R.string.furniture));
        categories.add(new CategoryItems(R.drawable.home_1, R.string.home));
        categories.add(new CategoryItems(R.drawable.stationary, R.string.stationary));
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, getContext());
        binding.categoriesRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.categoriesRV.setAdapter(categoriesAdapter);
    }

    public void setLatestRecyclerView() {
        List<LatestItems> latestItems = new ArrayList<>();
        latestItems.add(new LatestItems(R.drawable.banner_1, R.string.summer_clothes));
        latestItems.add(new LatestItems(R.drawable.banner_2, R.string.summer_clothes));
        latestItems.add(new LatestItems(R.drawable.banner_1, R.string.summer_clothes));
        latestItems.add(new LatestItems(R.drawable.banner_2, R.string.summer_clothes));
        latestItems.add(new LatestItems(R.drawable.banner_1, R.string.summer_clothes));
        latestItems.add(new LatestItems(R.drawable.banner_2, R.string.summer_clothes));

        LatestAdapter latestAdapter = new LatestAdapter(latestItems, getContext());
        binding.latestRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.latestRV.setAdapter(latestAdapter);

    }

    public void setProductsRecyclerView() {
        List<ProductItems> productItems = new ArrayList<>();
        productItems.add(new ProductItems(R.drawable.women_shoes, R.string.ankle_boots, 49.99,4.2));
        productItems.add(new ProductItems(R.drawable.backpack, R.string.back_pack, 20.58,4.3));
        productItems.add(new ProductItems(R.drawable.scarf, R.string.red_scarf, 11.00,3.7));
        productItems.add(new ProductItems(R.drawable.women_shoes, R.string.ankle_boots, 49.99,4.8));
        productItems.add(new ProductItems(R.drawable.backpack, R.string.back_pack, 20.58,3.7));
        productItems.add(new ProductItems(R.drawable.scarf, R.string.red_scarf, 11.00,4.9));


        ProductsAdapter productsAdapter = new ProductsAdapter(productItems, getContext(), productInterface);
        binding.productRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.productRV.setAdapter(productsAdapter);

    }

    ProductInterface productInterface = productItems -> {
        Intent intent = new Intent(requireContext(), ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productItems", productItems);
        intent.putExtras(bundle);
        startActivity(intent);
    };
}



