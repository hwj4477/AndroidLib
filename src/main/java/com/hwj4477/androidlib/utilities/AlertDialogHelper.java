package com.hwj4477.androidlib.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * AlertDialogHelper
 *
 * @author hwj4477@gmail.com
 *
 */

public class AlertDialogHelper {
	
	public static String BUTTON_MESSAGE_OK = "OK";
	public static String BUTTON_MESSAGE_YES = "YES";
	public static String BUTTON_MESSAGE_NO = "NO";

	public static void simpleDialog(Context context, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setMessage(msg);
		alertDialogBuilder.setPositiveButton(BUTTON_MESSAGE_OK, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialogBuilder.setCancelable(true);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	public static void commonDialog(Context context, String msg, DialogInterface.OnClickListener p_listener, DialogInterface.OnClickListener n_listener)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setMessage(msg);
		alertDialogBuilder.setPositiveButton(BUTTON_MESSAGE_YES, p_listener);
		alertDialogBuilder.setNegativeButton(BUTTON_MESSAGE_NO, n_listener);
		alertDialogBuilder.setCancelable(true);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
