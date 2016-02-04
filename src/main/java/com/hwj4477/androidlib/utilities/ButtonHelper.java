package com.hwj4477.androidlib.utilities;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import java.util.ArrayList;

/**
 * ButtonHelper
 *
 * @author hwj4477@gmail.com
 *
 */

public class ButtonHelper {
	
	public static void buttonClickEffect(View button){
	    button.setOnTouchListener(new OnTouchListener() {

	        public boolean onTouch(View v, MotionEvent event) {
	        	
	        	Button btn = (Button)v;
	        	
	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN: {
	                	
	                	btn.setTextColor(Color.RED);
	                	btn.invalidate();
	                	
	                    break;
	                }
	                case MotionEvent.ACTION_UP: {

	                	btn.setTextColor(Color.WHITE);
	                	btn.invalidate();

	                    break;
	                }
	            }
	            return false;
	        }
	    });
	}
	
	public static void buttonExclusiveTouch(View button, final ArrayList<Button> arrayBtn){
	    button.setOnTouchListener(new OnTouchListener() {

	        public boolean onTouch(View v, MotionEvent event) {
	        	
	        	Button btn = (Button)v;
	        	
	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN: {
	                	
	                	for(int i=0; i<arrayBtn.size(); i++)
	                	{
	                		if(btn == arrayBtn.get(i))
	                		{
	                			arrayBtn.get(i).setEnabled(true);
	                		}
	                		else
	                		{
	                			arrayBtn.get(i).setEnabled(false);
	                		}
	                	}
	                	
	                    break;
	                }
	                case MotionEvent.ACTION_UP: {

	                	for(int i=0; i<arrayBtn.size(); i++)
	                	{
	                		arrayBtn.get(i).setEnabled(true);
	                	}
	                	
	                    break;
	                }
	            }
	            return false;
	        }
	    });
	}
}
