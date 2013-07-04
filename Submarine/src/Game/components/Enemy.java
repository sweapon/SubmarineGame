package Game.components;

import Game.Map;
import Game.obj;
import Game.Game;

public class Enemy implements obj {
	
	private int x,y,speed,health,direction,redytofrie,relode,xgoto,ygoto,score;
	private String img = "monster.png";
	private Player player;
	private Map map;
	private boolean destroyed, xreached,yreached;
	private Game game;

	
	public Enemy(int x, int y, Player player, Map map,Game game){
		this.game = game;
		this.speed = 3;
		this.redytofrie = 180;
		this.relode = 60;
		this.x = x;
		this.y = y;
		this.player = player;
		this.map = map;
		this.health = 100 + (game.getLevel()-1)*50;
		this.xreached = true;
		this.yreached = true;
		this.score = 50*game.getLevel();
	}
	
	public void tick() {
		
		if (health <= 0){
			this.destroyed = true;
		}
		
		if(x > 580){
			x = x - speed;
		}
		if (redytofrie == 0){
			if(y < player.getY()+20){
				y = y + speed;
			}
			else if(y > player.getY()+40){
				y = y - speed;
			}
			
		}
		else{
			if(xreached && yreached){
				this.xgoto = (int)(Math.random()*300)+250;
				this.ygoto = (int)(Math.random()*480)+1;
				xreached = false;
				yreached = false;
			}
			if(x < xgoto && !xreached){
				x = x + speed;
			}
			else if(x > xgoto && !xreached){
				x = x - speed;
			}
			if(y < ygoto && !yreached){
				y = y + speed;
			}
			else if(y > ygoto && !yreached){
				y = y - speed;
			}
			if(x < xgoto + 10 && x >xgoto-10){
				xreached = true;
			}
			if(y < ygoto + 10 && y >ygoto-10){
				yreached = true;
			}
		}
		
		if(redytofrie == 0 && y < player.getY()+45 && y > player.getY()){
			redytofrie = relode + (int)(Math.random()*20);
			this.map.addMonsterMissile(new EnemyMissile(x,y+15,game.getLevel()));
			this.xgoto = (int)(Math.random()*300)+250;
			this.ygoto = (int)(Math.random()*440)+1;
			xreached = false;
			yreached = false;
		}
		if(redytofrie > 0){
			this.redytofrie = this.redytofrie - 1;
		}
		
		
		if (player.getMoving()){
			switch (direction){
			//case 0:this.x = x +3; break;
			case 1:this.x = this.x -3; break;
			}
		}
		//this.map.addStuff(new Bubble(this.x+60+(int) (Math.random()*10),this.y+10+(int)(Math.random()*10)));
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setDamage(int d){
		this.health = this.health-d;
	}
	
	public boolean getDestroyed(){
		return this.destroyed;
	}
	
	public int[] getBox() {
		int [] a = {x,y,x+64,y+32};
		return a;
	}

	
	public int getX() {
		return this.x;
	}

	
	public int getY() {
		return this.y;
	}

	
	public void setX(int x) {
		this.x = x;
	}

	
	public void setY(int y) {
		this.y = y;
	}

	public String getImg() {
		return img;
	}

	@Override
	public void setDirection(int d) {
		this.direction = d;
	}

}
