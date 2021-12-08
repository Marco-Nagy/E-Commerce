package com.marco_nagy.e_commerce.ui.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.databinding.CartItemBinding;
import com.marco_nagy.e_commerce.ui.cart.addModel.AddResponse;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    List<DataItem> dataItemList;
    Context context;
    CartInterface cartInterface;
    private static final String TAG = "CartAdapter";
    public CartAdapter(List<DataItem> dataItemList, Context context, CartInterface cartInterface) {
        this.dataItemList = dataItemList;
        this.context = context;
        this.cartInterface = cartInterface;
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
        DataItem items= dataItemList.get(position);
        holder.binding.setItem(items);
        if(items.getProductId().getImages().isEmpty()){
            holder.binding.imageItem.setImageResource(R.mipmap.online_shopping_foreground);
        }
        else{
            Glide.with(context).load(items.getProductId().getImages().get(0).getImage()).placeholder(R.mipmap.online_shopping_foreground).into(holder.binding.imageItem);

        }
        holder.binding.itemTitle.setText(items.getProductId().getItemName());
        holder.binding.productPrice.setText(items.getProductId().getPrice());
        holder.binding.quantityText.setText(items.getQuantity());
        holder.binding.itemDetails.setText(items.getProductId().getDescription());
        holder.binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppNetworkBuilder.getClient().getAddQuantity(Integer.parseInt(items.getQuantity()),CartFragment.token).enqueue(new Callback<AddResponse>() {
                    @Override
                    public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {
                        if (response.isSuccessful()){
                            assert response.body() != null;
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: addBtn=> "+response.body().getData());
                            Log.i(TAG, "onResponse: addBtn=> "+response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddResponse> call, Throwable t) {

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
         CartItemBinding binding;

        public CartViewHolder(@NonNull @NotNull CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
