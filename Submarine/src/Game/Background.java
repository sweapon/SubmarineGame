package Game;

public class Background {

	private int x,y;
	private String img = "Background.png";
	
	public Background(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String getImg(){
		return this.img;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}
