package com.marco_nagy.e_commerce.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.FragmentCartBinding;
import com.marco_nagy.e_commerce.ui.cart.addModel.Data;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.DataItem;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.GetCartResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    FragmentCartBinding binding;
     String token = SharedPref.read(SharedPref.Token, null);
    List<DataItem> dataItemList;
    CartAdapter cartAdapter;
    Data data;
    private static final String TAG = "CartFragment";
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCart();
        binding.checkoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CheckoutActivity.class));
            }
        });

    }

    public void getCart() {

        AppNetworkBuilder.getClient().getCart(token).enqueue(new Callback<GetCartResponse>() {

            @Override
            public void onResponse(@NotNull Call<GetCartResponse> call, @NotNull Response<GetCartResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    dataItemList = response.body().getData();
                    setCartRecyclerView();

                }
            }

            @Override
            public void onFailure(@NotNull Call<GetCartResponse> call, Throwable t) {


            }
        });

    }

    public void setCartRecyclerView() {

        cartAdapter = new CartAdapter(dataItemList, getContext(), cartInterface);

        binding.cartRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.cartRV.setAdapter(cartAdapter);


    }

    CartInterface cartInterface = new CartInterface() {

        @Override
        public void onAddToCartClick(DataItem cartItem) {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);

            intent.putExtra("cartItem", cartItem);
            startActivity(intent);
        }
    };
}