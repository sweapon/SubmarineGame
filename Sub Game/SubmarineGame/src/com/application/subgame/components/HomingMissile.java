package com.application.subgame.components;

import java.util.ArrayList;

import com.application.subgame.Game;
import com.application.subgame.MainActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class HomingMissile extends Missile{
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private Enemy target;
	private int destinationy,destiantionx,dy,dx;
	private Matrix matrix;
	private Game game;
	private ArrayList<Enemy> list = new ArrayList<Enemy>();
	
	public HomingMissile(int x, int y, int bd, int var, Bitmap b,Enemy target,Game game){
		super(x, y, bd, var, b);
		this.target = target;
		this.dy = 0;
		this.dx = 0;
		this.game = game;
		this.matrix = new Matrix();
	}
	
	public void tick(){
		if (target != null){
			if (!target.getDestroyed()){
				destinationy = target.getY() + 32/2;
				destiantionx = target.getX() + 64/2;
				//y
				if(destinationy > this.y){
					if (dy < 12){
						dy = dy + 1;
					}
				}
				else if(destinationy < this.y){
					if (dy > -12){
						dy = dy - 1;
					}
				}
				//x
				if(destiantionx > this.x){
					if (dx < 12){
						dx = dx + 2;
					}
				}
				else if(destiantionx < this.x){
					if (dx > -12){
						dx = dx - 2;
					}
				}
				setX(this.x + dx);
				setY(this.y + dy);
			}else{
				if (dx < 12){
					dx = dx + 2;
				}
				if(dy != 0){
					if (dy>0) dy = dy - 1;
					if (dy<0) dy = dy + 1;
				}
				setX(this.x + dx);
				setY(this.y + dy);
				this.target = null;
			}
		}
		if(target == null){
			if (dx < 12){
				dx = dx + 2;
			}
			if(dy != 0){
				if (dy>0) dy = dy - 1;
				if (dy<0) dy = dy + 1;
			}
			setX(this.x + dx);
			setY(this.y + dy);
			list = new ArrayList<Enemy>();
			for (obj j: game.getMap().getPotentionalMonsters()){
				if (j.getClass() == Enemy.class){
					list.add((Enemy)j);
				}
			}
			if(list.size() != 0){
				target = list.get((int)(Math.random()*list.size()));
			}
			list.clear();
		}
	}
	
	public void draw(Canvas canvas) {		
		float dx = this.dx;
		float dy = this.dy;
		float angel = 0;
		
		if(((dx >= 0 && dy > 0) || (dy <= 0 && dx >= 0)) && (Math.abs(dx) > Math.abs(dy))){
			if(dx == 0) angel = 0;
			else angel = dy/dx;
			angel = (float) (Math.asin(angel)/Math.PI*180);
		}
		else if (((dx < 0 && dy >= 0) || (dx >= 0 && dy >= 0))&& (Math.abs(dy) > Math.abs(dx))){
			if(dy == 0)angel = -1;
			else angel = dx/dy;
			angel = (float) (Math.acos(angel)/Math.PI*180);
		}else if(Math.abs(dy) > Math.abs(dx)){
			angel = dx/dy;
			angel = (float) (Math.acos(angel)/Math.PI*180)+180;
		}else if(Math.abs(dx) > Math.abs(dy)){
			angel = dy/dx;
			angel = (float) (Math.asin(angel)/Math.PI*180)+180;
		}else if (Math.abs(dx) == Math.abs(dy)){
			
			angel = (float) (Math.atan(dy/dx)/Math.PI*180);
		}else Log.d(TAG,"Det er fortsatt noe som ikke funker");
		matrix.setRotate(angel ,this.bitmap.getWidth()/2 , this.bitmap.getHeight()/2);
		matrix.postTranslate(this.x, this.y);
		canvas.drawBitmap(this.bitmap, matrix, null);
	}
}