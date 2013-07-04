package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import Game.components.DisplayDamage;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.SwingTimer;

public class GUI extends GraphicsProgram{

	private static final long serialVersionUID = -3751829419358061414L;

	private Game game;
	private int[] keys = {0,0,0,0};
	private Font f = new Font("Impact",Font.BOLD, 32);
	private GameStates state;
	private Menue menue;
	
	public void run(){
        this.setSize(640, 480);
		new SwingTimer(1000/60,this).start();
<<<<<<< HEAD
		this.state = GameStates.menue;
=======
>>>>>>> cbb289eba5e74439f103e0b52daee7d36df53e0c
	}
	
	public void init(){
		this.game = new Game(this);
		this.menue = new Menue();
		addKeyListeners();
		addActionListeners();

		// fjerner applet menubaren
		Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.setMenuBar(null);
            frame.pack();
        }
        this.setBackground(Color.BLACK);
	}
	
	public void actionPerformed(ActionEvent ae){
		removeAll();
		tick();
		printScreen();
	}

	private void tick(){
		switch (state){
		case game:game.tick();
			break;
		case menue:
			break;
		case store:
			break;
		}
	}
	
	public void keyPressed(KeyEvent event){
		char c = event.getKeyChar();
		// for å gjøre beveglesen mest mulig smooth så må man ha kontroll på hvilke "arrow" keys som er presset ned
		// dette gjøres med en liste med ints og setter dem til 1 hvis den keyen er presset ned
		switch (state){
		case game:{
			if (c == KeyEvent.VK_1){keys[0] = 1;game.getPlayer().move(keys);}
			if (c == KeyEvent.VK_2){keys[1] = 1;game.getPlayer().move(keys);}
			if (c == KeyEvent.VK_3){keys[2] = 1;game.getPlayer().move(keys);}
			if (c == KeyEvent.VK_5){keys[3] = 1;game.getPlayer().move(keys);}
			if (c == KeyEvent.VK_SPACE){game.getPlayer().setShooting(true);};break;}
		case menue:{
			
		}
		case store:
			break;
		}
	}
	
	public void keyReleased(KeyEvent event){
		char c = event.getKeyChar();
		switch (state){
		case game:{
			if (c == KeyEvent.VK_1){;keys[0] = 0;}
			if (c == KeyEvent.VK_2){;keys[1] = 0;}
			if (c == KeyEvent.VK_3){;keys[2] = 0;}
			if (c == KeyEvent.VK_5){;keys[3] = 0;}
			if (c == KeyEvent.VK_SPACE){game.getPlayer().setShooting(false);}
			game.getPlayer().stop(keys);};break;
		case menue:
			if (c == KeyEvent.VK_5){menue.moveUp();}
			if (c == KeyEvent.VK_2){menue.moveDown();}
			if (c == KeyEvent.VK_ENTER){state = menue.getState();game.reset();}
			break;
		case store:
			break;
		}
	}
	
	public void printScreen(){
		switch (state){
		case game:
			//printer først backgrunnen
			GImage g = new GImage(game.getMap().getBg().getImg(),game.getMap().getBg().getX(),game.getMap().getBg().getY());
			add(g);
			
			//skriver ut alle bildene som er i gamet på det gitte mappet
			g = new GImage(game.getPlayer().getImg(),game.getPlayer().getX(),game.getPlayer().getY());
			add(g);
			for(obj i:game.getMap().getComponents()){
				g = new GImage(i.getImg(),i.getX(),i.getY());
				add(g);
			}
			
			//skriver ut damage text som er i gamet på det gitte mappet
			GLabel t = null;
			for(DisplayDamage i:game.getMap().getText()){
				t = i.getText();
				t.setLocation((double)i.getX(), (double)i.getY());
				t.setFont(f);
				add(t);
			}
			
			//skriver ut playerstats
			GObject o = null;
			for(GObject i:game.getDps().getList()){
				o = i;
				add(o);
			};break;
		case menue:
			//menu tegning
			g = new GImage(menue.getBg().getImg(),menue.getBg().getX(),menue.getBg().getY());
			add(g);
			for(GObject i: menue.getList()){
				o = i;
				add(o);
			}
			break;
		case store:
			break;
		}
	}
	public void setState(GameStates state){
		this.state = state;
	}
}


