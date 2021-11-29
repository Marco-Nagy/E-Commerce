package com.marco_nagy.e_commerce.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.CartItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    List<CartItems> cartItems;
    Context context;
    CartInterface cartInterface;

    public CartAdapter(List<CartItems> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    public CartAdapter(List<CartItems> cartItems, Context context, CartInterface cartInterface) {
        this.cartItems = cartItems;
        this.context = context;
        this.cartInterface = cartInterface;

    }
    public int getItemsImage(CartItems image) {
        return image.getImage();
    }
    @NonNull
    @NotNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return  new CartAdapter.CartViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter.CartViewHolder holder, int position) {
        CartItems items= cartItems.get(position);
        holder.binding.setItem(cartItems.get(position));
        holder.binding.imageItem.setImageResource(getItemsImage(cartItems.get(position)));
        holder.binding.addBtn.setImageResource(R.drawable.add_small);
        holder.binding.removeBtn.setImageResource(R.drawable.remove_small);


    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
         CartItemBinding binding;

        public CartViewHolder(@NonNull @NotNull CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
