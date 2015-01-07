package com.hwj4477.androidlib.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 *
 */

public class DrawView extends View implements OnTouchListener {
	
	private Context _context = null;
	
	private List<Point> points = new ArrayList<Point>();
	private Paint paint;
	private Paint eraserPaint;
	private Bitmap bitmap;
	private Canvas actualCanvas;

	private final int lineWidth = 10;
	private final int color = Color.BLACK;
	private int paintAlpha;
	
	private boolean eraserMode = false;
	
	public DrawView(Context context) {
		super(context);
		
		_context = context;
		init();
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		_context = context;
		init();
	}

	public DrawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		_context = context;
		init();
	}

	public void init() {
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);
		
		paintAlpha = 250;

		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(color);
		paint.setStrokeWidth(lineWidth);
		paint.setAntiAlias(true);
		paint.setStrokeCap(Cap.ROUND);
		
		paint.setAlpha(paintAlpha);
		
		eraserPaint = new Paint();
		eraserPaint.setStyle(Paint.Style.FILL);
		eraserPaint.setStrokeWidth(lineWidth);
		eraserPaint.setAntiAlias(true);
		eraserPaint.setStrokeCap(Cap.ROUND);

		eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
	}
	
	public void setPaintColor(int color)
	{
		eraserMode = false;
		
		paint.setColor(color);
		paint.setAlpha(paintAlpha);
	}
	
	public void setPaintWidth(int width)
	{
		paint.setStrokeWidth(width);
	}
	
	public void setEraser()
	{
		eraserMode = true;
	}

	public void setResizeView()
	{
		bitmap.recycle();
		bitmap = null;
	}

	@Override
	public void onDraw(Canvas canvas) {
		if (bitmap == null) {
			this.bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_4444);

			actualCanvas = new Canvas(bitmap);
		}

		if(eraserMode)
		{
			for (Point point : points) {
				if (points.indexOf(point) == 0) {
					actualCanvas.drawCircle(point.x, point.y,
							(int) (lineWidth / 2), eraserPaint);
				} else {
					Point last = points.get(points.indexOf(point) - 1);
					actualCanvas.drawLine(last.x, last.y, point.x, point.y, eraserPaint);
				}
			}
		}
		else
		{
			for (Point point : points) {
				if (points.indexOf(point) == 0) {
					actualCanvas.drawCircle(point.x, point.y,
							(int) (lineWidth / 2), paint);
				} else {
					Point last = points.get(points.indexOf(point) - 1);
					actualCanvas.drawLine(last.x, last.y, point.x, point.y, paint);
				}
			}
		}

		canvas.drawBitmap(bitmap, 0, 0, paint);
	}

	public Bitmap getBitmap() {
		Bitmap resultBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_4444);
		
		Canvas saveCanvas = new Canvas(resultBitmap);
		saveCanvas.drawColor(Color.WHITE);
		saveCanvas.drawBitmap(bitmap, 0, 0, paint);
		
		return resultBitmap;
	}
	
	public Uri getBitmapUri() {
		String path = Images.Media.insertImage(_context.getContentResolver(), getBitmap(),null, null);
        Uri result= Uri.parse(path);
        
        return result;
	}

	public void clear() {
		bitmap = null;
		actualCanvas = null;
		points.clear();
		invalidate();
	}

	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			points.clear();
		} else {
			Point point = new Point();
			point.x = event.getX();
			point.y = event.getY();
			points.add(point);
		}
		invalidate();
		return true;
	}
}

class Point {
	float x, y;

	@Override
	public String toString() {
		return x + ", " + y;
	}
}