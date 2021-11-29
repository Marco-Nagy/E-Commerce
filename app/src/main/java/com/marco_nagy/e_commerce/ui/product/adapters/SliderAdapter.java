package com.marco_nagy.e_commerce.ui.product.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.SliderItemBinding;
import com.marco_nagy.e_commerce.ui.models.ImagesItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
    List<ImagesItem> images;



    public SliderAdapter(List<ImagesItem> images) {
        this.images = images;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.slider_item, parent, false));
    }


    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {


        Glide.with(viewHolder.itemView.getContext())
                .load(images.get(position).getImage())
                .placeholder(R.drawable._4)
                .into(viewHolder.binding.imageView);

    }


    @Override
    public int getCount() {
        return images.size();
    }

    public static class SliderViewHolder extends SliderViewAdapter.ViewHolder{
        SliderItemBinding binding;

        public SliderViewHolder(SliderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
