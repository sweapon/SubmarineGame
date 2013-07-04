package com.application.subgame.components;

import android.graphics.Canvas;

public interface obj {
	
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public void tick();
	public int getImg();
	public void setDirection(int d);
	public int[] getBox();
	public void draw(Canvas canvas);

}
