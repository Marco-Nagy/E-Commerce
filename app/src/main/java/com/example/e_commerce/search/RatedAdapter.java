package com.example.e_commerce.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.RatedItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RatedAdapter extends RecyclerView.Adapter<RatedAdapter.RatedViewHolder> {
    List<RatedItems> ratedItems;
    Context context;

    public RatedAdapter(List<RatedItems> ratedItems, Context context) {
        this.ratedItems = ratedItems;
        this.context = context;
    }

    public int getItemsImage(RatedItems image) {
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
        holder.binding.setItem(ratedItems.get(position));
        holder.binding.productImage.setImageResource(getItemsImage(ratedItems.get(position)));
        holder.binding.ratingImage.setImageResource(R.drawable.rating_7);



    }

    @Override
    public int getItemCount() {
        return ratedItems.size();
    }


    public static class RatedViewHolder extends RecyclerView.ViewHolder {
        RatedItemBinding binding;

        public RatedViewHolder(@NonNull @NotNull RatedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
