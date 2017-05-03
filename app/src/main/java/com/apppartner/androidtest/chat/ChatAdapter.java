package com.apppartner.androidtest.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidtest.R;
import com.apppartner.androidtest.chat.ChatLogMessageModel;
import com.apppartner.androidtest.Round.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatLogMessageModel> chatLogMessageModelList;
    private Context context;

    public ChatAdapter(Context context) {
        this.context = context;
        chatLogMessageModelList = new ArrayList<>();
    }

    public void setChatLogMessageModelList(List<ChatLogMessageModel> chatLogMessageModelList) {
        this.chatLogMessageModelList = chatLogMessageModelList;
        notifyDataSetChanged();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_chat, parent, false);

        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder viewHolder, int position) {
        ChatLogMessageModel chatLogMessageModel = chatLogMessageModelList.get(position);

        viewHolder.messageTextView.setText(chatLogMessageModel.getMessage());
        Picasso.with(context).load(chatLogMessageModel.getAvatarUrl()).resize(50, 50)
                .centerCrop().transform(new RoundedTransformation(100, 0)).into(viewHolder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return chatLogMessageModelList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatarImageView)
        ImageView avatarImageView;
        @BindView(R.id.messageTextView)
        TextView messageTextView;

        public ChatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
