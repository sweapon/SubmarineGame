package com.application.subgame;

import android.graphics.BitmapFactory;

import com.application.subgame.components.Enemy;
import com.application.subgame.components.Ice;
import com.application.subgame.components.Player;

public class Generator {
	
	 int newIce, newMonster;	
	 private Map map;
	 private Player player;
	 private Game game;
	 
	 public Generator(Map map, Player player, Game game){
		 this.map = map;
		 this.player = player;
		 this.game = game;
	 }
	 
	 public void tick(){
		 if (game.getGenerate()){
			 newIce = (int) (Math.random()*15);
			 
			 if (newIce==0 && map.getComponents().size()< 128){
				 int newY = (int) (Math.random()*game.getHeight());
				 int newX = (int) (Math.random()*game.getWidth());
				 if (newY > game.getHeight()-128){
					 newY = game.getHeight()-128;
				 }
				 map.addMonsters(new Ice(game.getWidth() + newX,newY,BitmapFactory.decodeResource(game.getResource(), R.drawable.ice)));
			 }
			 newMonster = (int) (Math.random()*50-game.getLevel());
			 
			 if (newMonster==0 && map.getMonsters().size()< 128 && !map.getMonsters().contains(Enemy.class)){
				 map.addMonsters(new Enemy(game.getWidth(),game.getHeight()/2,player,map,game,BitmapFactory.decodeResource(game.getResource(), R.drawable.monster)));
			 }
		 }
	}
}
