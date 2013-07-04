package com.application.subgame;


public class Background {

	private int x,y;
	private int img;
	
	public Background(int x, int y){
		this.x = x;
		this.y = y;
		this.img = R.drawable.background;
	}
	
	public int getImg(){
		return this.img;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}
