package Game;

import Game.components.Ice;
import Game.components.Enemy;
import Game.components.Player;


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
		 newIce = (int) (Math.random()*15);
		 
		 if (newIce==0 && map.getComponents().size()< 128){
			 int newY = (int) (Math.random()*480);
			 int newX = (int) (Math.random()*640);
			 map.addMonsters(new Ice(640+ newX,newY));
		 }
		 
		 newMonster = (int) (Math.random()*50-game.getLevel());
		 
		 if (newMonster==0 && map.getMonsters().size()< 128 && !map.getMonsters().contains(Enemy.class)){
			 map.addMonsters(new Enemy(620,200,player,map,game));
		 }
	}
}
