package com.wyh.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ChatHolder> {

    private static final int ITEM_TYPE_RECEIVER = 0x1;
    private static final int ITEM_TYPE_SENDER = 0x1;

    private LayoutInflater inflater;

    private List<Message> messages;

    public ChatRecyclerAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatHolder(inflater.inflate(R.layout.item_chat_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        if (messages.get(position).getType() == 0) {
            holder.setReceiverView();
            holder.tv_receive.setText(messages.get(position).getContent());
        } else {
            holder.setSendView();
            holder.tv_send.setText(messages.get(position).getContent());
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getType() == 0) {
            return ITEM_TYPE_RECEIVER;
        } else {
            return ITEM_TYPE_SENDER;
        }
    }

    protected final static class ChatHolder extends RecyclerView.ViewHolder {

        public TextView tv_receive;
        public TextView tv_send;
        public TextView tv_time;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            tv_receive = itemView.findViewById(R.id.tv_receive);
            tv_send = itemView.findViewById(R.id.tv_send);
            tv_time = itemView.findViewById(R.id.tv_time);
        }

        private void setReceiverView() {
            tv_receive.setVisibility(View.VISIBLE);
            tv_send.setVisibility(View.GONE);
            tv_time.setVisibility(View.GONE);
        }

        private void setSendView() {
            tv_receive.setVisibility(View.GONE);
            tv_send.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.GONE);
        }
    }

}
