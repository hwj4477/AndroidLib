package com.hwj4477.androidlib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.12.10.
 *
 */

public class ScrollViewNoScroll extends ScrollView {

	public ScrollViewNoScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean onTouchEvent(MotionEvent ev) {
		return true;
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;
	}
}
