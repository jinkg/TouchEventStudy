package com.yalin.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * YaLin
 * 2016/12/22.
 */

public class CustomView2 extends TextView {
    private static final String TAG = "CustomView";

    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    float originX = -1, originY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                originX = event.getX();
                originY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(event.getX() - originX);
                int dy = (int) Math.abs(event.getY() - originY);
                if (dx > 10 || dy > 10) {
                    return false;
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
