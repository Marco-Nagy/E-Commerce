package com.marco_nagy.e_commerce.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.CategoriesItemBinding;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    List<CategoryItems> items;
    Context context;
    public CategoriesAdapter(List<CategoryItems> items, Context context) {
        this.items = items;
        this.context = context;
    }


    public int getItemsImage(CategoryItems image){
        return image.getImage();
    }

    @NonNull
    @NotNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.categories_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriesAdapter.CategoriesViewHolder holder, int position) {

        holder.binding.setItem(items.get(position));
        holder.binding.categoryImage.setImageResource(getItemsImage(items.get(position)));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{
CategoriesItemBinding binding;
     public CategoriesViewHolder(@NonNull @NotNull CategoriesItemBinding binding) {
         super(binding.getRoot());
         this.binding= binding;
     }
 }

}
