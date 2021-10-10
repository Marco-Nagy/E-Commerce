package com.marco_nagy.e_commerce.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ColorItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
private Context context;
private List<ColorItem> colorItems;
public static int checkedPosition =-1;

    public ColorAdapter(Context context, List<ColorItem> colorItems) {
        this.context = context;
        this.colorItems = colorItems;
    }

    public void setColorItems(ArrayList<ColorItem> colorItems) {
        this.colorItems = colorItems;
        this.colorItems = new ArrayList<>();
        notifyDataSetChanged();
    }


    public int getItemsColor(ColorItem color){
        return color.getColor();
    }

    @NonNull
    @NotNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ColorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.color_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ColorAdapter.ColorViewHolder holder, int position) {
        holder.bind(colorItems.get(position));
        holder.binding.colorCardView.setImageResource(getItemsColor(colorItems.get(position)));
        holder.itemView.setOnClickListener(v -> {
            holder.binding.imageCheck.setVisibility(View.VISIBLE);
           if (checkedPosition != holder.getAdapterPosition()){
               notifyItemChanged(checkedPosition);
               checkedPosition = holder.getAdapterPosition();
           }else if(checkedPosition == holder.getAdapterPosition()){
               notifyItemChanged(checkedPosition);
           }
        });
    }

    @Override
    public int getItemCount() {
        return colorItems.size();
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder{
        ColorItemBinding binding;

        public ColorViewHolder(@NonNull @NotNull ColorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind( ColorItem colorItem){
            if (checkedPosition==-1){
                binding.imageCheck.setVisibility(View.GONE);
            }else if(checkedPosition== getAdapterPosition()){
                binding.imageCheck.setVisibility(View.VISIBLE);
            }else {
                binding.imageCheck.setVisibility(View.GONE);
            }
            binding.colorCardView.setImageResource(colorItem.getColor());
        }

    }
    public ColorItem getSelectedColor(){
        if (checkedPosition!= -1){
            return colorItems.get(checkedPosition);
        }
        return null;
    }
}
