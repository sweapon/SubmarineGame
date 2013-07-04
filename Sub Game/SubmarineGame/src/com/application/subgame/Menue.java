package com.application.subgame;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.application.subgame.components.Background;

public class Menue {
	
	private Background bg;
	private String overskrift,start,exit;
	private int p;
	private float[] peker = {0,0,0,0};
	private Paint f = new Paint();
	private Paint f2 = new Paint();
	private Game game;
	private boolean isTouched;
	private Bitmap selectbar;
	
	
	public Menue(Game game){
		p = 0;
		this.game = game;
		this.selectbar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResource(), R.drawable.selectbar), game.getWidth(), game.getHeight()/12, true);
		this.bg = new Background(0, 0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResource(), R.drawable.background2),game.getWidth(),game.getHeight(),true));
		this.overskrift = "SUUUB Game 4tw";
		this.start = "Start Game";
		this.exit = "Exit";
		this.peker[0] = this.game.getWidth()/8;
		this.peker[1] = this.game.getHeight()*1/6;
		this.peker[2] = this.game.getWidth()*3/4;
		this.peker[3] = this.game.getHeight()*2/6;

		this.f.setTextSize(50);
		this.f2.setTextSize(35);
	}
	
	public void repaint(){
		switch(p){
		case 0:
		this.peker[0] = this.game.getWidth()/8;
		this.peker[1] = this.game.getHeight()*3/12;
		this.peker[2] = this.game.getWidth()*3/4;
		this.peker[3] = this.game.getHeight()*2/6;
		break;
		case 1:
		this.peker[0] = this.game.getWidth()/8;
		this.peker[1] = this.game.getHeight()*5/12;
		this.peker[2] = this.game.getWidth()*3/4;
		this.peker[3] = this.game.getHeight()*3/6;
		break;
		}
	}
	
	public void setPeker(int peker){
		this.p = peker;
	}
	
	public GameStates getState(){
		switch(p){
		case 0:return GameStates.game;
		case 1:return GameStates.menue;
		}
		return null;
	}

	public float[] getPeker(){
		return this.peker;
	}
	
	public void setIsTouched(boolean s){
		this.isTouched = s;
	}
	
	public boolean getIsTouched(){
		return this.isTouched;
	}
	
	public void draw(Canvas canvas){
		this.bg.draw(canvas);
		
		canvas.drawText(overskrift, game.getWidth()/10, game.getHeight()/7, f);
		
		if (isTouched){
			canvas.drawBitmap(selectbar,0,getPeker()[1]+5,null);
		}
		canvas.drawText(start, game.getWidth()/8, game.getHeight()*2/6, f2);
		canvas.drawText(exit, game.getWidth()/8, game.getHeight()*3/6, f2);
	}
}
