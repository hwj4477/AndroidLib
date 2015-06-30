package com.hwj4477.androidlib.utilities;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerSchdeuler
 *
 * @author hwj4477@gmail.com
 * @since 15.06.30
 *
 */

public class TimeScheduler {

    private TimeScheduler instance;

    private Timer timer;

    private Handler handler;

    private TimerScheduleListener listener;

    /**
     * Constructor
     */
    public TimeScheduler()
    {
        instance = this;
        timer = null;
        listener = null;

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(listener != null)
                    listener.handlerMessage(instance);
            }
        };
    }

    /**
     * start
     *
     * @param delay
     * @param period
     * @param listener
     */
    public void startScheduler(long delay, long period, TimerScheduleListener listener)
    {
        if(timer == null)
        {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, delay, period);

            this.listener = listener;
        }
    }

    /**
     * stop
     */
    public void stopScheduler()
    {
        if(timer != null)
        {
            timer.cancel();
            timer = null;
            listener = null;
        }
    }

    /**
     * etc.
     */
    public boolean isScheduling()
    {
        if(timer != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Interface - UI Thread Task
     */
    public interface TimerScheduleListener
    {
        void handlerMessage(TimeScheduler scheduler);
    }
}
