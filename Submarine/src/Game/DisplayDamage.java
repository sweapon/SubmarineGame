package Game;

import java.awt.Color;

import acm.graphics.GLabel;

public class DisplayDamage {
	
	private int x,y,despawne;
	private boolean remove;
	private GLabel text;

	
	public DisplayDamage(int d,int x,int y){
		this.x = x;
		this.y = y;
		text = new GLabel(Integer.toString(d));
		this.despawne = 40;
		text.setColor(Color.yellow);

	}
	
	public void tick(){
		this.y = this.y - 1;
		this.despawne = this.despawne-1;
		if (this.despawne<0){this.remove = true;}
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean getRemove(){
		return this.remove;
	}
	
	public GLabel getText(){
		return this.text;
	}

}
