package com.example.e_commerce.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.ProductItems;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.RatedItemBinding;
import com.example.e_commerce.product.ProductInterface;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RatedAdapter extends RecyclerView.Adapter<RatedAdapter.RatedViewHolder> {
    List<ProductItems> productItems;
    Context context;
    ProductInterface productInterface;

    public RatedAdapter(List<ProductItems> productItems, Context context, ProductInterface productInterface) {
        this.productItems = productItems;
        this.context = context;
        this.productInterface = productInterface;
    }

    public int getItemsImage(ProductItems image) {
        return image.getImage();
    }

    @NonNull
    @NotNull
    @Override
    public RatedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RatedViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rated_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RatedAdapter.RatedViewHolder holder, int position) {
        ProductItems items= productItems.get(position);
        holder.binding.setItem(productItems.get(position));
        holder.binding.productImage.setImageResource(getItemsImage(productItems.get(position)));
        holder.binding.ratingImage.setImageResource(R.drawable.rating_7);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productInterface.onProductClick(items);

            }
        });



    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }


    public static class RatedViewHolder extends RecyclerView.ViewHolder {
        RatedItemBinding binding;

        public RatedViewHolder(@NonNull @NotNull RatedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
