package com.hwj4477.androidlib.utilities;

import android.content.Context;

/**
 * LayoutValueConvertHelper
 *
 * @author hwj4477@gmail.com
 *
 */

public class LayoutValueConvertHelper {

	public static float convertPixelToDP(float px, Context context){
		return px * context.getResources().getDisplayMetrics().density;
    }
}
