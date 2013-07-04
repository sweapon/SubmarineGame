package Game.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Game.Game;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class DisplayPlayerStats {
	
	//private Font f = new Font("Impact",Font.BOLD, 32);
	private Font l = new Font("Impact",Font.BOLD, 26);
	private Player player;
	private Game game;
	private GLabel score,level,textScore,textLevel;
	private GRect playerHealt,container;
	private ArrayList<GObject> stats = new ArrayList<GObject>();
	
	public DisplayPlayerStats(Game g, Player p){
		this.player = p;
		this.game = g;
		this.container = new GRect(20, 10, 200, 20);
		this.playerHealt = new GRect(20, 10, 200, 20);
		stats.add(container);
		
		playerHealt.setFillColor(Color.green);
		playerHealt.setFilled(true);
		stats.add(playerHealt);
		
		textScore = new GLabel("Score");
		textScore .setLocation(350, 30);
		textScore .setFont(l);
		stats.add(textScore);
		
		score = new GLabel(Integer.toString(0));
		score.setLocation(430, 30);
		score.setFont(l);
		stats.add(score);
		
		textLevel = new GLabel("Level");
		textLevel.setLocation(20,60);
		textLevel.setFont(l);
		stats.add(textLevel);
		
		level = new GLabel(Integer.toString(1));
		level.setLocation(90,60);
		level.setFont(l);
		stats.add(level);
		
		upDate();
	}
	
	public void upDate(){
		score.setLabel(Integer.toString(player.getScore()));
		level.setLabel(Integer.toString(game.getLevel()));
		playerHealt.setSize(((double)200/player.getFullHealt())*player.getHealt(), 20);
	}
	
	public ArrayList<GObject> getList(){
		return this.stats;
	}
	
	

}
