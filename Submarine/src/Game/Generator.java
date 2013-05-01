package Game;


public class Generator {
	
	 int newIce;	
	 private Map map;
	 private Player player;
	 
	 public Generator(Map map, Player player){
		 this.map = map;
		 this.player = player;
	 }
	 
	 public void tick(){
		 newIce = (int) (Math.random()*15);
		 
		 if (newIce==0 && map.getComponents().size()< 128){
			 int newY = (int) (Math.random()*480);
			 int newX = (int) (Math.random()*640);
			 map.addMonsters(new Ice(640+ newX,newY));
		 }
		 
		 newIce = (int) (Math.random()*50);
		 
		 if (newIce==0 && map.getMonsters().size()< 128 && !map.getMonsters().contains(Monster.class)){
			 map.addMonsters(new Monster(620,200,player,map));
		 }
	}
}
