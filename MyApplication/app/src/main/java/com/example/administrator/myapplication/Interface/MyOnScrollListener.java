package com.example.administrator.myapplication.Interface;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MyOnScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == recyclerView.SCROLL_STATE_IDLE){
            recyclerView.removeOnScrollListener(this);
        }
    }
}
