package Game;

import java.util.ArrayList;

import Game.components.DisplayPlayerStats;
import Game.components.Ice;
import Game.components.Missile;
import Game.components.Enemy;
import Game.components.EnemyMissile;
import Game.components.Player;

public class Game {
	
	private Player player;
	private Generator gen;
	private int level,position;
	private ArrayList<Map> world = new ArrayList<Map>();
	private DisplayPlayerStats dps;
	private GUI GUI;
	
	public Game(GUI gui){
		this.GUI = gui;
		this.position = 0;
		this.player = new Player(50,200);
		world.add(new Map(player));
		this.player.setMap(world.get(position));
		this.gen = new Generator(world.get(position),this.player,this);
		this.dps = new DisplayPlayerStats(this,player);
		this.level = 1;
	}
	
	public Map getMap(){
		if(player.getXx() > 5000){
			this.level = this.level +1;
			player.setXx(0);
			world.get(position).clearAll();
			// det neste som skal gj�res er � implementere storen :D
			//GUI.setState(GameStates.store);
		}
		return world.get(position);
	}
	
	public void tick(){
		if (player.getHealt() <= 0){
			GUI.setState(GameStates.menue);
			player.setHealt(player.getFullHealt());
			player.setScore(0);
			world.get(position).clearAll();
		}
		
		gen.tick();
		getMap().tick();
		player.tick();
		
		// sjekker om playeren koliderer med ice stuff
		for(obj i:getMap().getComponents()){
			dps.upDate();
			if (checkCollition(player,i) || checkCollition(i, player)){
				if( i.getClass() == Ice.class){
					((Ice) i).setDamage(true);
				}
				if (i.getClass() == EnemyMissile.class){
					((EnemyMissile)i).setDamage(true);
					System.out.println(((EnemyMissile) i).getDamagenumber());
					player.addToHealth(((EnemyMissile) i).getDamagenumber());
				}
			}
		}
		
		//sjekker om noen missile treffer noe med ice
		for(obj i: getMap().getMissile()){
			for(obj j:getMap().getMonsters()){
				if ((checkCollition(j,i) || checkCollition(i, j))){
					if(j.getClass() == Ice.class){
						((Missile) i).setDamage(true);
						((Ice) j).setDamage(true);
					}
					else if(j.getClass() == Enemy.class){
						((Missile) i).setDamage(true);
						((Enemy) j).setDamage(((Missile) i).getDamagenumber());
					}
				}
			}
		}
	}
	
	//sjekker om det er kollisjon mellom to objekter getBox gir har alle obj objektene og den retunerer alle de fire hj�rnene
	//som et objet best�r av og p� den m�ten sjekker om obj b har noen av sine hj�rner inne i obj a
	public boolean checkCollition(obj a,obj b){
			if (((a.getBox()[0] < b.getBox()[2] && a.getBox()[0] > b.getBox()[0])
				|| (a.getBox()[2] > b.getBox()[0] && a.getBox()[2] < b.getBox()[2]))
				&& ((a.getBox()[1] < b.getBox()[3] && a.getBox()[1] > b.getBox()[1])
				||	(a.getBox()[3] > b.getBox()[1] && a.getBox()[3] < b.getBox()[3])))
				{
				return true;
			}
		return false;
	}
	
	public void reset(){
		this.player.setScore(0);
		this.player.setHealt(this.player.getFullHealt());
		this.player.setXx(0);
		this.level = 1;
	}
	
	public int getLevel(){
		return this.level;
	}
	public Player getPlayer(){
		return this.player;
	}
	
	public DisplayPlayerStats getDps(){
		return this.dps;
	}

}
