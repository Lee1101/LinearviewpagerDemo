package com.lee.user.linearviewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lee on 2016/11/3.
 */
public class MyLinearLayout extends LinearLayout {

    private TextView left;
    private TextView right;
    private ViewPager viewPager;

    private float startX;


    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        left = (TextView) getChildAt(0);
        viewPager = (ViewPager) getChildAt(1);
        right = (TextView) getChildAt(2);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //右滑时消费
                if (ev.getX() - startX > 0 && viewPager.getCurrentItem() == 0){
                    return true;
                }else if(ev.getX() - startX < 0 && viewPager.getCurrentItem() == viewPager.getAdapter().getCount()-1){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int delta = (int) (event.getX() - startX);
//                if(Math.abs(delta) < 500){
                if(delta > 0){
                    scrollTo(-delta,0);
                }else{
                    scrollTo(-delta,0);
                }
//                }
                break;
            case MotionEvent.ACTION_UP:
                int delta1 = (int) (event.getX() - startX);
                if(delta1 > 0){
                    scrollTo(0,0);
                }else{
                    scrollTo(0,0);
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
