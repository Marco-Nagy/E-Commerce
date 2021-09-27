package com.marco_nagy.e_commerce.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.shop.ShopActivity;
import com.marco_nagy.e_commerce.databinding.ActivityConversationBinding;
import com.marco_nagy.e_commerce.product.ProductActivity;

public class ConversationActivity extends AppCompatActivity {
    ActivityConversationBinding binding;
    ProductActivity.TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_conversation);
       binding.imageShop.setOnClickListener(v->
               startActivity(new Intent(getApplicationContext(),ShopActivity.class)));
    }

}