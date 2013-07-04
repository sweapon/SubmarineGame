package com.application.subgame.components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.application.subgame.Game;
import com.application.subgame.MainActivity;
import com.application.subgame.Map;
import com.application.subgame.R;



public class Player implements obj{
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private int x,y,dx,dy,healt,basedamage,xx,readytofire,reload,score,fullHealt,direction,variation,speed,level,experience;
	private int img = R.drawable.submarine;
	private boolean moving,shooting,attack;
	private Map map;
	private Game game;
	private Bitmap image;
	private Attack weapon;
	
	public Player(int x, int y, Game game){
		this.game = game;
		this.moving = true;
		this.dx = 3;
		
		this.direction = 1;
		this.x = x;
		this.y = y;
		this.score = 0;
		this.fullHealt = 100;
		this.variation = 50;
		this.basedamage = 100;
		this.reload = 15;
		this.readytofire = 0;
		this.speed = 6;
		this.level = 1;
		this.experience = 0;
		
		this.image = BitmapFactory.decodeResource(game.getResource(), R.drawable.submarine);
		this.attack = false;
		this.weapon = new HomingMissileAttack(this.game,this.basedamage,this.variation,1,this);
		this.healt = fullHealt;
	}

	
	public void move(int[] keys){
		if (keys[0] == 1){
			dx = -3;
			direction = 0;
			moving = true;
		}
		if (keys[2] == 1){
			dx = 3;
			direction = 1;
			moving = true;
		}
		if (keys[1] == 1){
			dy = speed;
		}
		if (keys[3] == 1){
			dy = -speed;
		}
		map.setDirection(1);
	}
	
	public void stop(int[] keys){
		if(keys[0] == 0 && keys[2] == 0){dx = 0;moving = false;}
		if(keys[1] == 0 && keys[3] == 0){dy = 0;}
	}
	
	public boolean getMoving(){
		return this.moving;
	}
	
	public void setShooting(boolean s){
		this.shooting = s;
	}
	
	public void tick(){
		if (shooting && readytofire== 0){
			this.map.addMissiles(new Missile(x+100, y+40,basedamage,variation,BitmapFactory.decodeResource(game.getResource(),R.drawable.missile)));
			readytofire = reload;
		}
		
		if(attack) weapon.attack();attack = false;
		
		if (dx != 0){
			xx = xx + dx;
		}
		if (dy != 0){
			//s�rger for at playeren ikke g�r utenfor skjermen :)
			if(!((y + dy)<0-15) && !((y + dy)>game.getHeight()-64) && !((y + dy)<16)) {
				y = y + dy;
			}
		}
		if (readytofire > 0){readytofire = readytofire-1;}
		
		//lager bobbler :)
		this.map.addStuff(new Bubble(this.x-(int) (Math.random()*10),this.y+36+(int)(Math.random()*10),BitmapFactory.decodeResource(game.getResource(),R.drawable.bubble)));
	}
	
	public void attack(){
		this.weapon.attack();
	}
	
	public void addToScore(int s){
		this.score = this.score + s;
	}
	
	public void addToHealth(int h){
		this.healt = this.healt - h;
	}
	
	public String getSave(){
		return "basedamage " + Integer.toString(basedamage) + "#"
				+ "variation " + Integer.toString(variation) + "#"
				+ "fullhealt " + Integer.toString(fullHealt) + "#"
				+ "speed " + Integer.toString(speed) + "#"
				+ "level " + Integer.toString(level) + "#"
				+ "experience " + Integer.toString(experience);
	}
	
	public void load(String load){

		String[] loadArray = load.split("#");
		String[] info;
		for(int i = 0; i < loadArray.length;i++){
			loadArray[i].trim();
			info = loadArray[i].split(" ");
			Log.d(TAG,info[0] + info[1]);
			if(info[0].equals("reload")) this.reload = Integer.parseInt(info[1]);
			else if(info[0].equals("basedamage")) this.basedamage = Integer.parseInt(info[1]);
			else if(info[0].equals("variation")) this.variation = Integer.parseInt(info[1]);
			else if(info[0].equals("fullhealt")) this.fullHealt = Integer.parseInt(info[1]);
			else if(info[0].equals("speed")) this.speed = Integer.parseInt(info[1]);
			else if(info[0].equals("level")) this.level = Integer.parseInt(info[1]);
			else if(info[0].equals("experience")) this.experience = Integer.parseInt(info[1]);
		}
	}
	
	public int nextLevel(int level){
		return (level)*100;
	}
	
	
	//getters og setters :D
	public void setScore(int score){
		this.score = score;
	}
	
	public int getFullHealt(){
		return this.fullHealt;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setMap(Map m){
		this.map = m;
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public int[] getBox(){
		int [] a = {this.x,this.y+15,x+this.image.getWidth(),y+this.image.getHeight()};
		return a;
	}
	
	public int getImg(){
		return this.img;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getHealt() {
		return healt;
	}

	public void setHealt(int healt) {
		this.healt = healt;
	}

	public int getBaseDamage() {
		return basedamage;
	}

	public void setDamage(int damage) {
		this.basedamage = damage;
	}
	
	public int getXx(){
		return this.xx;
	}
	
	public void setXx(int xx){
		this.xx = xx;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setExperience(int exp){
		this.experience = this.experience + exp;
		//soudokode for � sette level til riktg level trenger mer koding for � gj�re det ordentlig ^^
		if(this.experience < 100){
			this.level = 1;
		}else this.level = experience/100;
		Log.d(TAG,Integer.toString(this.level));
	}
	
	public int getExperience(){
		return this.experience;
	}

	public void setDirection(int d) {
	}
	
	public void ressetmovement(){
		this.dy = 0;
	}
	
	public int getReload(){
		return this.reload;
	}
	
	public int getVariation(){
		return this.variation;
	}
	
	public void setAttack(boolean attack){
		this.attack = attack;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, this.x,this.y,null);
	}
}
