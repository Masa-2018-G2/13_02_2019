package com.sheygam.masa_2018_g2_13_02_19;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> list;

    public MessageAdapter() {
        list = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 200; i++) {
            int type = rnd.nextInt(2);
            list.add(new Message("Message " + i,type));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if(type == 0){
            View view  = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.left_row,viewGroup,false);
            return new LeftViewHolder(view);
        }
        View view  = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.right_row,viewGroup,false);
        return new RightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message msg = list.get(i);
        if(msg.getType() == 0){
            ((LeftViewHolder)viewHolder).messageTxt.setText(msg.getText());
        }else{
            ((RightViewHolder)viewHolder).messageTxt.setText(msg.getText());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    class RightViewHolder extends RecyclerView.ViewHolder{
        TextView messageTxt;
        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTxt = itemView.findViewById(R.id.message_txt);
        }
    }

    class LeftViewHolder extends RecyclerView.ViewHolder{
        TextView messageTxt;
        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTxt = itemView.findViewById(R.id.message_txt);
        }
    }
}
