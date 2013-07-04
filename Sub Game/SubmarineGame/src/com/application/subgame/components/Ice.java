package com.application.subgame.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.application.subgame.R;



public class Ice implements obj{
	
	private int img = R.drawable.ice;
	private int x,y,direction;
	private boolean damage;
	private Bitmap bitmap;
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public void setDamage(boolean t){
		damage = t;
	}
	
	public Ice(int x, int y,Bitmap b){
		this.x = x;
		this.y = y;
		this.bitmap = b;
	}
	
	public int[] getBox(){
		int [] a = {x,y,x+128,y+128};
		return a;
	}
	
	public int getImg(){
		return this.img;
	}
	
	public boolean getDamage(){
		return this.damage;
	}
	
	public void tick(){
		switch (direction){
		case 0:this.x = x +6; break;
		case 1:this.x = x -6; break;
		}
		if(x < -640){
			setDamage(true);
		}
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, this.x,this.y, null);
	}

}
