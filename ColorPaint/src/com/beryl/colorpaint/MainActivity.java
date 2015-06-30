package com.beryl.colorpaint;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.view.View.OnClickListener;

public  class MainActivity extends Activity implements OnClickListener  {
	private DrawView drawView;
	private Uri imageUri;
	File f = null;
	int shapenum;
	String imgSaved = null;
	final Context context = this;
	SlidingDrawer drawer;
	Button button;
	int mWidth;
    int mHeight;
	@SuppressWarnings("deprecation")
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        drawView =(DrawView)findViewById(R.id.drawing);
        final Button btnbrown1 = (Button)findViewById(R.id.btnbrown);
   
      
        btnbrown1.setOnClickListener(this); 
			
			
		
        
        final Button btnred1 = (Button)findViewById(R.id.btnred);
		
		btnred1.setOnClickListener(this);
		
final Button btnorange1 = (Button)findViewById(R.id.btnorange);
		
		btnorange1.setOnClickListener(this);
		
final Button btnyellow1 = (Button)findViewById(R.id.btnyellow);
		
		btnyellow1.setOnClickListener(this);
final Button btngreen1 = (Button)findViewById(R.id.btngreen);
		
		btngreen1.setOnClickListener(this);
		
final Button btnblue1 = (Button)findViewById(R.id.btnblue);
		
		btnblue1.setOnClickListener(this);
		
final Button btngrey1 = (Button)findViewById(R.id.btngrey);
		
		btngrey1.setOnClickListener(this);
final Button btnviolet1 = (Button)findViewById(R.id.btnviolet);
		
		btnviolet1.setOnClickListener(this);
		
final Button btnlilac1 = (Button)findViewById(R.id.btnlilac);
		
		btnlilac1.setOnClickListener(this);
final Button btnwhite1 = (Button)findViewById(R.id.btnwhite);
		
		btnwhite1.setOnClickListener(this);
		
final Button btnlblue1 = (Button)findViewById(R.id.btnlblue);
		
		btnlblue1.setOnClickListener(this);
		
final Button btnblack1 = (Button)findViewById(R.id.btnblack);
		
		btnblack1.setOnClickListener(this) ;
			
		
		
		
final ImageButton btnsqr1 = (ImageButton)findViewById(R.id.btnsqr);
		
		btnsqr1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 shapenum=1;
				 drawView.setShape(shapenum);
				}
		});
		
			final ImageButton btntri1 = (ImageButton)findViewById(R.id.btntri);
		
		btntri1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 shapenum=2;
				 drawView.setShape(shapenum);
				}
		});
		
final ImageButton btncircle1 = (ImageButton)findViewById(R.id.btncircle);
		
		btncircle1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 shapenum=3;
				 drawView.setShape(shapenum);
				}
		});
		
		
		final ImageButton btnexit1 = (ImageButton)findViewById(R.id.btnexit);
		
		btnexit1.setOnClickListener(this);

		
final ImageButton btnclear1 = (ImageButton)findViewById(R.id.btnclear);
		
		btnclear1.setOnClickListener(this);
		
		
		final ImageButton btngallery1 = (ImageButton)findViewById(R.id.btngallery);		
		
	     btngallery1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
							 "content://media/internal/images/media")); 
							 startActivity(intent); 
					}
			});
		
		
final ImageButton btnerase1 = (ImageButton)findViewById(R.id.btneraser);
	btnerase1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				drawView.setErase(true);
				
				 
						   
						
				
				
				
			}
	});
           
        // btnerase1.setOnClickListener(this);
		
