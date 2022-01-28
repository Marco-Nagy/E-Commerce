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
import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.CartItemBinding;
import com.marco_nagy.e_commerce.ui.cart.addModel.AddResponse;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.DataItem;
import com.marco_nagy.e_commerce.ui.cart.removeModel.RemoveResponse;
import com.marco_nagy.e_commerce.ui.cart.subModel.SubResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<DataItem> dataItemList;
    Context context;
    CartInterface cartInterface;
    String token = SharedPref.read(SharedPref.Token, null);
    int quantity;
    String strQuantity;
    double totalAmount =0.0;
    double amount;
    String itemPrice;
    String itemQuantity;
    private static final String TAG = "CartAdapter";
    CartFragment cartFragment = new CartFragment();
    public CartAdapter(List<DataItem> dataItemList, Context context, CartInterface cartInterface) {
        this.dataItemList = dataItemList;
        this.context = context;
        this.cartInterface = cartInterface;
    }

    @NonNull
    @NotNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CartAdapter.CartViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter.CartViewHolder holder, int position) {
        DataItem items = dataItemList.get(holder.getAdapterPosition());
        holder.binding.setItem(items);
        if (items.getProductId().getImages().isEmpty()) {
            holder.binding.imageItem.setImageResource(R.mipmap.online_shopping_foreground);
        } else {
            Glide.with(context).load(items.getProductId().getImages().get(0).getImage()).placeholder(R.mipmap.online_shopping_foreground).into(holder.binding.imageItem);

        }
        holder.binding.itemTitle.setText(items.getProductId().getItemName());

        itemPrice =items.getProductId().getPrice();
        holder.binding.productPrice.setText(itemPrice);

        itemQuantity =items.getQuantity();
        holder.binding.quantityText.setText(itemQuantity);

        holder.binding.itemDetails.setText(items.getProductId().getDescription());
        holder.binding.addBtn.setOnClickListener(v ->
                AppNetworkBuilder.getClient().getAddQuantity(items.getProductId().getItemId(), token).enqueue(new Callback<AddResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<AddResponse> call, @NotNull Response<AddResponse> response) {
                        if (response.isSuccessful()) {

                            assert response.body() != null;
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: addBtn=> " + response.body().getData());
                            Log.i(TAG, "onResponse: addBtn=> " + response.body().getMessage());
                            quantity = response.body().getData().getQuantity();
                            strQuantity = Integer.toString(quantity);
                            dataItemList.get(holder.getAdapterPosition()).setQuantity(String.valueOf(quantity));
                            holder.binding.quantityText.setText(strQuantity);
                            cartInterface.onUpdateCart();

                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<AddResponse> call, @NotNull Throwable t) {

                    }
                }));

        holder.binding.subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity <= 1) {
                    return;
                }
                AppNetworkBuilder.getClient().getSubQuantity(items.getProductId().getItemId(), token).enqueue(new Callback<SubResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<SubResponse> call, @NotNull Response<SubResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            quantity = response.body().getData().getQuantity();
                            strQuantity = Integer.toString(quantity);
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: addBtn=> " + response.body().getData());
                            Log.i(TAG, "onResponse: addBtn=> " + response.body().getMessage());
                            holder.binding.quantityText.setText(strQuantity);
                            dataItemList.get(holder.getAdapterPosition()).setQuantity(String.valueOf(quantity));
                            cartInterface.onUpdateCart();
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<SubResponse> call, @NotNull Throwable t) {

                    }
                });
            }
        });

        holder.binding.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + items.getProductId().getItemId()+ token);
                AppNetworkBuilder.getClient().removeItem(items.getProductId().getItemId(), token).enqueue(new Callback<RemoveResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<RemoveResponse> call, @NotNull Response<RemoveResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;


                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: removed Item " + response.body().getData());

                            dataItemList.remove(holder.getAdapterPosition());
                            CartAdapter.this.notifyItemRemoved(holder.getAdapterPosition());

                            cartInterface.onUpdateCart();
                        } else {
                            assert response.errorBody() != null;
                            RemoveResponse message = new Gson().fromJson(response.errorBody().charStream(), RemoveResponse.class);

                            Log.i(TAG, "onResponse: removed Item " + message.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<RemoveResponse> call, @NotNull Throwable t) {
                        Log.i(TAG, "onFailure: remove " + t.getLocalizedMessage());
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
