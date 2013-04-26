package Game;

public class Game {
	
	private Player player;
	private Generator gen;
	private int level;
	
	public void Game(){
		this.player = new Player();
		this.gen = new Generator();
		
	}
	
	public void tick(){
		
	}
	
	public Player getPlayer(){
		return this.player;
	}

}
