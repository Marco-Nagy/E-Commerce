package com.marco_nagy.e_commerce.ui.product.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ColorItemBinding;
import com.marco_nagy.e_commerce.ui.models.ColorItem;
import com.marco_nagy.e_commerce.ui.product.ColorInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    private Context context;
    private List<ColorItem> colorItems;
    public static int checkedPosition = -1;
    ColorInterface colorInterface;


    public ColorAdapter(Context context, List<ColorItem> colorItems, ColorInterface colorInterface) {
        this.context = context;
        this.colorItems = colorItems;
        this.colorInterface =colorInterface;
    }

    public void setColorItems(ArrayList<ColorItem> colorItems) {
        this.colorItems = colorItems;
        this.colorItems = new ArrayList<>();
        notifyDataSetChanged();
    }


    public String getItemsColor(ColorItem color) {
        return color.getColorId();
    }


    @NonNull
    @NotNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ColorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.color_item, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ColorAdapter.ColorViewHolder holder, int position) {
        holder.bind(colorItems.get(position));
        int color = Color.parseColor(colorItems.get(position).getColorId());
        holder.binding.colorCardView.setCardBackgroundColor(color);
        holder.binding.colorCardView.setRadius(150);
        //holder.binding.colorCardView.setBackgroundColor(color);
        holder.itemView.setOnClickListener(v -> {
            holder.binding.imageCheck.setVisibility(View.VISIBLE);
            if (checkedPosition != holder.getAdapterPosition()) {
                notifyItemChanged(checkedPosition);
                checkedPosition = holder.getAdapterPosition();
               colorInterface.onColorSelect(colorItems.get(position));
            } else if (checkedPosition == holder.getAdapterPosition()) {
                notifyItemChanged(checkedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorItems.size();
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder {
        ColorItemBinding binding;

        public ColorViewHolder(@NonNull @NotNull ColorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ColorItem colorItem) {

            if (checkedPosition == -1) {
                binding.imageCheck.setVisibility(View.GONE);
            } else if (checkedPosition == getAdapterPosition()) {
                binding.imageCheck.setVisibility(View.VISIBLE);
            } else {
                binding.imageCheck.setVisibility(View.GONE);
            }
            binding.colorCardView.setCardBackgroundColor(Color.parseColor(colorItem.getColorId()));
            binding.colorCardView.setRadius(150);

            ;
        }

    }

    public ColorItem getSelectedColor() {
        if (checkedPosition != -1) {
            return colorItems.get(checkedPosition);
        }
        return null;
    }
}
