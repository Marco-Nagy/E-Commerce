package com.marco_nagy.e_commerce.ui.product.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.FragmentTabProductBinding;
import com.marco_nagy.e_commerce.ui.models.ColorItem;
import com.marco_nagy.e_commerce.ui.models.DataItem;
import com.marco_nagy.e_commerce.ui.models.SizesItem;
import com.marco_nagy.e_commerce.ui.product.adapters.ColorAdapter;
import com.marco_nagy.e_commerce.ui.product.adapters.SizeAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class TabProductFragment extends Fragment {
FragmentTabProductBinding binding;
    DataItem latestProduct, searchItem;


    public TabProductFragment( DataItem latestProduct, DataItem searchItem) {
        this.latestProduct = latestProduct;
        this.searchItem = searchItem;
    }

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
//        latestProduct = (DataItem) getIntent().getSerializableExtra("latestProduct");
//        searchItem = (DataItem) getIntent().getSerializableExtra("searchItems");

        setColorRecyclerView();
        setSizeRecyclerView();
    }
    public void setColorRecyclerView() {
        List<ColorItem> colorItems;
        if (searchItem != null){
            colorItems = searchItem.getColor();
        }else {
            colorItems = latestProduct.getColor();
        }

        ColorAdapter colorAdapter = new ColorAdapter(getContext(),colorItems );
        binding.selectColorRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.selectColorRV.setAdapter(colorAdapter);


    }
    public void setSizeRecyclerView() {
        List<SizesItem> sizeItems;
        if (searchItem != null){
            sizeItems = searchItem.getSizes();
        }else {
            sizeItems = latestProduct.getSizes();
        }


        SizeAdapter sizeAdapter = new SizeAdapter(getContext(),sizeItems );
        binding.selectSizeRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.selectSizeRV.setAdapter(sizeAdapter);


    }
}