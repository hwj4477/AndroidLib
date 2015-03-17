package com.hwj4477.androidlib.camera;

import android.hardware.Camera;
import android.util.Log;

public class CameraHelper {

    /**
     *
     * @author hwj4477@gmail.com
     * @since 14.11.20
     *
     */

	private static final String LOG = "LOG";
	
	public static Camera openFrontFacingCameraGingerbread() {
	    int cameraCount = 0;
	    Camera cam = null;
	    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
	    cameraCount = Camera.getNumberOfCameras();
	    for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
	        Camera.getCameraInfo(camIdx, cameraInfo);
	        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
	            try {
	                cam = Camera.open(camIdx);
	            } catch (RuntimeException e) {
	                Log.e(LOG, "Camera failed to open: " + e.getLocalizedMessage());
	            }
	        }
	    }

	    return cam;
	}
}
