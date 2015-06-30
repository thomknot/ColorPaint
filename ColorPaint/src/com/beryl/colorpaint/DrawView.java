package com.beryl.colorpaint;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.MotionEvent;


public class DrawView extends View {

	private Paint drawPaint, mPaint;
	private int paintColor = 0xFF667700;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap, mBitmap;
	private int shape1 =1;
	private int z=0;
	private SparseArray<PointF> mActivePointers;
	Matrix mMatrix;
    RectF mSrcRectF;
    RectF mDestRectF;
    boolean mPause;
	private boolean erase=false;
	double[] pressure = new double[10];
	 private int[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA,
		      Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
		      Color.LTGRAY, Color.YELLOW };
	 
	 public void setErase(boolean isErase){
	      erase=isErase; 
	      if(erase) 
	      {
	    	 // drawCanvas.drawText("Eraser Selected", 10, 60, mPaint);
	    	
	    	  this.setColor("#FF00FFFF");
	    	 // drawPaint.setAlpha(0xFF);
	     // drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT ));
	      drawPaint.setAntiAlias(true);
	       invalidate();
	      }
	      else drawPaint.setXfermode(null);
	        //  invalidate();
	 }
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
		mMatrix = new Matrix();
        mSrcRectF = new RectF();
        mDestRectF = new RectF();
        mPause = false;
		
	}
	
	public void addBitmap(Bitmap bitmap){
        mBitmap = bitmap;
    }
 
    public Bitmap getBitmap(){
        return mBitmap;
    }
	
	private void setupDrawing(){
		//drawPath = new Path();
		drawPaint = new Paint();
		drawPaint.setColor(paintColor);
		mActivePointers = new SparseArray<PointF>();
	    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    mPaint.setColor(Color.BLUE);
	    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	    
		}
		
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		if(!mPause){
            if(mBitmap!=null){
 
                // Setting size of Source Rect
                mSrcRectF.set(0, 0,mBitmap.getWidth(),mBitmap.getHeight());
 
                // Setting size of Destination Rect
                mDestRectF.set(0, 0, getWidth(), getHeight());
 
                // Scaling the bitmap to fit the PaintView
                mMatrix.setRectToRect( mSrcRectF , mDestRectF, ScaleToFit.CENTER);
 
                // Drawing the bitmap in the canvas
                canvas.drawBitmap(mBitmap, mMatrix, mPaint);
            }
 
            // Redraw the canvas
            invalidate();
        }
    
		
	
		canvas.drawBitmap(canvasBitmap, 0, 0, drawPaint);
		//testing
		 
		for (int size = mActivePointers.size(), i = 0; i < size; i++) {
		      PointF point = mActivePointers.valueAt(i);
		      if (point != null)
		    	  mPaint.setColor(colors[i % 9]);  
		  	
				switch(shape1){
				case 1:
					drawCanvas.drawRect(point.x, point.y, point.x+16, point.y+16, drawPaint);
					break;
				case 2:
				{

					Path p = new Path();
					p.moveTo(point.x, point.y);
					p.lineTo(point.x+16, point.y);
					p.lineTo(point.x+8, point.y-16);
					p.lineTo(point.x, point.y);
					p.close();
					drawCanvas.drawPath(p, drawPaint);
				}
				break;
				case 3:
					drawCanvas.drawCircle(point.x, point.y, 10, drawPaint); 
					
					break;
					
				}
		      
		    }
		   
		canvas.drawText("Total pointers: " + mActivePointers.size(), 10, 40 , mPaint);
		//canvas.drawText("pressure "+pressure, 10, 60, mPaint);
		
		
	}
	
	 public void pause(boolean pause){
	        mPause = pause;
	    }
	
	
	public void setShape(int newShape){
		invalidate();
		shape1 = newShape;
		
	}
	
	public void setColor(String newColor){
		//set color     
		
		invalidate();
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);
		}
	
	public void clear(){
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*float x = event.getX();
		float y = event.getY();
	
		
		
		invalidate();*/
		
		//TESTING

		
		
		int pointerIndex = event.getActionIndex();

	    int pointerId = event.getPointerId(pointerIndex);

	    int maskedAction = event.getActionMasked();
	    
	    switch (maskedAction) {

	    case MotionEvent.ACTION_DOWN:
	    case MotionEvent.ACTION_POINTER_DOWN: {
	      
	    	
	    	pressure[pointerId] = event.getPressure(pointerIndex);
	      PointF f = new PointF();
	      f.x = event.getX(pointerIndex);
	      f.y = event.getY(pointerIndex);
	      mActivePointers.put(pointerId, f);
	      break;
	    }
	    case MotionEvent.ACTION_MOVE: {
	      for (int size = event.getPointerCount(), i = 0; i < size; i++) {
	        PointF point = mActivePointers.get(event.getPointerId(i));
	        if (point != null) {
	          point.x = event.getX(i);
	          point.y = event.getY(i);
	        }
	      }
	      break;
	    }
	    case MotionEvent.ACTION_UP:
	    case MotionEvent.ACTION_POINTER_UP:
	    case MotionEvent.ACTION_CANCEL: {
	      mActivePointers.remove(pointerId);
	      
	      break;
	    }
	    }
	    
	    invalidate();
	
		
		    return true;
		    
		}
		
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}
	
	
}
