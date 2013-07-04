package com.application.subgame.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.application.subgame.R;


public class Bubble implements obj {

	private int img = R.drawable.bubble;
	private int x,y,direction,despawne;
	private boolean damage;
	private boolean moving;
	private Bitmap bitmap;
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public void setDamage(boolean t){
		damage = t;
	}
	
	public Bubble(int x, int y,Bitmap b){
		this.despawne = (int) (Math.random()*40) + 10;
		this.x = x;
		this.y = y;
		this.bitmap = b;
	}
	
	public void setMoving(boolean m){
		this.moving = m;
	}
	
	public int[] getBox(){
		int [] a = {0,0,0,0};
		return a;
	}
	
	public int getImg(){
		return this.img;
	}
	
	public boolean getDamage(){
		return this.damage;
	}
	
	public void tick(){
		this.y = this.y - 2;
		this.despawne = this.despawne-1;
		if (this.moving){
			switch (direction){
			case 0:this.x = x +6; break;
			case 1:this.x = x -6; break;
			}
		}
		if (this.despawne<=0){this.damage = true;}
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
		canvas.drawBitmap(bitmap,this.x,this.y, null);
	}

}
