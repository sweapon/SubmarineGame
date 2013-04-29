package Game;

import java.util.ArrayList;

public class Game {
	
	private Player player;
	private Generator gen;
	private int level,position;
	private ArrayList<Map> world = new ArrayList<Map>();
	
	public Game(){
		world.add(new Map());

		this.position = 0;
		this.player = new Player(50,10);
		this.player.setMap(world.get(position));
		this.gen = new Generator(world.get(position));
		
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
			if ((checkCollition(player,i) || checkCollition(i, player)) && i.getClass() == Ice.class){
			System.out.println("kolisjon");
			((Ice) i).setDamage(true);
			}
		}
		
		//sjekker om noen missile treffer noe med ice
		for(obj i: getMap().getMissile()){
			for(obj j:getMap().getComponents()){
				if ((checkCollition(j,i) || checkCollition(i, j)) && j.getClass() == Ice.class){
				System.out.println("kolisjon");
				((Missile) i).setDamage(true);
				((Ice) j).setDamage(true);
				}
			}
		}
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	//sjekker om det er kollisjon mellom to objekter
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

}
