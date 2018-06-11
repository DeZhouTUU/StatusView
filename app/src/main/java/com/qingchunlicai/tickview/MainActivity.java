package com.qingchunlicai.tickview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qingchunlicai.tickview.StatusView.StatusView;
import com.qingchunlicai.tickview.StatusView.TickView;

public class MainActivity extends AppCompatActivity {

    StatusView mStatusView;
    TextView tv;

    boolean start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStatusView = (StatusView)findViewById(R.id.status_view);
        tv = (TextView)findViewById(R.id.tv);
        mStatusView.setmProgressWheelColor(ContextCompat.getColor(MainActivity.this,R.color.colorWhite));

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!start){
                    tv.setText("开始");
                    mStatusView.mProgressWheelSpin();
                }else {
                    tv.setText("停止");
                    mStatusView.crossStart();
                    mStatusView.mProgressWheelStop();
                }
                start = !start;
            }
        });
    }
}
