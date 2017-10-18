package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.Interface.MyOnScrollListener;
import com.example.administrator.myapplication.Interface.ObservableHorizontalScrollView;
import com.example.administrator.myapplication.Interface.ObservableHorizontalScrollView.ScrolViewListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRvLayer;
    private RecyclerView mRvRoom;
    private ObservableHorizontalScrollView mSvRoom;
    private List mData = new ArrayList();
    String TAG = "lichao";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"OnCreate............");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        setSyncScrollListener();
    }

    private void setSyncScrollListener() {
        mRvLayer.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){
            private  int mLastY;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.i(TAG,"in mRvLayer onInterceptTouchEvent rv.getScrollState() is " + rv.getScrollState() );
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE){
                    onTouchEvent(rv,e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction()==MotionEvent.ACTION_DOWN ){
                    mLastY= rv.getScrollY();
                    Log.i(TAG,"in mRvLayer onTouchEvent mLastY is " + mLastY);
                    rv.addOnScrollListener(mLayerOSL);
                }else {
                    if (e.getAction() == MotionEvent.ACTION_DOWN && rv.getScrollY() == mLastY){
                        rv.removeOnScrollListener(mLayerOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        mRvRoom.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){
            private  int mLastY;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.i(TAG,"in mRvRoom onInterceptTouchEvent rv.getScrollState() is " + rv.getScrollState());
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN && mRvLayer.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    Log.i(TAG,"in mRvRoom onTouchEvent mLastY is " + mLastY);
                    rv.addOnScrollListener(mRoomOSL);
                } else {
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mRoomOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        mSvRoom.setScrolViewListener(new ScrolViewListener(){
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                mRvLayer.removeOnScrollListener(mLayerOSL);
                mRvRoom.removeOnScrollListener(mRoomOSL);
            }
        });
    }

    private void initData() {
        int layerCount = 30;
        for (int i=0;i<layerCount; i++){
            mData.add(new Object());
        }
        mRvLayer.setAdapter(new BaseQuickAdapter<Object, BaseViewHolder>(R.layout.item_room, mData) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        });
        mRvRoom.setAdapter(new BaseQuickAdapter<Object, BaseViewHolder>(R.layout.item_layer, mData) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                LinearLayout llLayer = helper.getView(R.id.ll_layer);
                llLayer.removeAllViews();
                for (int i=0;i<5;i++){
                    View room = View.inflate(MainActivity.this,R.layout.item_room,null);
                    View line = new View(MainActivity.this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1,LinearLayout.LayoutParams.MATCH_PARENT);
                    line.setLayoutParams(params);
                    line.setBackgroundColor(Color.parseColor("#f5f5f5"));
                    llLayer.addView(room);
                    llLayer.addView(line);
                }
            }
        });
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRvLayer = (RecyclerView) findViewById(R.id.rv_layer);
        mRvRoom = (RecyclerView) findViewById(R.id.rv_room);
        mSvRoom = (ObservableHorizontalScrollView) findViewById(R.id.sv_room);
        mRvLayer.setLayoutManager(new LinearLayoutManager(this));
        mRvLayer.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.parseColor("#f5f5f5")).size(1).build());

        mRvRoom.setLayoutManager(new LinearLayoutManager(this));
        mRvRoom.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.parseColor("#f5f5f5")).size(1).build());
    }

    private  final  RecyclerView.OnScrollListener mLayerOSL = new MyOnScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mRvRoom.scrollBy(dx,dy);
        }
    };
    private final  RecyclerView.OnScrollListener mRoomOSL = new MyOnScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mRvLayer.scrollBy(dx,dy);
        }
    };

}
