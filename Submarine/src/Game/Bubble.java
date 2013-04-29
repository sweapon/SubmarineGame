package Game;

public class Bubble implements obj {

	private String img = "bubble.png";
	private int x,y,direction,despawne;
	private boolean damage;
	private boolean moving;
	
	public void setDirection(int d){
		this.direction = d;
	}
	
	public void setDamage(boolean t){
		damage = t;
	}
	
	public Bubble(int x, int y){
		this.despawne = (int) (Math.random()*20) + 5;
		this.x = x;
		this.y = y;
	}
	
	public void setMoving(boolean m){
		this.moving = m;
	}
	
	public int[] getBox(){
		int [] a = {0,0,0,0};
		return a;
	}
	
	public String getImg(){
		return this.img;
	}
	
	public boolean getDamage(){
		return this.damage;
	}
	
	public void tick(){
		this.y = this.y - 1;
		this.despawne = this.despawne-1;
		if (this.moving){
			switch (direction){
			case 0:this.x = x +3; break;
			case 1:this.x = x -3; break;
			}
		}
		if (this.despawne<0){this.damage = true;}
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
