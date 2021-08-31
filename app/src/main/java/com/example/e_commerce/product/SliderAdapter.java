package com.example.e_commerce.product;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.SliderItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
int []images;

    public SliderAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.slider_item, parent, false));
    }


    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        viewHolder.binding.imageView.setImageResource(images[position]);

    }


    @Override
    public int getCount() {
        return images.length;
    }

    public static class SliderViewHolder extends SliderViewAdapter.ViewHolder{
        SliderItemBinding binding;

        public SliderViewHolder(SliderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
