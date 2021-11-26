package com.marco_nagy.e_commerce.product.fragments;

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
import com.marco_nagy.e_commerce.product.adapters.ColorAdapter;
import com.marco_nagy.e_commerce.product.ColorItem;
import com.marco_nagy.e_commerce.product.adapters.SizeAdapter;
import com.marco_nagy.e_commerce.product.SizeItem;

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
        setSizeRecyclerView();
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
    public void setSizeRecyclerView() {
        List<SizeItem> sizeItems = new ArrayList<>();
        sizeItems.add(new SizeItem("4.0"));
        sizeItems.add(new SizeItem("4.5"));
        sizeItems.add(new SizeItem("5.0"));
        sizeItems.add(new SizeItem("5.5"));
        sizeItems.add(new SizeItem("6.0"));
        sizeItems.add(new SizeItem("6.5"));
        sizeItems.add(new SizeItem("7.0"));
        sizeItems.add(new SizeItem("7.5"));
        sizeItems.add(new SizeItem("8.0"));
        sizeItems.add(new SizeItem("8.5"));
        SizeAdapter sizeAdapter = new SizeAdapter(getContext(),sizeItems );
        binding.selectSizeRV.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.selectSizeRV.setAdapter(sizeAdapter);


    }
}