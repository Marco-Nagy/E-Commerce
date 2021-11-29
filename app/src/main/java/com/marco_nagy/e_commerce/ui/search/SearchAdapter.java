package com.marco_nagy.e_commerce.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.SearchItemBinding;
import com.marco_nagy.e_commerce.ui.models.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RatedViewHolder> {
    List<DataItem> searchItems;
    Context context;
    SearchInterface searchInterface;

    public SearchAdapter(List<DataItem> searchItems, Context context, SearchInterface searchInterface) {
        this.searchItems = searchItems;
        this.context = context;
        this.searchInterface = searchInterface;
    }
//    public int getItemsImage(ImagesData image) {
//        return image.getImage();
//    }

    @NonNull
    @NotNull
    @Override
    public RatedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RatedViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchAdapter.RatedViewHolder holder, int position) {
        DataItem items= searchItems.get(position);
        holder.binding.setItem(items);
        holder.binding.productTitle.setText(items.getItemName());
        holder.binding.productPrice.setText(items.getPrice());
        if(items.getImages().isEmpty()){
            holder.binding.productImage.setImageResource(R.mipmap.online_shopping_foreground);
        }
        else{
            Glide.with(context).load(items.getImages().get(0).getImage()).placeholder(R.mipmap.online_shopping_foreground).into(holder.binding.productImage);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               searchInterface.onSearchProductClick(items);

            }
        });
        holder.binding.productImage.setAnimation(AnimationUtils.loadAnimation(context,R.anim.animation));
        holder.binding.productCardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.bottom_to_top));


    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }


    public static class RatedViewHolder extends RecyclerView.ViewHolder {
        SearchItemBinding binding;

        public RatedViewHolder(@NonNull @NotNull SearchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
