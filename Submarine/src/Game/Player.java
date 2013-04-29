package Game;



public class Player implements obj{
	
	private int x,y,dx,dy,healt,damage,weapon,xx,readytofire,reload;
	private String img = "Submarine.png";
	private boolean moving,shooting;
	private int direction;
	private Map map;
	
	
	public Player(int x, int y){
		this.reload = 5;
		this.readytofire = 0;
		this.direction = 0;
		this.x = x;
		this.y = y;
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
			dy = 3;
		}
		if (keys[3] == 1){
			dy = -3;
		}
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
			this.map.addMissiles(new Missile(x+100, y+40));
			readytofire = reload;
		}
		if (dx != 0){
			xx = xx + dx;
		}
		if (dy != 0){
			y = y + dy;
		}
		if (readytofire > 0){readytofire = readytofire-1;}
		
		//lager bobbler :)
		this.map.addStuff(new Bubble(this.x-(int) (Math.random()*10),this.y+40+((int)Math.random()*10)));
		
	}
	
	
	//getters og setters :D
	
	public void setMap(Map m){
		this.map = m;
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public int[] getBox(){
		int [] a = {this.x,this.y+15,x+128,y+64};
		return a;
	}
	
	public String getImg(){
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}


	@Override
	public void setDirection(int d) {
		// TODO Auto-generated method stub
		
	}
	
}
