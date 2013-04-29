package Game;

import java.util.ArrayList;

public class Map {
	
	private ArrayList<obj> components = new ArrayList<obj>();
	private ArrayList<obj> monsters = new ArrayList<obj>();
	private ArrayList<obj> missiles = new ArrayList<obj>();
	private boolean moving;
	private int direction;
	
	public void tick(){
		
		//her sjekkes og behandles alle kollisjoner
		obj b = null;
		obj j = null;
		for(obj i:components){
			if (moving){
				if(i.getClass() == Bubble.class){((Bubble)i).setMoving(moving);}
				i.setDirection(direction);
				i.tick();
			}
			else if(i.getClass() == Bubble.class){
				//((Bubble)i).setMoving(moving);
				i.tick();
			}
			if (i.getClass() == Bubble.class && ((Bubble) i).getDamage() ){
				b = i;
			}
			if (i.getClass() == Ice.class && ((Ice) i).getDamage()){
				System.out.println("ahkjhfslajfjahlsfddshkl");
				j = i;
			}
		}
		//((Ice) i).getDamage() || 
		
		components.remove(j);
		components.remove(b);
		if (j != null){System.out.println(j.getClass());}
		
		//behandler kollisjonene til missiles
		obj s = null;
		for(obj i: missiles){
			i.tick();
			((Missile) i).setMoving(moving);
			if(i.getX()>= 700 || ((Missile)i).getDamage()){
				s = i;
			}
		}
		components.remove(s);
		missiles.remove(s);
	}
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public void addStuff(obj a){
		components.add(a);
	}
	
	public void addMonsters(obj a){
		this.monsters.add(a);
		this.components.add(a);
	}
	
	public void addMissiles(obj a){
		this.missiles.add(a);
		this.components.add(a);
	}
	
	public ArrayList<obj> getMissile(){
		return this.missiles;
	}
	
	public ArrayList<obj> getComponents(){
		return this.components;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
