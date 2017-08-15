package com.foney.lovespace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.foney.lovespace.adapter.ChatAdapter;
import com.foney.lovespace.entity.Chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by foney on 2017/8/15.
 */

public class TestActivity extends AppCompatActivity {

    private List<Chat> chats = new ArrayList<Chat>();
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private EditText chatMsgEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love_chat);
        ButterKnife.bind(this);
        initChats();
        chatMsgEditText = (EditText) findViewById(R.id.chat_msg_edit_text);
        recyclerView = (RecyclerView)findViewById(R.id.chat_msg_list_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chatAdapter = new ChatAdapter(chats);
        recyclerView.setAdapter(chatAdapter);
    }

    @OnClick(R.id.chat_send_button) void send() {
        String msg = chatMsgEditText.getText().toString();
        if(msg != null && "".equals(msg)) {
            Chat chat = new Chat();
            chat.setToCustomerId(456);
            chat.setFromCustomerId(123);
            chat.setCreateTime(new Date());
            chat.setContent(msg);
            chats.add(chat);
            chatAdapter.notifyItemInserted(chats.size() - 1);
            recyclerView.scrollToPosition(chats.size() - 1);
            chatMsgEditText.setText("");
        }
    }

    public void initChats() {
        Chat chat1 = new Chat();
        chat1.setContent("你好，你好你好，你好你好，你好你好，你好你好，你好你好，你好你好，你好");
        chat1.setCreateTime(new Date());
        chat1.setFromCustomerId(123);
        chat1.setToCustomerId(456);
        chats.add(chat1);

        Chat chat2 = new Chat();
        chat2.setContent("你好，你好你好，好，你好你好，你好你好，你好");
        chat2.setCreateTime(new Date());
        chat2.setFromCustomerId(456);
        chat2.setToCustomerId(123);
        chats.add(chat2);

        Chat chat3 = new Chat();
        chat3.setContent("你好，你好你好，你好你好，");
        chat3.setCreateTime(new Date());
        chat3.setFromCustomerId(123);
        chat3.setToCustomerId(456);
        chats.add(chat3);

        Chat chat4 = new Chat();
        chat4.setContent("你好，你好你好，你好你好，你好你好，你好你好，你好你好，你好你好，你好");
        chat4.setCreateTime(new Date());
        chat4.setFromCustomerId(123);
        chat4.setToCustomerId(456);
        chats.add(chat4);

        Chat chat5 = new Chat();
        chat5.setContent("你好，你好你好，你好你好，你好你好，你好你好，你好你好，，你好你好，你好你好，，你好你好，你好你好，你好你好，你好");
        chat5.setCreateTime(new Date());
        chat5.setFromCustomerId(456);
        chat5.setToCustomerId(123);
        chats.add(chat5);
    }
}
