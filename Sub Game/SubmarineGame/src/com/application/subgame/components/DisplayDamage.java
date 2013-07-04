package com.application.subgame.components;


import android.graphics.Color;
import android.graphics.Paint;

public class DisplayDamage {
	
	private int x,y,despawne;
	private boolean remove;
	private String text;
	private Paint enemy;
	private Paint player;
	private Paint paint;

	
	public DisplayDamage(int d,int x,int y,boolean good){
		player = new Paint();
		player.setColor(Color.YELLOW);
		player.setTextSize(30);
		
		enemy = new Paint();
		enemy.setColor(Color.RED);
		enemy.setTextSize(30);
		
		this.x = x;
		this.y = y;
		if (this.x > 800){this.x = 720;}
		text = Integer.toString(d);
		this.despawne = 40;
		if (good){paint = player;}
		else{paint = enemy;}
	}
	
	public void tick(){
		this.y = this.y - 2;
		this.despawne = this.despawne-2;
		if (this.despawne<0){this.remove = true;}
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean getRemove(){
		return this.remove;
	}
	
	public String getText(){
		return this.text;
	}
	
	public Paint getPaint(){
		return this.paint;
	}
}
