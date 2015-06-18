package com.hwj4477.androidlib.av;

import android.content.Context;
import android.media.MediaRecorder;

import java.io.IOException;

/**
 * MediaRecorder Wrapper
 *
 * @author wjhong
 * @since 15. 6. 18..
 */
public class MediaRecorderWrapper {

    private static MediaRecorderWrapper instance = null;

    private Context _context = null;

    private MediaRecorder mediaRecorder;

    private boolean isRecording = false;

    /**
     * Singleton
     *
     * @param context
     * @return instance
     */
    synchronized public static MediaRecorderWrapper getInstance(Context context)
    {
        if(instance == null)
            instance = new MediaRecorderWrapper(context);
        return instance;
    }

    private MediaRecorderWrapper(Context context)
    {
        _context = context;
    }

    /**
     * Start Recording
     *
     * @param outputFile
     */
    public void start(String outputFile)
    {
        if(!isRecording)
        {
            isRecording = true;

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            mediaRecorder.setOutputFile(outputFile);

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stop
     */
    public void stop()
    {
        if(isRecording)
        {
            isRecording = false;

            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
