package Game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import acm.util.SwingTimer;

public class GUI extends GraphicsProgram{

	private static final long serialVersionUID = -3751829419358061414L;

	private Game game;
	private int[] keys = {0,0,0,0};
	
	public void run(){
        this.setSize(640, 480);
		new SwingTimer(1000/30,this).start();
	}
	
	public void init(){
		this.game = new Game();
		addKeyListeners();
		addActionListeners();
		
		Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.setMenuBar(null);
            frame.pack();
        }
        this.setBackground(Color.black);
	}
	
	public void actionPerformed(ActionEvent ae){
		removeAll();
		tick();
		printScreen();
	}

	private void tick(){
		game.tick();
	}
	
	public void keyPressed(KeyEvent event){
		char c = event.getKeyChar();
		if (c == KeyEvent.VK_1){keys[0] = 1;game.getPlayer().move(keys);}
		if (c == KeyEvent.VK_2){keys[1] = 1;game.getPlayer().move(keys);}
		if (c == KeyEvent.VK_3){keys[2] = 1;game.getPlayer().move(keys);}
		if (c == KeyEvent.VK_5){keys[3] = 1;game.getPlayer().move(keys);}
		if (c == KeyEvent.VK_SPACE){game.getPlayer().setShooting(true);
		}
		
	}
	
	public void keyReleased(KeyEvent event){
		char c = event.getKeyChar();
		if (c == KeyEvent.VK_1){;keys[0] = 0;}
		if (c == KeyEvent.VK_2){;keys[1] = 0;}
		if (c == KeyEvent.VK_3){;keys[2] = 0;}
		if (c == KeyEvent.VK_5){;keys[3] = 0;}
		game.getPlayer().setShooting(false);
		game.getPlayer().stop(keys);
	}
	
	public void printScreen(){
		GImage g = new GImage(game.getPlayer().getImg(),game.getPlayer().getX(),game.getPlayer().getY());
		add(g);
		for(obj i:game.getMap().getComponents()){
			g = new GImage(i.getImg(),i.getX(),i.getY());
			add(g);
		}
	}
}
