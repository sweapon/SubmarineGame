package Game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;
import acm.util.SwingTimer;

public class GUI extends GraphicsProgram{
	
	private Game game;
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
		if (c == KeyEvent.VK_1 || c == KeyEvent.VK_3 || c == KeyEvent.VK_5 || c == KeyEvent.VK_2){
			game.getPlayer().move(c);
		}
	}
	
	
	public void printScreen(){
		
	}
}
