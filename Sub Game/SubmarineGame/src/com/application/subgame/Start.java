package com.application.subgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Start{
	
	private MainGamePanel mainGame;
	private Bitmap bitmap;
	private Rect rect;
	private Paint paint;
	private int alpha, wait;
	
	public Start(MainGamePanel mgp, Rect rect){
		this.paint = new Paint();
		this.mainGame = mgp;
		this.rect = rect;
		this.wait = 15;
		alpha = 250;
		this.bitmap = BitmapFactory.decodeResource(mgp.getResources(), R.drawable.start);
	}
	
	public void update(){
		this.paint.setAlpha(alpha);
		if (wait <= 0){
			if (alpha > 10){
				this.alpha = this.alpha - 10;
			}
			else{
				this.mainGame.setState(GameStates.game);
				this.wait = 15;
				this.alpha = 250;
			}
		}
		wait = wait-1;
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, rect.width()/2-bitmap.getWidth()/2,rect.height()/2-bitmap.getHeight()/2, paint);
	}
	
	

}
