package com.qingchunlicai.tickview.StatusView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.qingchunlicai.tickview.R;


/**
 * Created by fatJiang on 2018/6/11.
 */

public class StatusView extends RelativeLayout{

    private Context mContext;
    private TickView tickView;
    private CrossView crossView;
    private ProgressWheel mProgressWheel;

    public StatusView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){
        LayoutInflater.from(mContext).inflate(R.layout.view_status,this);
        tickView = (TickView)findViewById(R.id.tick_view);
        crossView = (CrossView)findViewById(R.id.cross_view);
        mProgressWheel = (ProgressWheel)findViewById(R.id.progress_wheel);
        mProgressWheel.setBarWidth(3);
    }

    public void setmProgressWheelColor(int color){
        mProgressWheel.setBarColor(color);
    }

    public void mProgressWheelSpin(){
        mProgressWheel.spin();
        tickView.setVisibility(View.GONE);
        crossView.setVisibility(View.GONE);
    }

    public void mProgressWheelStop(){
        mProgressWheel.setProgress(1);
    }

    public void tickStart() {
        tickView.setVisibility(View.VISIBLE);
        crossView.setVisibility(View.GONE);
        tickView.start();
    }

    public void tickStop(){
        tickView.stop();
    }

    public void crossStart() {
        tickView.setVisibility(View.GONE);
        crossView.setVisibility(View.VISIBLE);
        crossView.start();
    }

    public void crossStop(){
        crossView.stop();
    }
}
