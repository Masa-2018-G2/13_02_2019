package com.sheygam.masa_2018_g2_13_02_19;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<User> list = new ArrayList<>();
    private MyRowListener listener;

    public MyAdapter(MyRowListener listener) {
        for (int i = 0; i < 100; i++) {
            list.add(new User("User " + i, "user"+i+"@mail.com"));
        }

        this.listener = listener;
    }


    public void add(User user){
        list.add(2,user);
        notifyItemInserted(2);
    }

    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
//        Log.d("MY_TAG", "onCreateViewHolder: ");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_row,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
//        Log.d("MY_TAG", "onBindViewHolder: ");
        User user = list.get(position);
        myViewHolder.nameTxt.setText(user.getName());
        myViewHolder.emailTxt.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void move(int from, int to) {
        list.add(to,list.remove(from));

        notifyItemMoved(from, to);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView nameTxt, emailTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name_txt);
            emailTxt = itemView.findViewById(R.id.email_txt);
            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(listener!=null){
                listener.onLongClick(getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    interface MyRowListener{
        void onClick(int position);
        void onLongClick(int position);
    }
}