final ImageButton btnsave1 = (ImageButton)findViewById(R.id.btnsave);
		
		btnsave1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				
				AlertDialog.Builder saveDialog = new AlertDialog.Builder(context);
				saveDialog.setTitle("Save Painting");
				saveDialog.setMessage("Save painting to the gallery?");
				saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
				    public void onClick(DialogInterface dialog, int which)
				    {
				        //save drawing
				    /*	drawView.setDrawingCacheEnabled(true);
				    	String imgSaved = MediaStore.Images.Media.insertImage(
				    			MainActivity.this.getContentResolver(), drawView.getDrawingCache(),
							    UUID.randomUUID().toString()+".png", "drawing");
						*/
				    	
				    	
				    	save();				    	
						if(imgSaved!=null)
						{
						    Toast savedToast = Toast.makeText(getApplicationContext(), 
						        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
						    savedToast.show();
						    
						    MediaScannerConnection.scanFile(context,
						            new String[] { f.toString() }, null,
						            new MediaScannerConnection.OnScanCompletedListener() {
						        public void onScanCompleted(String path, Uri uri) {
						            Log.i("ExternalStorage", "Scanned " + path + ":");
						            Log.i("ExternalStorage", "-> uri=" + uri);
						        }
						   });
						}
						else{
						    Toast unsavedToast = Toast.makeText(getApplicationContext(), 
						        "Oops! Image could not be saved.", Toast.LENGTH_LONG);
						    unsavedToast.show();
						    }
						
				    	
						drawView.destroyDrawingCache();
						
				    }
				 });
				saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
				{
				    public void onClick(DialogInterface dialog, int which)
				    {
				        dialog.cancel();
				    }
				});
				saveDialog.show();
			
			
			
				
				
			}
			
		});
		
		
		final ImageButton btnemail1 = (ImageButton)findViewById(R.id.btnemail);
		btnemail1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				
			
				  final Intent intent;
				  save();
			    	
				        intent = new Intent();
				        intent.setAction(Intent.ACTION_SEND);
				        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
				        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
				        intent.setType("image/*");
				        startActivity(intent);
				
				
			}
		});
		
		//sliding drawer test
		
		drawer=(SlidingDrawer)findViewById(R.id.slidingDrawer1);
		button=(Button)findViewById(R.id.handle);
		drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
		@Override
		public void onDrawerOpened() {
		

		}
		});
		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
		@Override
		public void onDrawerClosed() {
	
		
		}
		});
		
		mWidth = mHeight = 0;
		 
        
       ImageButton mBtnPick = (ImageButton) findViewById(R.id.btn_pick);
 
       
        mBtnPick.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
 
                Intent customChooserIntent = Intent.createChooser(i, "Pick an image");
                startActivityForResult(customChooserIntent, 10);
            }
        });
 
        if(savedInstanceState!=null){
            mWidth = savedInstanceState.getInt("width");
            mHeight = savedInstanceState.getInt("height");
            Bitmap bitmap = savedInstanceState.getParcelable("bitmap");
            if(bitmap!=null){
                drawView.addBitmap(bitmap);
            }
        }
    }
 
    
    public static int calculateInSampleSize(
 
        BitmapFactory.Options options, int reqWidth, int reqHeight) {
     
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
 
        if (height > reqHeight || width > reqWidth) {
 
     
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
 
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
 
        return inSampleSize;
		
		
    }
    
    private Bitmap getBitmapFromUri(Uri data){
        Bitmap bitmap = null;
 
        
        InputStream is=null;
        try {
 
            is = getContentResolver().openInputStream(data);
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, options);
            options.inSampleSize = calculateInSampleSize(options, mWidth, mHeight);
            options.inJustDecodeBounds = false;
            is = getContentResolver().openInputStream(data);
            bitmap = BitmapFactory.decodeStream(is,null,options);
            if(bitmap==null){
                Toast.makeText(getBaseContext(), "Image is not Loaded",Toast.LENGTH_SHORT).show();
                return null;
            }
 
            is.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return bitmap;
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 10 && resultCode == RESULT_OK && null != intent) {
            Uri data = intent.getData();
            Bitmap bitmap = getBitmapFromUri(data);
            if(bitmap!=null){
            	drawView.addBitmap(bitmap);
            }
        }
    }
 
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mWidth = drawView.getWidth();
        mHeight = drawView.getHeight();
    }
 
    @Override
    protected void onSaveInstanceState(Bundle outState) {
 
        outState.putInt("width", mWidth);
        outState.putInt("height", mHeight);
        if(drawView.getBitmap()!=null){
            outState.putParcelable("bitmap", drawView.getBitmap());
        }
 
        super.onSaveInstanceState(outState);
    }
 
    @Override
    protected void onResume() {
    	drawView.pause(false);
        drawView.invalidate();
        super.onResume();
    }
 
    @Override
    protected void onPause() {
    	drawView.pause(true);
        super.onPause();
 
    }
 
    
    public void save()
    {
    	 drawView.setDrawingCacheEnabled(true);
	    	OutputStream fOut = null;
	    	File mediaDirectory = new File(Environment.getExternalStorageDirectory().getPath()
	    	   			+ "/DCIM/Camera/");
	    	 mediaDirectory.mkdirs();
	    	  f = new File(mediaDirectory, UUID.randomUUID().toString() + ".png");	    	
	    	 try {
	    	     fOut = new FileOutputStream(f);
	    	     drawView.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, fOut);
	    	     fOut.flush();
	    	     fOut.close();
	    	     imgSaved = "success";
	    	    /* sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse
	    	     ("file://" + Environment.getExternalStorageDirectory())));*/
	    	}
	    	catch (Exception e) {
	    	     e.printStackTrace();
	    	}				  
	    	 MediaScannerConnection.scanFile(context,
			            new String[] { f.toString() }, null,
			            new MediaScannerConnection.OnScanCompletedListener() {
			        public void onScanCompleted(String path, Uri uri) {
			            Log.i("ExternalStorage", "Scanned " + path + ":");
			            Log.i("ExternalStorage", "-> uri=" + uri);
			            imageUri = uri;
			        }
			   });
    }
    
    
    @Override
	public void onClick(View v) {
    	/*if(v.getId()==R.id.btnsqr){
    		 shapenum=1;
			 drawView.setShape(shapenum);
		    	}
		if(v.getId()==R.id.btntri){
			shapenum=2;
			 drawView.setShape(shapenum);
		    	    
		    	}
		if(v.getId()==R.id.btncircle){
			shapenum=3;
			 drawView.setShape(shapenum);
		    
		}*/
		if(v.getId()==R.id.btnexit){
			finish();
			System.exit(0);
		    
		}
		if(v.getId()==R.id.btnclear){
			drawView.clear();
		    
		}
		
    	else
    	{
    		
    		drawView.setErase(false);
		String color = v.getTag().toString();
		drawView.setColor(color);
		
		}
		/*if (v.getId()==R.id.btneraser){
			//drawView.setErase(true);
		}*/
	}
    
    

    
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
     /*   drawView.setDrawingCacheEnabled(true);
        drawView.getDrawingCache();*/
       // setContentView(R.layout.activity_main);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


   
}
 