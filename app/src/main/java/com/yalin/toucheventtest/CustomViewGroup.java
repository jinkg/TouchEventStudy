package com.yalin.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * YaLin
 * 2016/12/22.
 */

public class CustomViewGroup extends LinearLayout {
    private static final String TAG = "CustomViewGroup";

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    float originX = -1, originY = -1;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: " + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                originX = ev.getX();
                originY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(ev.getX() - originX);
                int dy = (int) Math.abs(ev.getY() - originY);
                if (dx > 10 || dy > 10) {
                    originX = -1;
                    originY = -1;
                    onTouchEvent(cloneMotionEventWithAction(ev, MotionEvent.ACTION_DOWN));
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    private MotionEvent cloneMotionEventWithAction(MotionEvent event, int action) {
        return MotionEvent.obtain(event.getDownTime(), event.getEventTime(), action, event.getX(),
                event.getY(), event.getMetaState());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                getChildAt(2).setX(event.getX());
                getChildAt(2).setY(event.getY());
                break;
        }
        return super.onTouchEvent(event);
    }
}
