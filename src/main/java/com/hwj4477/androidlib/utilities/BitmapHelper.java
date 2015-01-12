package com.hwj4477.androidlib.utilities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapHelper {

    public static Bitmap getBitmapFromResource(Context context, int rId, BitmapFactory.Options options)
    {
        return BitmapFactory.decodeResource(context.getResources(), rId, options);
    }

	public static Bitmap getCircularBitmap(Bitmap bitmap)
	{
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	            bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
	            bitmap.getWidth() / 2, paint);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	    //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
	    //return _bmp;
	    return output;
	}
	
	public static Drawable getBitmapToDrawable(Bitmap bitmap, Context context)
	{
		return new BitmapDrawable(context.getResources(),bitmap);
	}
	
	public static Bitmap getDrawableToBitmap(int res_id, Context context)
	{
		BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(res_id);
		Bitmap bitmap = drawable.getBitmap();
		
		return bitmap;
	}
	
	public static void recycleBitmap(ImageView iv) {
        Drawable d = iv.getDrawable();
        if (d instanceof BitmapDrawable) {
            Bitmap b = ((BitmapDrawable)d).getBitmap();
            b.recycle();
        }
         
        d.setCallback(null);
    }
	
	public static Bitmap getBitmapForVisibleRegion(WebView webview) {
	    Bitmap returnedBitmap = null;
	    webview.setDrawingCacheEnabled(true);
	    returnedBitmap = Bitmap.createBitmap(webview.getDrawingCache());
	    webview.setDrawingCacheEnabled(false);
	    return returnedBitmap;
	}
	
	public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.RGB_565);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) 
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else 
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
	
	public static void saveBitmapToFileCache(Bitmap bitmap, String strFilePath)
	{
		File copyFile = new File(strFilePath);

		OutputStream out = null;
		        
		try {
		            
		    copyFile.createNewFile();
		    out = new FileOutputStream(copyFile);
		            
		    if ( bitmap.compress(CompressFormat.PNG, 70, out) )
		            ;
		    } catch (Exception e) {         
		    e.printStackTrace();
		    } finally {
		         try {
		             out.close();
		         } catch (IOException e) {
		             e.printStackTrace();
		    }
		}
	}
	
	public static int getBitmapOfWidth(String file)
	{
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(file, options);
			
			return options.outWidth;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public static int getBitmapOfHeight(String file)
	{
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(file, options);
			
			return options.outHeight;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public static int getSampleSize(int limit, int width, int height)
	{
		int sampleSize = 1;
		
		while(true)
		{
			int sampleWidth = width / sampleSize;
			int sampleHeight = height / sampleSize;
			
			if(sampleWidth <= limit && sampleHeight <= limit)
				break;
			
			sampleSize++;
		}
		
		return sampleSize;
	}
	
	public static void saveBitmapImage(Context context, Bitmap imageBitmap, String strFilename)
	{
		String path = Environment.getExternalStorageDirectory().toString();
		OutputStream fOut = null;
		File file = new File(path, strFilename);
		
		try {
			fOut = new FileOutputStream(file);
			
			imageBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
			
			fOut.flush();
			fOut.close();
			
			MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("glotrek", "file.getAbsolutePath() : " + file.getAbsolutePath());
		
		Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(file.getAbsolutePath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
	}
}
