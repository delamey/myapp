package com.example.delamey.myapplication5;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WaveView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mWaveHeight;
    private int mWaveDx;
    private int dx;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#FF3891"));
        mPaint.setStyle(Paint.Style.FILL);
        //波长的长度(这里设置为屏幕的宽度)
        mWaveDx = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //控件的宽高
        mWidth = MeasureUtils.measureView(widthMeasureSpec, mWaveDx);
        mHeight = MeasureUtils.measureView(heightMeasureSpec, 300);
        //水波的高度
        mWaveHeight = DensityUtil.dip2px(getContext(), 16);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWave(canvas);
    }


    private void drawWave(Canvas canvas) {
        Path path = new Path();
        path.reset();
        path.moveTo(-mWaveDx + dx, mHeight / 2);
        for (int i = -mWaveDx; i < getWidth() + mWaveDx; i += mWaveDx) {
            path.rQuadTo(mWaveDx / 4, -mWaveHeight, mWaveDx / 2, 0);
            path.rQuadTo(mWaveDx / 4, mWaveHeight, mWaveDx / 2, 0);

        }
        //绘制封闭的区域
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        //path.close() 绘制封闭的区域
        path.close();
        canvas.drawPath(path, mPaint);
    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mWaveDx);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //水平方向的偏移量
                dx = (int) animation.getAnimatedValue();
                invalidate();
            }

        });
        valueAnimator.start();

    }
    public static class MeasureUtils {
        /**
         * 用于View的测量
         *
         * @param measureSpec 测量模式和大小
         * @param defaultSize 默认的大小
         * @return
         */
        public static int measureView(int measureSpec, int defaultSize) {
            int measureSize;
            //获取用户指定的大小以及模式
            int mode = MeasureSpec.getMode(measureSpec);
            int size = MeasureSpec.getSize(measureSpec);
            //根据模式去返回大小
            if (mode == MeasureSpec.EXACTLY) {
                //精确模式（指定大小以及match_parent）直接返回指定的大小
                measureSize = size;
            } else {
                //UNSPECIFIED模式、AT_MOST模式（wrap_content）的话需要提供默认的大小
                measureSize = defaultSize;
                if (mode == MeasureSpec.AT_MOST) {
                    //AT_MOST（wrap_content）模式下，需要取测量值与默认值的最小值
                    measureSize = Math.min(measureSize, size);
                }
            }
            return measureSize;
        }

        private int measureSize(int measureSpec) {
            int result;
            int specMode = MeasureSpec.getMode(measureSpec);
            int specSize = MeasureSpec.getSize(measureSpec);
            if (specMode == MeasureSpec.EXACTLY) {
                result = specSize;
            } else {
                result = 300;
                if (specMode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, specSize);
                }
            }
            return result;

        }

    }
    public static class DensityUtil {


        public static int dip2px(Context var0, float var1) {
            float var2 = var0.getResources().getDisplayMetrics().density;
            return ( int ) (var1 * var2 + 0.5F);
        }

        public static int dp2px(float value) {
            final float scale = Resources.getSystem().getDisplayMetrics().densityDpi;
            return ( int ) (value * (scale / 160) + 0.5f);
        }

        public static int px2dip(Context var0, float var1) {
            float var2 = var0.getResources().getDisplayMetrics().density;
            return ( int ) (var1 / var2 + 0.5F);
        }

        public static int sp2px(Context var0, float var1) {
            float var2 = var0.getResources().getDisplayMetrics().scaledDensity;
            return ( int ) (var1 * var2 + 0.5F);
        }

        public static int px2sp(Context var0, float var1) {
            float var2 = var0.getResources().getDisplayMetrics().scaledDensity;
            return ( int ) (var1 / var2 + 0.5F);
        }
    }
}