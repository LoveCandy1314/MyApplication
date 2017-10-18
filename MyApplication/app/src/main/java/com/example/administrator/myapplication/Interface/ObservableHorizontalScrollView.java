package com.example.administrator.myapplication.Interface;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2017/7/18.
 */

public class ObservableHorizontalScrollView extends HorizontalScrollView {
    private  ScrolViewListener scrolViewListener = null;
    public ObservableHorizontalScrollView(Context context) {
        super(context);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void setScrolViewListener(ScrolViewListener scrolViewListener){
        this.scrolViewListener = scrolViewListener;
    }
    public ObservableHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrolViewListener!=null){
            scrolViewListener.onScrollChanged(this,l,t,oldl,oldt);
        }
    }

    public interface  ScrolViewListener{
        void onScrollChanged(ObservableHorizontalScrollView scrollView,int x,int y,int oldx, int oldy);
    }
}
