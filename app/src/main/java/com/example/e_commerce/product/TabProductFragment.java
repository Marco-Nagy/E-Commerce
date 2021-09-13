package com.example.e_commerce.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentTabProductBinding;
import com.example.e_commerce.review.ReviewItem;
import com.example.e_commerce.review.ReviewsAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class TabProductFragment extends Fragment {
FragmentTabProductBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tab_product, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setColorRecyclerView();
    }
    public void setColorRecyclerView() {
        List<ColorItem> colorItems = new ArrayList<>();
        colorItems.add(new ColorItem(R.color.pink));
        colorItems.add(new ColorItem(R.color.blue));
        colorItems.add(new ColorItem(R.color.black));
        colorItems.add(new ColorItem(R.color.white));
        colorItems.add(new ColorItem(R.color.rose));
        colorItems.add(new ColorItem(R.color.orange));
        colorItems.add(new ColorItem(R.color.green));
        colorItems.add(new ColorItem(R.color.gray));


        ColorAdapter colorAdapter = new ColorAdapter(getContext(),colorItems );
        binding.selectColorRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.selectColorRV.setAdapter(colorAdapter);


    }
}