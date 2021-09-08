package com.example.e_commerce.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_commerce.ProductItems;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ActivityMessagesBinding;
import com.example.e_commerce.search.RatedAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {
    ActivityMessagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messages);
        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        setMessagesRecyclerView();
    }
    public void setMessagesRecyclerView() {
        List<MessageItem> messageItems = new ArrayList<>();
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));
        messageItems.add(new MessageItem(R.string.smiley_s_store,R.string.message_body,R.string.ss,"9:20 AM"));

        MessagesAdapter messagesAdapter = new MessagesAdapter(messageItems, this,messageInterface);
        binding.messageRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.messageRV.setAdapter(messagesAdapter);

    }
    MessageInterface messageInterface = new MessageInterface() {
        @Override
        public void onMessageClick(MessageItem messageItem) {
            startActivity(new Intent(getApplicationContext(),ConversationActivity.class));
        }
    };
}