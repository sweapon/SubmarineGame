package Game;

public interface obj {
	
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public void tick();
	public String getImg();
	public void setDirection(int d);
	public int[] getBox();

}
