package com.qingchunlicai.tickview.StatusView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by fatJiang on 2018/6/11.
 */

public class CrossView extends View {

    private int width;
    private int height;

    float factor;//进度因子:0-1
    float scaleAX = 0.3333f;
    float scaleAY = 0.3333f;
    float scaleBX = 0.6667f;
    float scaleBY = 0.6667f;
    float scaleCX = 0.6667f;
    float scaleCY = 0.3333f;
    float scaleDX = 0.3333f;
    float scaleDY = 0.6667f;
    private PathMeasure tickPathMeasure;
    private Path path;

    private float progress;
    private AnimatorSet set;
    private ValueAnimator animation1;
    private ValueAnimator animation;
    private Paint paint;
    private Path pathTick;
    private Path pathTick2;
    private Animator.AnimatorListener listener;

    public CrossView(Context context) {
        this(context, null);
    }

    public CrossView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrossView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        path = new Path();
        pathTick = new Path();
        pathTick2 = new Path();
        tickPathMeasure = new PathMeasure();

        animation1 = ValueAnimator.ofFloat(0f, 1f);
        animation1.setDuration(250);
        animation1.setInterpolator(new LinearInterpolator());
        animation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                factor = (float) animation.getAnimatedValue();//更新进度因子
                postInvalidate();//刷新
            }
        });
        animation = ValueAnimator.ofFloat(0f, 1f);
        animation.setDuration(250);
        animation.setInterpolator(new LinearInterpolator());
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                postInvalidate();//刷新
            }
        });
        set = new AnimatorSet();
        set.play(animation).after(animation1);
        set.setDuration(500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathTick.moveTo(width * scaleAX, height * scaleAY);
        pathTick.lineTo(width * scaleBX, height * scaleBY);
        tickPathMeasure.setPath(pathTick, false);
        tickPathMeasure.getSegment(0, factor * tickPathMeasure.getLength(), path, true);
        path.rLineTo(0, 0);
        canvas.drawPath(path, paint);
        pathTick2.moveTo(width * scaleCX, height * scaleCY);
        pathTick2.lineTo(width * scaleDX, height * scaleDY);
        tickPathMeasure.setPath(pathTick2, false);
        tickPathMeasure.getSegment(0, progress * tickPathMeasure.getLength(), path, true);
        path.rLineTo(0, 0);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void setListener(Animator.AnimatorListener listener) {
        this.listener = listener;
    }

    public void start() {
        stop();
        path = new Path();
        factor = 0;
        progress = 0;
        //属性动画-插值器刷新
        if (set != null) {
            if (listener != null)
                set.addListener(listener);
            set.start();
        }
    }

    public void stop() {
        if (set != null) {
            set.end();
        }
    }

}
