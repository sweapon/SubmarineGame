package Game;

import java.awt.event.KeyEvent;

public class Player {
	
	private int x,y,dx,dy,healt,damage,weapon;
	private String img;
	
	
	public void Player(int x, int y){
		this.x = x;
		this.y = y;
	}

	
	public void move(char c){
		if (c == KeyEvent.VK_1){
			dx = -3;
		}
		else if (c == KeyEvent.VK_3){
			dx = 3;
		}
		else if (c == KeyEvent.VK_2){
			dy = 3;
		}
		else if (c == KeyEvent.VK_5){
			dy = -3;
		}
			
	}
	
	
	//getters og setters :D
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
	
}
