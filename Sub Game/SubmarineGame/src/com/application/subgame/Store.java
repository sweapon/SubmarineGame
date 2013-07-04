package com.application.subgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.application.subgame.components.Background;
import com.application.subgame.components.Player;

public class Store {
	
	private Player player;
	private String damage,reloadtime,Healt;
	private int dmgmin,dmgmax,reload,healt;
	private Background bg;
	private Game game;
	private Paint paint;
	
	public Store(Player player, Game game){
		this.player = player;
		this.game = game;
		this.paint = new Paint();
		this.paint.setTextSize(25);
		this.paint.setColor(Color.WHITE);
		this.damage = "Damage";
		this.reloadtime = "Reloadtime";
		this.Healt = "Healt";
		this.reload = player.getReload();
		this.healt = player.getHealt();
		this.dmgmin = player.getBaseDamage();
		this.dmgmax = player.getVariation()+1;
		this.bg = new Background(0,0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.getResource(), R.drawable.background), game.getWidth(), game.getHeight(), true));		
	}
	
	public void update(){
		this.reload = player.getReload();
		this.healt = player.getHealt();
		this.dmgmin = player.getBaseDamage();
		this.dmgmax = player.getBaseDamage() + player.getVariation();
	}
	
	public void draw(Canvas canvas){
		this.bg.draw(canvas);
		
		canvas.drawText(Healt,game.getWidth()/2, game.getHeight()*3/6, paint);
		canvas.drawText(Integer.toString(healt) + " / " + Integer.toString(player.getFullHealt()),game.getWidth()*3/4, game.getHeight()*3/6, paint);
		
		canvas.drawText(damage,game.getWidth()/2, game.getHeight()*7/12, paint);
		canvas.drawText(Integer.toString(dmgmin) + " - " + Integer.toString(dmgmax),game.getWidth()*3/4, game.getHeight()*7/12, paint);
		
		canvas.drawText(reloadtime,game.getWidth()/2, game.getHeight()*4/6, paint);
		canvas.drawText(Integer.toString(reload),game.getWidth()*3/4, game.getHeight()*4/6, paint);
		
	}
}