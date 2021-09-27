package com.marco_nagy.e_commerce.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.MessageItemBinding;
import com.marco_nagy.e_commerce.databinding.NotificationItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{
    List<NotificationItem> notificationItems;
    Context context;

    public NotificationAdapter(List<NotificationItem> notificationItems, Context context) {
        this.notificationItems = notificationItems;
        this.context = context;
    }

    public int getItemsImage(NotificationItem image){
        return image.getImage();
    }
    @NonNull
    @NotNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.notification_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationAdapter.NotificationViewHolder holder, int position) {
        NotificationItem items= notificationItems.get(position);
        holder.binding.setItem(notificationItems.get(position));
        holder.binding.notificationImage.setImageResource(getItemsImage(notificationItems.get(position)));

    }

    @Override
    public int getItemCount() {
        return notificationItems.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        NotificationItemBinding binding;

        public NotificationViewHolder(@NonNull @NotNull NotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
