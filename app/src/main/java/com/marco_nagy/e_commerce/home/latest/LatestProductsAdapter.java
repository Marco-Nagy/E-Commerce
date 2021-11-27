package com.marco_nagy.e_commerce.home.latest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.LatestProductBinding;
import com.marco_nagy.e_commerce.home.latest.models.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LatestProductsAdapter extends RecyclerView.Adapter<LatestProductsAdapter.LatestProductViewHolder> {
    List<DataItem> latestProducts;
    Context context;
    LatestProductInterface latestProductInterface;

    public LatestProductsAdapter(List<DataItem> latestProducts, Context context, LatestProductInterface latestProductInterface) {
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

    private static final String TAG = "LatestProductsAdapter";
    @Override
    public void onBindViewHolder(@NonNull @NotNull LatestProductViewHolder holder, int position) {
        DataItem latestProduct = latestProducts.get(position);
        holder.binding.setItem(latestProduct);
        Log.i(TAG, "onBindViewHolder: " + latestProduct.getImages().size());
        if(latestProduct.getImages().isEmpty()){
            holder.binding.productImage.setImageResource(R.drawable.backpack);
        }
        else{
            Glide.with(context).load(latestProduct.getImages().get(0).getImage()).placeholder(R.drawable.backpack).into(holder.binding.productImage);

        }
        holder.binding.productImage.setAnimation(AnimationUtils.loadAnimation(context,R.anim.animation));
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
