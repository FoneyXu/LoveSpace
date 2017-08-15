package com.foney.lovespace.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foney.lovespace.R;
import com.foney.lovespace.entity.Chat;

import java.util.List;

import butterknife.BindView;

/**
 * Created by foney on 2017/8/15.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private List<Chat> chats;

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        //123代表当前用户
        if(chat.getFromCustomerId() == 123) {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftChatHeadImageView.setImageResource(R.drawable.story);
            holder.leftTimeTextView.setText(chat.getCreateTime().toString());
            holder.leftContentTextView.setText(chat.getContent());
        }else if(chat.getToCustomerId() == 123){
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightChatHeadImageView.setImageResource(R.drawable.person);
            holder.rightTimeTextView.setText(chat.getCreateTime().toString());
            holder.rightContentTextView.setText(chat.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;

        ImageView leftChatHeadImageView;
        TextView leftTimeTextView;
        TextView leftContentTextView;

        ImageView rightChatHeadImageView;
        TextView rightTimeTextView;
        TextView rightContentTextView;

        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_chat);
            leftChatHeadImageView = (ImageView) view.findViewById(R.id.left_chat_head_image_view);
            leftTimeTextView = (TextView)view.findViewById(R.id.left_chat_time_text_view);
            leftContentTextView = (TextView)view.findViewById(R.id.left_chat_content_text_view);

            rightLayout = (LinearLayout) view.findViewById(R.id.right_chat);
            rightChatHeadImageView = (ImageView) view.findViewById(R.id.right_chat_head_image_view);
            rightTimeTextView = (TextView)view.findViewById(R.id.right_chat_time_text_view);
            rightContentTextView = (TextView)view.findViewById(R.id.right_chat_content_text_view);
        }


    }

}
