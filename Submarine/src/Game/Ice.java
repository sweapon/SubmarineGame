package Game;

public class Ice implements obj{
	
	private String img = "ice.png";
	private int x,y,direction;
	private boolean damage;
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public void setDamage(boolean t){
		damage = t;
	}
	
	public Ice(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int[] getBox(){
		int [] a = {x,y,x+128,y+128};
		return a;
	}
	
	public String getImg(){
		return this.img;
	}
	
	public boolean getDamage(){
		return this.damage;
	}
	
	public void tick(){
		switch (direction){
		case 0:this.x = x +3; break;
		case 1:this.x = x -3; break;
		}
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
