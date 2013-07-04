package com.application.subgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;



public class DisplayPlayerStats {
	
	//private Font f = new Font("Impact",Font.BOLD, 32);
	private Player player;
	private Game game;
	private String score,level,textScore,textLevel;
	private float[] playerHealt = {20,10,350,40},container = {20,10,350,40};
	private Paint paint,paintHealt;

	
	public DisplayPlayerStats(Game g, Player p){
		paint = new Paint();
		paint.setTextSize(25);
		paint.setColor(Color.BLACK);
		
		paintHealt = new Paint();
		paintHealt.setColor(Color.GREEN);
		paintHealt.setStyle(Style.FILL);
		
		this.player = p;
		this.game = g;
		this.textLevel = "Levle";
		this.textScore = "Score:";
		upDate();
	}
	
	public void upDate(){
		score = Integer.toString(player.getScore());
		level=Integer.toString(game.getLevel());
		playerHealt[2] = (float)((double)350/player.getFullHealt())*player.getHealt();
	}
	public String getScore(){
		return this.score;
	}
	public String getLevel(){
		return this.level;
	}
	public String getTextScore(){
		return this.textScore;
	}
	public String getTextLevel(){
		return this.textLevel;
	}
	public float[] getPlayerHealt(){
		return this.playerHealt;
	}
	public float[] getcontainer(){
		return this.container;
	}
	
	public Paint getPaint(){
		return this.paint;
	}
	public Paint getPaintHealt(){
		return this.paintHealt;
	}
}
