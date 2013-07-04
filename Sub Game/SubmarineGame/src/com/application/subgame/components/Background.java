package com.application.subgame.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Background {

	private int x,y;
	private Bitmap img;
	
	public Background(int x, int y,Bitmap b){
		this.x = x;
		this.y = y;
		this.img = b;
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(img, this.x, this.y, null);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}
