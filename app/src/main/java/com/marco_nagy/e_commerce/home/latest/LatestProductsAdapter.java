package com.marco_nagy.e_commerce.home.latest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.LatestProductBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LatestProductsAdapter extends RecyclerView.Adapter<LatestProductsAdapter.LatestProductViewHolder> {
    List<LatestProduct> latestProducts;
    Context context;
    LatestProductInterface latestProductInterface;

    public LatestProductsAdapter(List<LatestProduct> latestProducts, Context context, LatestProductInterface latestProductInterface) {
        this.latestProducts = latestProducts;
        this.context = context;
        this.latestProductInterface = latestProductInterface;
    }
//    public int getItemsImage(ProductItems image) {
//        return image.getImage();
//    }

    @NonNull
    @NotNull
    @Override
    public LatestProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new LatestProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.latest_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LatestProductViewHolder holder, int position) {
        LatestProduct latestProduct = latestProducts.get(position);
        holder.binding.setItem(latestProducts.get(position));
        holder.binding.productImage.setImageResource(R.drawable.backpack);
        //holder.binding.productImage.setImageResource(getItemsImage(latestData.get(position)));
        holder.binding.latestProductPrice.setText(latestProducts.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latestProductInterface.onLatestProductClick(latestProduct);

            }
        });
    }

    @Override
    public int getItemCount() {
        return latestProducts.size();
    }

    public static class LatestProductViewHolder extends RecyclerView.ViewHolder {
        LatestProductBinding binding;


        public LatestProductViewHolder(@NonNull @NotNull  LatestProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
