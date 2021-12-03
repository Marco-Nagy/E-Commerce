package com.marco_nagy.e_commerce.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.marco_nagy.e_commerce.MainActivity;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.FragmentMoreBinding;

import org.jetbrains.annotations.NotNull;


public class MoreFragment extends Fragment {
FragmentMoreBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_more, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.clearAll();
                startActivity( new Intent(getContext(), MainActivity.class));
                }
        });
    }
}