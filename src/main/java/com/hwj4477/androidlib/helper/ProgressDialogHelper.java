package com.hwj4477.androidlib.helper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

public class ProgressDialogHelper {

	/**
	 *
	 * ProgressDialog Helper
	 *
	 * @author hwj4477@gmail.com
	 * @since 14.5.20
	 *
	 */
	
	private static ProgressDialogHelper instance = null;
	
	private static ProgressDialog loadingDialog = null;
	
	private static boolean isLoading = false;
	
	private static final int LOADING_LIMIT = 10000;
	
	private ProgressDialogHelper()
	{
	}
	
	public synchronized static ProgressDialogHelper getInstance()
	{
		if(instance == null)
		{
			instance = new ProgressDialogHelper();
		}
		
		return instance;
	}
	
	public void showLoading(Context context)
	{
		if(!isLoading)
		{
			loadingDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
			loadingDialog.setMessage("Loading...");
			loadingDialog.setCancelable(false);
			
			isLoading = true;
			
			loadingDialog.show();
			
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					hideLoading();
				}
			}, LOADING_LIMIT);
		}
	}
	
	public void showLoading(Context context, String messege)
	{
		if(!isLoading)
		{
			loadingDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
			loadingDialog.setMessage(messege);
			loadingDialog.setCancelable(false);
			
			isLoading = true;
			
			loadingDialog.show();
			
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					hideLoading();
				}
			}, LOADING_LIMIT);
		}
	}
	
	public void hideLoading()
	{
		if(isLoading && loadingDialog != null)
		{
			isLoading = false;
			
			loadingDialog.dismiss();
			
			loadingDialog = null;
		}
	}
}
