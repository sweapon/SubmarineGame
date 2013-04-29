package Game;

public class Missile implements obj {
	
	
	private String img = "missile.png";
	private int x,y,direction;
	private boolean moving,damage;
	
	//constructor
	public Missile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setDamage(boolean d){
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
	
	
	public void tick(){
		this.x = x +5;
		if (moving){
			switch (direction){
			case 0:this.x = x +3; break;
			case 1:this.x = x -3; break;
			}
		}
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
