package com.application.subgame;

import java.util.ArrayList;


public class Map {
	
	// her er det 4 lister som inneholder de viktige komponenete i spillet components er alle de obj objektene som skal tegnes som
	// ikke er player objektet, monsters listen blir alle de obj p� spillet som er en motstander av playeren (ikke noen funksjon enn�)
	// missiles listen best�r av alle angrepene som playeren gj�r for � lett f� tak i hvordan kollisjon skal behandles
	// text listen er en liste med objeter som viser damage n�r missiles treffer en motstander eller n�r playeren blir truffet
	// av en motstander(den siste biten er ikke implementer enn�)
	private ArrayList<obj> components = new ArrayList<obj>();
	private ArrayList<obj> monsters = new ArrayList<obj>();
	private ArrayList<obj> missiles = new ArrayList<obj>();
	private ArrayList<obj> monstermissiles = new ArrayList<obj>();
	private ArrayList<obj> remove = new ArrayList<obj>();
	
	private ArrayList<DisplayDamage> text = new ArrayList<DisplayDamage>();
	
	private int direction;
	private Player player;
	private Background bg;
	
	public Map(Player p){
		this.direction = 1;
		player = p;
		this.bg = new Background(0,0);
	}
	
	public void tick(){
		remove.clear();
		//her sjekkes og behandles alle kollisjoner
		
		for(obj i:components){
			if (player.getMoving()){
				if(i.getClass() == Bubble.class){
					((Bubble)i).setMoving(player.getMoving());
					i.setDirection(direction);
					i.tick();
				}
			}
			else if(i.getClass() == Bubble.class){
				((Bubble)i).setMoving(player.getMoving());
				i.tick();
			}
			
			//tar seg av de objekten som m� removes
			if (i.getClass() == Bubble.class && ((Bubble) i).getDamage() ){
				remove.add(i);
			}
		}
		components.removeAll(remove);
		remove.clear();
		
		//behandler kollisjonene til missiles samtidig som den aktiverer dems tick funkjson
		for(obj i: missiles){
			if (player.getMoving()){
				((Missile)i).setMoving(player.getMoving());
				i.setDirection(direction);
				i.tick();
			}else{i.tick();}
			
			((Missile) i).setMoving(player.getMoving());
			if(i.getX()>= 700 || ((Missile)i).getDamage()){
				//legger til damage p� skjermen og s�rger for at den blir fjernet fra mappet
				if (((Missile)i).getDamagenumber() != 0){
				text.add(new DisplayDamage(((Missile)i).getDamagenumber(), i.getX() +32, i.getY()-(int)(Math.random()*5)*4,true));
				}
				remove.add(i);
			}
		}
		components.removeAll(remove);
		missiles.removeAll(remove);
		remove.clear();
		
		// monsters ticks og behanlig av �delagte monstre
		for(obj i: monsters){
			if(i.getClass() == Ice.class){
				if(player.getMoving()){
					i.setDirection(direction);
					i.tick();
				}
			}
			else{i.tick();}
			if (i.getClass() == Ice.class && ((Ice) i).getDamage()){
				remove.add(i);
			}
			if (i.getClass() == Enemy.class && ((Enemy) i).getDestroyed()){
				remove.add(i);
			}
		}
		
		//lager bobler n�r monstre d�r :D burde skirve om til en universal formel!
		for(obj i:remove){
			if(i.getClass() == Enemy.class){
				player.addToScore(((Enemy)i).getScore());
				for(int q = 0; q< 20; q++){
					addStuff(new Bubble(i.getX() + (int)(Math.random()*64),i.getY()  + (int)(Math.random()*32)));
				}
			}
			else if(i.getClass() == Ice.class){
				player.addToScore(5);
				for(int q = 0; q< 20; q++){
					addStuff(new Bubble(i.getX() + 38 + (int)(Math.random()*60),i.getY() + 38 + (int)(Math.random()*60)));
				}
			}
		}
		components.removeAll(remove);
		monsters.removeAll(remove);
		remove.clear();
		
		for(obj i:monstermissiles){
			if(player.getMoving()){
				i.setDirection(direction);
				((EnemyMissile)i).setMoving(player.getMoving());
				i.tick();
			}
			else{
				i.tick();
			}
			((EnemyMissile)i).setMoving(player.getMoving());
			if(i.getX()<= 10 || ((EnemyMissile)i).getDamage()){
				if (((EnemyMissile)i).getDamage()){
				text.add(new DisplayDamage(((EnemyMissile)i).getDamagenumber(), i.getX() -32, i.getY()-(int)(Math.random()*5)*4,false));		
				}
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
	
	public Background getBg(){
		return this.bg;
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
	
	public void clearAll(){
		this.components.clear();
		this.missiles.clear();
		this.monsters.clear();
		this.monstermissiles.clear();
		this.text.clear();
	}

//	public void setMoving(boolean moving) {
//		this.moving = moving;
//	}

}
