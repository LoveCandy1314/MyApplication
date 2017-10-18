package com.example.administrator.myapplication.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private List<String> data ;
    private Context context;

    public MyAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context,R.layout.recycle_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.setTextView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class  MyViewHolder extends  RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycle_item_textView);
        }
        public void setTextView(int position){
            textView.setText(data.get(position));
        }
    }
}
