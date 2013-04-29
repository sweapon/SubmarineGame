package Game;


public class Generator {
	
	 int newIce;	
	 private Map map;
	 
	 public Generator(Map map){
		 this.map = map;
	 }
	 
	 public void tick(){
		 newIce = (int) (Math.random()*15);
		 
		 if (newIce==0 && map.getComponents().size()< 128){
			 int newY = (int) (Math.random()*480);
			 int newX = (int) (Math.random()*640);
			 map.addStuff(new Ice(640+ newX,newY));
		 }
		 if(map.getComponents().size()>32){
			obj r = null;
			for(obj i:map.getComponents()){
				if (i.getClass() == Ice.class && i.getX()<-128){
					r = i;
				}
			}
			map.getComponents().remove(r);
	 	}
	}
}
