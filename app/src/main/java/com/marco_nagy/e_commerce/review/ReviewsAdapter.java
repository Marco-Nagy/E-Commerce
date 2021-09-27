package com.marco_nagy.e_commerce.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ReviewItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>{
    List<ReviewItem> reviewItems;
    Context context;

    public ReviewsAdapter(List<ReviewItem> reviewItems, Context context) {
        this.reviewItems = reviewItems;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ReviewsAdapter.ReviewViewHolder holder, int position) {
        ReviewItem items= reviewItems.get(position);
        holder.binding.setItem(reviewItems.get(position));
        holder.binding.rateImage.setImageResource(R.drawable.rating_3);

    }

    @Override
    public int getItemCount() {
        return reviewItems.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ReviewItemBinding binding;

        public ReviewViewHolder(@NonNull @NotNull ReviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
