package com.example.e_commerce.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ProductItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    List<ProductItems> productItems;
    Context context;
    ProductInterface productInterface;

    public ProductsAdapter(List<ProductItems> productItems, Context context, ProductInterface productInterface) {
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
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsAdapter.ProductViewHolder holder, int position) {
        ProductItems products= productItems.get(position);
        holder.binding.setItem(productItems.get(position));
        holder.binding.productImage.setImageResource(getItemsImage(productItems.get(position)));
        holder.binding.productPrice.setText(productItems.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productInterface.onProductClick(products);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding binding;

        public ProductViewHolder(@NonNull @NotNull ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
