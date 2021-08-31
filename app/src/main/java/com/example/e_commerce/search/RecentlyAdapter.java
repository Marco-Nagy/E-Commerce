package com.example.e_commerce.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.ProductItems;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.RecentlyItemBinding;



import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecentlyAdapter extends RecyclerView.Adapter<RecentlyAdapter.RecentlyViewHolder> {
    List<ProductItems> productItems;
    Context context;

    public RecentlyAdapter(List<ProductItems> productItems, Context context) {
        this.productItems = productItems;
        this.context = context;
    }

    public int getItemsImage(ProductItems image) {
        return image.getImage();
    }

    @NonNull
    @NotNull
    @Override
    public RecentlyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecentlyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recently_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecentlyAdapter.RecentlyViewHolder holder, int position) {
        holder.binding.setItem(productItems.get(position));
        holder.binding.productImage.setImageResource(getItemsImage(productItems.get(position)));
        holder.binding.productPrice.setText(productItems.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public static class RecentlyViewHolder extends RecyclerView.ViewHolder {
        RecentlyItemBinding binding;

        public RecentlyViewHolder(@NonNull @NotNull RecentlyItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
