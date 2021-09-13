package com.example.e_commerce.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.MessageItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{
    List<MessageItem> messageItems;
    Context context;
    MessageInterface messageInterface;

    public MessagesAdapter(List<MessageItem> messageItems, Context context, MessageInterface messageInterface) {
        this.messageItems = messageItems;
        this.context = context;
        this.messageInterface = messageInterface;
    }

    @NonNull
    @NotNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MessagesAdapter.MessageViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessagesAdapter.MessageViewHolder holder, int position) {
        MessageItem items= messageItems.get(position);
        holder.binding.setItem(messageItems.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageInterface.onMessageClick(items);

            }
        });

    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        MessageItemBinding binding;

        public MessageViewHolder(@NonNull @NotNull MessageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
