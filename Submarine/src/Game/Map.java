package Game;

import java.util.ArrayList;

public class Map {
	
	// her er det 4 lister som inneholder de viktige komponenete i spillet components er alle de obj objektene som skal tegnes som
	// ikke er player objektet, monsters listen blir alle de obj på spillet som er en motstander av playeren (ikke noen funksjon ennå)
	// missiles listen består av alle angrepene som playeren gjør for å lett få tak i hvordan kollisjon skal behandles
	// text listen er en liste med objeter som viser damage når missiles treffer en motstander eller når playeren blir truffet
	// av en motstander(den siste biten er ikke implementer ennå)
	private ArrayList<obj> components = new ArrayList<obj>();
	private ArrayList<obj> monsters = new ArrayList<obj>();
	private ArrayList<obj> missiles = new ArrayList<obj>();
	private ArrayList<obj> monstermissiles = new ArrayList<obj>();
	private ArrayList<obj> remove = new ArrayList<obj>();
	
	private ArrayList<DisplayDamage> text = new ArrayList<DisplayDamage>();
	
	private boolean moving;
	private int direction;
	private Player player;
	
	public Map(Player p){
		player = p;
		addMonsters(new Monster(620,200,p,this));
	}
	
	public void tick(){
		remove.clear();
		//her sjekkes og behandles alle kollisjoner
		
		for(obj i:components){
			if (moving){
				if(i.getClass() == Bubble.class){
					((Bubble)i).setMoving(moving);
					i.setDirection(direction);
					i.tick();
				}
			}
			else if(i.getClass() == Bubble.class){
				((Bubble)i).setMoving(moving);
				i.tick();
			}
			
			//tar seg av de objekten som må removes
			if (i.getClass() == Bubble.class && ((Bubble) i).getDamage() ){
				remove.add(i);
			}
		}
		components.removeAll(remove);
		remove.clear();
		
		//behandler kollisjonene til missiles samtidig som den aktiverer dems tick funkjson
		for(obj i: missiles){
			if (moving){
				((Missile)i).setMoving(moving);
				i.setDirection(direction);
				i.tick();
			}else{i.tick();}
			
			((Missile) i).setMoving(moving);
			if(i.getX()>= 700 || ((Missile)i).getDamage()){
				//legger til damage på skjermen og sørger for at den blir fjernet fra mappet
				text.add(new DisplayDamage(((Missile)i).getDamagenumber(), i.getX() +32, i.getY()-(int)(Math.random()*5)*4));
				remove.add(i);
			}
		}
		components.removeAll(remove);
		missiles.removeAll(remove);
		remove.clear();
		
		// monsters ticks og behanlig av ødelagte monstre
		for(obj i: monsters){
			if(i.getClass() == Ice.class){
				if(moving){
					i.setDirection(direction);
					i.tick();
				}
			}
			else{i.tick();}
			if (i.getClass() == Ice.class && ((Ice) i).getDamage()){
				remove.add(i);
			}
			if (i.getClass() == Monster.class && ((Monster) i).getDestroyed()){
				remove.add(i);
			}
		}
		
		for(obj i:remove){
			if(i.getClass() == Monster.class){
				player.addToScore(50);
			}
			else if(i.getClass() == Ice.class){
				player.addToScore(5);
			}
			for(int q = 0; q< 40; q++){
				addStuff(new Bubble(i.getX() + 38 + (int)(Math.random()*60),i.getY() + 38 + (int)(Math.random()*60)));
			}
		}
		components.removeAll(remove);
		monsters.removeAll(remove);
		remove.clear();
		
		for(obj i:monstermissiles){
			if(moving){
				i.setDirection(direction);
				((MonsterMissile)i).setMoving(moving);
				i.tick();
			}
			else{
				((MonsterMissile)i).setMoving(moving);
				i.tick();
			}
			if(i.getX()<= 10 || ((MonsterMissile)i).getDamage()){
				remove.add(i);
			}
		}
		components.removeAll(remove);
		monstermissiles.removeAll(remove);
		
		//behandler damage text
		DisplayDamage d = null;
		for(DisplayDamage i: text){
			i.tick();
			if(i.getRemove()){
				d = i;
			}
		}
		text.remove(d);
	}
	
	public ArrayList<DisplayDamage> getText(){
		return this.text;
	}
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public void addStuff(obj a){
		components.add(a);
	}
	
	public void addMonsterMissile(obj a){
		this.monstermissiles.add(a);
		this.components.add(a);
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
	
	public ArrayList<obj> getMonsters(){
		return this.monsters;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
