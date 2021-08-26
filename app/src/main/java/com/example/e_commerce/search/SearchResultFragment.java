package com.example.e_commerce.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentSearchResultBinding;
import com.example.e_commerce.home.ProductItems;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {
FragmentSearchResultBinding binding;
NavController navController;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search_result, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.backBtn.setOnClickListener(v -> navController.navigateUp());
        setRecentlyRecyclerView();
        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.filterBtn.setImageResource(R.drawable.filter);
                navController.navigate(R.id.action_searchResultFragment_to_searchFilterFragment);
            }
        });
    }
    public void setRecentlyRecyclerView(){
        List<RatedItems> ratedItems = new ArrayList<>();
        ratedItems.add(new RatedItems(R.drawable.v_neck_shirt_pink, R.string.v_neck_pi,49.99,4.1));
        ratedItems.add(new RatedItems(R.drawable.v_neck_shirt_lim, R.string.v_neck_l,49.99,4.5));
        ratedItems.add(new RatedItems(R.drawable.r_neck_shirt, R.string.r_neck,11.00,4.6));
        ratedItems.add(new RatedItems(R.drawable.v_neck_polo, R.string.v_neck_p,49.99,4.9));
        ratedItems.add(new RatedItems(R.drawable.image_11, R.string.r_neck_bas,20.58,3.9));
        ratedItems.add(new RatedItems(R.drawable.image_12, R.string.chemise,11.00,3.5));
        RatedAdapter ratedAdapter = new RatedAdapter(ratedItems,getContext());
        binding.searchRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.searchRV.setAdapter(ratedAdapter);

    }
}