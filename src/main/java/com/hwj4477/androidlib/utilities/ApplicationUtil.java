package com.hwj4477.androidlib.utilities;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * @author wjhong
 * @since 2016. 3. 9..
 *
 * Application Util
 *
 */

public class ApplicationUtil {

    private static boolean isRunningAppication(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo info : tasks) {

            ComponentName name = info.topActivity;
            if(name.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }

        return false;
    }
}
