package com.example.e_commerce.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.LatestItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.LatestViewHolder> {
    List<LatestItems> latestItems;
    Context context;

    public LatestAdapter(List<LatestItems> latestItems, Context context) {
        this.latestItems = latestItems;
        this.context = context;
    }

    public int getItemsImage(LatestItems image) {
        return image.getImage();
    }

    @NonNull
    @NotNull
    @Override
    public LatestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new LatestViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.latest_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LatestAdapter.LatestViewHolder holder, int position) {
        holder.binding.setItem(latestItems.get(position));
        holder.binding.latestImage.setImageResource(getItemsImage(latestItems.get(position)));
        holder.binding.seeMoreBtn.setImageResource(R.drawable.see_more);
        holder.binding.textSeeMore.setText(R.string.see_more);


    }

    @Override
    public int getItemCount() {
        return latestItems.size();
    }


    public static class LatestViewHolder extends RecyclerView.ViewHolder {
        LatestItemBinding binding;

        public LatestViewHolder(@NonNull @NotNull LatestItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}