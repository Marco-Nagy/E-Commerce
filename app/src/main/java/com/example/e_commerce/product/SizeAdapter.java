package com.example.e_commerce.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.SizeItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeViewHolder>{
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private List<SizeItem> sizeItems;
    public static int checkedPositionSize =-1;

    public SizeAdapter(Context context, List<SizeItem> sizeItems) {
        this.context = context;
        this.sizeItems = sizeItems;
    }

    public void setSizeItems(List<SizeItem> sizeItems) {
        this.sizeItems = sizeItems;
        this.sizeItems= new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public SizeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new SizeViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.size_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SizeAdapter.SizeViewHolder holder, int position) {
        holder.bind(sizeItems.get(position));

        holder.itemView.setOnClickListener(v -> {
            holder.binding.sizeCheck.setTextColor(Color.parseColor("#FF6969"));
            if (checkedPositionSize != holder.getAdapterPosition()){
                notifyItemChanged(checkedPositionSize);
                checkedPositionSize = holder.getAdapterPosition();
            }else if(checkedPositionSize == holder.getAdapterPosition()){
                notifyItemChanged(checkedPositionSize);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sizeItems.size();
    }

    public static class SizeViewHolder extends RecyclerView.ViewHolder{
        SizeItemBinding binding;
         Context context;

        public SizeViewHolder(@NonNull @NotNull SizeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind( SizeItem sizeItem){
            if (checkedPositionSize==-1){
                binding.sizeCheck.setTextColor(Color.parseColor("#515C6F"));
            }else if(checkedPositionSize== getAdapterPosition()){
                binding.sizeCheck.setTextColor(Color.parseColor("#FF6969"));
            }else {
                binding.sizeCheck.setTextColor(Color.parseColor("#515C6F"));
            }

            binding.sizeCheck.setText(sizeItem.getSize());
        }

    }
    public SizeItem getSelectedSize(){
        if (checkedPositionSize!= -1){
            return sizeItems.get(checkedPositionSize);
        }
        return null;
    }
}
