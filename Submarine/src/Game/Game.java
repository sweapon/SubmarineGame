package Game;

import java.util.ArrayList;

public class Game {
	
	private Player player;
	private Generator gen;
	private int level,position;
	private ArrayList<Map> world = new ArrayList<Map>();
	private DisplayPlayerStats dps;
	
	public Game(){

		this.position = 0;
		this.player = new Player(50,200);
		world.add(new Map(player));
		this.player.setMap(world.get(position));
		this.gen = new Generator(world.get(position),this.player);
		this.dps = new DisplayPlayerStats(this,player);
		this.level = 1;
	}
	
	public Map getMap(){
		return world.get(position);
	}
	
	public void tick(){
		gen.tick();
		getMap().setMoving(player.getMoving());
		getMap().setDirection(player.getDirection());
		getMap().tick();
		player.tick();
		
		// sjekker om playeren koliderer med ice stuff
		for(obj i:getMap().getComponents()){
			dps.upDate();
			if (checkCollition(player,i) || checkCollition(i, player)){
				if( i.getClass() == Ice.class){
					((Ice) i).setDamage(true);
				}
				if (i.getClass() == MonsterMissile.class){
					player.addToHealth(5);
					((MonsterMissile)i).setDamage(true);
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
					else if(j.getClass() == Monster.class){
						((Missile) i).setDamage(true);
						((Monster) j).setDamage(((Missile) i).getDamagenumber());
					}
				}
			}
		}
	}
	
	//sjekker om det er kollisjon mellom to objekter getBox gir har alle obj objektene og den retunerer alle de fire hjørnene
	//som et objet består av og på den måten sjekker om obj b har noen av sine hjørner inne i obj a
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
