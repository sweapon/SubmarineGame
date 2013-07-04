package Game.components;

import Game.obj;

public class EnemyMissile implements obj {

	private String img = "monstermissile.png";
	private int x,y,direction,Damagenumber,speed,level;
	private boolean moving,damage;
	
	//constructor
	public EnemyMissile(int x, int y,int level){
		this.level = level;
		this.speed = 8;
		this.x = x;
		this.y = y;
	}
	
	public void tick(){
		this.x = x - speed;
		if (moving){
			switch (direction){
			case 0:this.x = x +3; break;
			case 1:this.x = x -3; break;
			}
		}
	}
	
	public int getDamagenumber(){
		return Damagenumber;
	}
	
	public void setDamage(boolean d){
		this.Damagenumber = (int)(Math.random()*20)+20 + 10*(level-1);
		this.damage = d;
	}
	
	public boolean getDamage(){
		return this.damage;
	}
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public int[] getBox(){
		int [] a = {x,y,x+32,y+16};
		return a;
	}
	
	public String getImg(){
		return this.img;
	}
	
	public void setMoving(boolean a){
		this.moving = a;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
		
	}

}