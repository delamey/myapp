package com.example.delamey.myapplication5.wxapi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ListView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.delamey.myapplication5.R;

public class CustomListView extends ListView implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private GestureDetector mGestureDetector;

         // 删除事件监听器
         public interface OnDeleteListener {
             void onDelete(int index );
         }

            private OnDeleteListener mOnDeleteListener;

              // 删除按钮
              private View mDeleteBtn;

              // 列表项布局
              private ViewGroup mItemLayout;

              // 选择的列表项
              private int mSelectedItem;

              // 当前删除按钮是否显示出来了
              private boolean isDeleteShown;


    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(getContext(), this);

        // 监听onTouch事件
        setOnTouchListener(this);
    }


    // 设置删除监听事件
    public void setOnDeleteListener(OnDeleteListener listener) {
        mOnDeleteListener = listener;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDeleteShown) {
            hideDelete();
            return false;
            } else {
            return mGestureDetector.onTouchEvent(event);
            }
    }

    public void hideDelete() {
        mItemLayout.removeView(mDeleteBtn);
        mDeleteBtn = null;
        isDeleteShown = false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteShown) {
              mSelectedItem = pointToPosition((int) e.getX(), (int) e.getY());
           }
         return false;
    }

    public boolean isDeleteShown() {
        return isDeleteShown;
    }
    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 如果当前删除按钮没有显示出来，并且x方向滑动的速度大于y方向的滑动速度
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            mDeleteBtn = LayoutInflater.from(getContext()).inflate(
                    R.layout.delete_btn, null);

            mDeleteBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mItemLayout.removeView(mDeleteBtn);
                    mDeleteBtn = null;
                    isDeleteShown = false;
                    mOnDeleteListener.onDelete(mSelectedItem);
                }
            });

            mItemLayout = (ViewGroup) getChildAt(mSelectedItem
                    - getFirstVisiblePosition());

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);

            mItemLayout.addView(mDeleteBtn, params);
            isDeleteShown = true;
        }

        return false;
    }
}
