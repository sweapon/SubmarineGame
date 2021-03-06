package com.application.subgame;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.application.subgame.components.DisplayDamage;
import com.application.subgame.components.Enemy;
import com.application.subgame.components.EnemyMissile;
import com.application.subgame.components.Ice;
import com.application.subgame.components.Missile;
import com.application.subgame.components.Player;
import com.application.subgame.components.obj;

public class Game {
	
	private Player player;
	private Generator gen;
	private int level,position;
	private ArrayList<Map> world = new ArrayList<Map>();
	private DisplayPlayerStats dps;
	private MainGamePanel GUI;
	private int widht,height;
	private boolean generate = false;
	
	public Game(MainGamePanel gui,Rect rect){
		this.GUI = gui;
		this.widht = rect.width();
		this.height = rect.height();
		this.position = 0;
		this.player = new Player(50,200,this);
		world.add(new Map(player,this));
		this.player.setMap(world.get(position));
		this.gen = new Generator(world.get(position),this.player,this);
		this.dps = new DisplayPlayerStats(this,player);
		this.level = 1;
	}
	
	public Map getMap(){
		return world.get(position);
	}
	
	public void tick(){
		if(player.getXx() > 1000*level){
			this.level = this.level +1;
			player.setXx(0);
			world.get(position).clearAll();
			//det neste som skal gj�res er � implementere storen :D
			GUI.setState(GameStates.store);
		}
		if (player.getHealt() <= 0){
			player.setExperience(player.getScore()/10);
			GUI.setState(GameStates.gameover);
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
			for(obj j:getMap().getPotentionalMonsters()){
				if ((checkCollition(j,i) || checkCollition(i, j)) && (j.getX() >0 && j.getX()<widht)){
					if(j.getClass() == Ice.class){
						((Missile) i).setDamage(true);
						((Ice) j).setDamage(true);
					}
					else if(j.getClass() == Enemy.class){
						((Missile) i).setDamage(true);
						((Enemy) j).setDamage(((Missile) i).getDamagenumber());
					}
					break;
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
		this.player.setShooting(false);
		this.player.setY(height/2 - 32);
		this.level = 1;
		this.player.ressetmovement();
	}
	
	public int getWidth(){
		return this.widht;
	}
	
	public int getHeight(){
		return this.height;
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
	
	public boolean getGenerate(){
		return this.generate;
	}
	
	public void setGenerate(boolean g){
		this.generate = g;
	}
	
	public Resources getResource(){
		return this.GUI.getResources();
	}
	
	public void draw(Canvas canvas){
		//printer f�rst backgrunnen
		getMap().getBg().draw(canvas);
		//skriver ut alle bildene som er i gamet p� det gitte mappet
		getPlayer().draw(canvas);
		for(obj i:getMap().getComponents()){
			if(i.getX() <widht && i.getX()>-80){
				i.draw(canvas);
			}
		}
		//skriver ut damage text som er i gamet p� det gitte mappet
		for(DisplayDamage i:getMap().getText()){
			canvas.drawText(i.getText(), i.getX(), i.getY(), i.getPaint());
		}
	}

}
