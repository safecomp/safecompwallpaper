package com.safecomp.wallpaper;

import java.util.Random;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class SafeCompWallpaperService extends WallpaperService{

	@Override
	public Engine onCreateEngine() {
		return new SafeCompWallpaperServiceEngine();
	}

	class SafeCompWallpaperServiceEngine extends Engine{
		private final Handler handler=new Handler();
		
		long lastTime=0;
		boolean tochable=false;
		String shape="Circle";
		@Override
		public void onTouchEvent(MotionEvent event) {
			super.onTouchEvent(event);
			
			if(tochable){
				long CurrentTime=System.currentTimeMillis();
				if(CurrentTime-lastTime>500){
					drawShape(event.getX(),event.getY());
					lastTime=CurrentTime;
				}
			}
		}
		
		private void drawShape(float x,float y){
			final SurfaceHolder holder=getSurfaceHolder();

			Canvas canvas=null;
			
			try{
				canvas=holder.lockCanvas();
				if(canvas!=null){
					Random rnd=new Random();
					int r=rnd.nextInt(Math.min(canvas.getWidth()/2,canvas.getHeight()/2));
					
					Paint p=new Paint();
					p.setColor(randomColor());
					
					if(shape.equals("Circle")){
						canvas.drawCircle(x,y,r, p);
					}else{
						Rect rect=new Rect((int)x-r/2,(int)y-r/2,(int)x+r/2,(int)y+r/2);
						canvas.drawRect(rect,p);
					}
				}
			}finally {
				if(canvas!=null){
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
		
		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
			
			draw();
		}
		private int randomColor(){
			Random rnd=new Random();
			int red=rnd.nextInt(256);
			int green=rnd.nextInt(256);
			int blue=rnd.nextInt(256);
			return Color.argb(255, red, green, blue);
		}
		private void draw(){
			SharedPreferences prefs = PreferenceManager
			          .getDefaultSharedPreferences(SafeCompWallpaperService.this);
			tochable=prefs.getBoolean("TouchEnable", false);
			shape=prefs.getString("Shape", "Circle");
			
			final SurfaceHolder holder=getSurfaceHolder();
			
			Canvas canvas=null;
			
			try{
				canvas=holder.lockCanvas();
				if(canvas!=null){
					Paint p=new Paint();
					p.setColor(randomColor());
					
					canvas.drawRect(new Rect(0,0,canvas.getWidth(),canvas.getHeight()),p);
				}
			}finally {
				if(canvas!=null){
					holder.unlockCanvasAndPost(canvas);
				}
			}
			
			handler.removeCallbacks(drawSurface);
			handler.postDelayed(drawSurface,1500);
		}
		private final Runnable drawSurface =new Runnable() {
			
			@Override
			public void run() {
				draw();
			}
		};
	}
}
