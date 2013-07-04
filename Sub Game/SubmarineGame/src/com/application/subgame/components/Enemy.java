package com.application.subgame.components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.application.subgame.Game;
import com.application.subgame.Map;
import com.application.subgame.R;


public class Enemy implements obj {
	
	private int x,y,speed,health,direction,redytofrie,relode,xgoto,ygoto,score,dx,dy;
	private int img = R.drawable.monster;
	private Player player;
	private Map map;
	private boolean destroyed, xreached,yreached;
	private Game game;
	private Bitmap bitmap;

	
	public Enemy(int x, int y, Player player, Map map,Game game,Bitmap b){
		this.game = game;
		this.speed = 6;
		this.x = x;
		this.y = y;
		this.player = player;
		this.map = map;
		this.health = 100 + (game.getLevel()-1)*50;
		this.xreached = true;
		this.yreached = true;
		this.score = 50*game.getLevel();
		this.redytofrie = 60 - game.getLevel()+1;
		this.relode = 60 -game.getLevel()+1;
		this.bitmap = b;
		this.dx = 0;
		this.dy = 0;
	}
	
	public void tick() {
		
		if (health <= 0){
			this.destroyed = true;
		}
		
		if(x > 720){
			x = x - speed;
		}
		if (redytofrie == 0){
			if(y < player.getY()+20){
				if (dy < speed){
					dy = dy + 2;
				}
			}
			else if(y > player.getY()+40){
				if (dy > -speed){
					dy = dy - 2;
				}
			}
			this.dx = 0;
			this.x = this.x + dx;
			this.y = this.y + dy;
			
		}
		else{
			if(xreached && yreached){
				this.xgoto = (int)(Math.random()*(game.getWidth()-300))+250;
				this.ygoto = (int)(Math.random()*game.getHeight())+1 - 32;
				xreached = false;
				yreached = false;
			}
			if(x < xgoto + 10 && x >xgoto-10){
				xreached = true;
			}
			if(y < ygoto + 10 && y >ygoto-10){
				yreached = true;
			}
//			//y
//			if(ygoto > this.y && !yreached){
//				if (dy < speed){
//					dy = dy + 2;
//				}
//			}
//			else if(ygoto< this.y && !yreached){
//				if (dy > -speed){
//					dy = dy - 2;
//				}
//			}
//			//x
//			if(xgoto > this.x && !xreached){
//				if (dx < speed){
//					dx = dx + 2;
//				}
//			}
//			else if(xgoto < this.x && !xreached){
//				if (dx > -speed){
//					dx = dx - 2;
//				}
//			}
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
			this.x = this.x + dx;
			this.y = this.y + dy;
		}
		
		if(redytofrie == 0 && y < player.getY()+45 && y > player.getY()){
			redytofrie = relode + (int)(Math.random()*20);
			this.map.addMonsterMissile(new EnemyMissile(x,y+15,game.getLevel(),BitmapFactory.decodeResource(game.getResource(), R.drawable.monstermissile)));
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

	public int getImg() {
		return img;
	}

	@Override
	public void setDirection(int d) {
		this.direction = d;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(this.bitmap, this.x,this.y, null);
	}

}
