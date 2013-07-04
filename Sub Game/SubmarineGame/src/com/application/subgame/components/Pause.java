package com.application.subgame.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Pause {
	
	private int x,y;
	private int alpha;
	private boolean decending;
	private Paint paint;
	private Bitmap bitmap;
	
	public Pause(int x, int y,Bitmap b){
		this.x = x;
		this.y = y;
		this.alpha = 250;
		decending = true;
		this.paint = new Paint();
		this.bitmap = b;
	}
	
	public void tick(){
		if (decending){
			if (this.alpha <=20){this.decending = false;}
			this.alpha = alpha - 10;
			this.paint.setAlpha(alpha);
		}
		else{
			if (this.alpha >= 240){this.decending = true;}
			this.alpha = this.alpha+10;
			this.paint.setAlpha(alpha);
		}
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x,y, paint);
	}

}
