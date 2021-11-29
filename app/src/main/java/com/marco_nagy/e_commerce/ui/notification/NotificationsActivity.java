package com.marco_nagy.e_commerce.ui.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ActivityNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
ActivityNotificationsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_notifications);
        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setMessagesRecyclerView();
    }

    public void setMessagesRecyclerView() {
        List<NotificationItem> notificationItems = new ArrayList<>();
        notificationItems.add(new NotificationItem(R.drawable.icon_market,"Smiley’s Store marked your order #1982984 as shipped.","9:20 AM"));
        notificationItems.add(new NotificationItem(R.drawable.icon_order,"Smiley’s Store marked your order #1982984 as shipped.","9:20 AM"));
        notificationItems.add(new NotificationItem(R.drawable.icon_offer,"Smiley’s Store marked your order #1982984 as shipped.","9:20 AM"));
        notificationItems.add(new NotificationItem(R.drawable.icon_sale,"Smiley’s Store marked your order #1982984 as shipped.","9:20 AM"));

        NotificationAdapter notificationAdapter = new NotificationAdapter(notificationItems, this);
        binding.notificationRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.notificationRV.setAdapter(notificationAdapter);

    }
}