package com.casecode.mobilemovieexplorer.presentation.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AdapterViewFlipper;

import androidx.annotation.NonNull;

/**
 * Created by Mahmoud Abdalhafeez on 12/29/2023
 */
public class CustomAdapterViewFlipper extends AdapterViewFlipper {

    private GestureDetector gestureDetector;

    public CustomAdapterViewFlipper(Context context) {
        super(context);
        init(context);
    }

    public CustomAdapterViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, new SwipeGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gestureDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY) &&
                    Math.abs(diffX) > SWIPE_THRESHOLD &&
                    Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    // Swipe from left to right
                    showPrevious();
                } else {
                    // Swipe from right to left
                    showNext();
                }
                return true;
            }

            return false;
        }
    }
}
