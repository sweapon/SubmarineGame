package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
	
	public void run(){
		// bare en liten ting som gjør at programmet ikke fryser når den leter etter den riktige fonten
		GLabel l = new GLabel(" ");
		l.setFont(f);
		
        this.setSize(640, 480);
		new SwingTimer(1000/60,this).start();
	}
	
	public void init(){
		this.game = new Game();
		addKeyListeners();
		addActionListeners();
		
		
		// fjerner applet menubaren
		Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.setMenuBar(null);
            frame.pack();
        }
        this.setBackground(Color.blue);
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
		// for å gjøre beveglesen mest mulig smooth så må man ha kontroll på hvilke "arrow" keys som er presset ned
		// dette gjøres med en liste med ints og setter dem til 1 hvis den keyen er presset ned
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
		if (c == KeyEvent.VK_SPACE){game.getPlayer().setShooting(false);}
		game.getPlayer().stop(keys);
	}
	
	public void printScreen(){
		
		//skriver ut alle bildene som er i gamet på det gitte mappet
		GImage g = new GImage(game.getPlayer().getImg(),game.getPlayer().getX(),game.getPlayer().getY());
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
		}
	}
}
