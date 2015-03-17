package com.hwj4477.androidlib.camera;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class Preview extends SurfaceView implements SurfaceHolder.Callback {

    /**
     *
     * @author hwj4477@gmail.com
     * @since 14.11.20
     *
     */

    private static final String LOG = "LOG";

    SurfaceHolder mHolder;
    public Camera mCamera;
    
    public Preview(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init();
	}
	
	public Preview(Context context, AttributeSet attrs){
		super(context, attrs);
		init();
	}
	
	public Preview(Context context) {
        super(context);
        init();
    }
	
	private void init()
	{
		mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

    public void surfaceCreated(SurfaceHolder holder) {

    	mCamera = CameraHelper.openFrontFacingCameraGingerbread();
    	mCamera.setDisplayOrientation(90);
        try {
           mCamera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    	mCamera.stopPreview();
        mCamera = null;
    }
    
    public void destroyCamera()
    {
    	surfaceDestroyed(mHolder);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

    	Parameters parameters = mCamera.getParameters();
    	
    	int min = parameters.getMinExposureCompensation();
    	int max = parameters.getMaxExposureCompensation();
    	
    	parameters.setExposureCompensation(max);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }
    
    public void setExposureCompensation(int value)
    {
    	try {
    		Parameters parameters = mCamera.getParameters();
        	
        	int min = parameters.getMinExposureCompensation();
        	int max = parameters.getMaxExposureCompensation();
        	
        	if(value < min)
        		value = min;
        		
        	if(value > max)
        		value = max;
        	
        	parameters.setExposureCompensation(value);
            mCamera.setParameters(parameters);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.d(LOG, "Camera is Null");
		}
    	
    }
    
    public void setZoom(int value)
    {
    	try {
    		Parameters parameters = mCamera.getParameters();
        	
        	int min = 0;
        	int max = parameters.getMaxZoom();
        	
        	if(value < min)
        		value = min;
        		
        	if(value > max)
        		value = max;
        	
        	parameters.setZoom(value);
            mCamera.setParameters(parameters);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.d(LOG, "Camera is Null");
		}
    }
    
    public void setFlash(boolean onOff)
    {
    	try {
    		Parameters parameters = mCamera.getParameters();
        	
    		if(onOff)
    		{
    			parameters.setFlashMode(Parameters.FLASH_MODE_ON);
    		}
    		else
    		{
    			parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
    		}
    		
            mCamera.setParameters(parameters);
            
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.d(LOG, "Camera is Null");
		}
    }
    
    public void setFlashAuto()
    {
    	try {
    		Parameters parameters = mCamera.getParameters();
        	
    		parameters.setFlashMode(Parameters.FLASH_MODE_AUTO);
    		
            mCamera.setParameters(parameters);
            
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.d(LOG, "Camera is Null");
		}
    }
}