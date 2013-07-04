package com.application.subgame;

import java.io.IOException;

import com.application.subgame.components.Pause;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback,SensorEventListener{
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private GameThread thread;
	private Game game;
	private float firsty = 20;
	private int [] keys = {0,0,1,0};
	private Display display;
	private Rect rect;
	private GameStates state;
	private Menue menue;
	private Pause pause;
	private Start s;
	private Store store;
	private GameEnd gameover;
	private MainActivity ma;
	
	private Bitmap pa = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
	
	public MainGamePanel(Context context,Display display,MainActivity ma) {
		super(context);
		getHolder().addCallback(this);
		this.display = display;
		setFocusable(true);
		this.rect = new Rect();
		display.getRectSize(this.rect);
		this.ma = ma;
		
		// start setup for the game
		this.game = new Game(this,this.rect);
		this.state = GameStates.menue;
		//makes the loop for the game
		// set screen size and resize the background so it fits the device no matter the resolution ^^
		this.menue = new Menue(game);
		this.pause = new Pause(rect.width()/2-pa.getWidth()/2,rect.height()/2-pa.getHeight()/2,BitmapFactory.decodeResource(getResources(), R.drawable.pause));
		this.store = new Store(game.getPlayer(),game);
		s = new Start(this,rect);
		this.gameover = new GameEnd(rect,getResources(),game.getPlayer());
		//ma.Save(game.getPlayer().getSave());
		try {
			game.getPlayer().load(ma.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MainGamePanel(Context context){
		super(context);
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		this.thread = new GameThread(getHolder(),this);
		this.firsty = 20;
		this.thread.setRunning(true);
		this.thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		Log.d(TAG,"joiner threaden");
		while (retry){
			try{
				thread.join();
				retry = false;
			}catch (InterruptedException e){
			}
		}
	}
	
	//touchfunksjoene til spillet
	public boolean onTouchEvent(MotionEvent event){
		switch(state){
		case game:
			if (event.getAction() == MotionEvent.ACTION_UP){
				game.getPlayer().setShooting(false);
			}
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				if(event.getX() > getWidth() - 100){
					game.getPlayer().setShooting(true);
					return true;
				}
				if(event.getX() < 100){
					game.getPlayer().setAttack(true);
					return true;
				}
			}break;
		case menue:
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				if(event.getY()>rect.height()*1/6 && event.getY()<rect.height()*2/6){
					menue.setPeker(0);
					menue.setIsTouched(true);
				}
				else if (event.getY()>rect.height()*2/6 && event.getY()<rect.height()*3/6){
					menue.setPeker(1);
					menue.setIsTouched(true);
				}else menue.setIsTouched(false);
				menue.repaint();
				return true;
			}
			else if(event.getAction() == MotionEvent.ACTION_MOVE){
				if(event.getY()<rect.height()*2/6){
					menue.setPeker(0);
				}
				else if (event.getY()>rect.height()*2/6){
					menue.setPeker(1);
				}
				menue.repaint();
				return true;
			}
			else if(event.getAction() == MotionEvent.ACTION_UP){
				if (menue.getIsTouched()){
					if(event.getY()>rect.height()*1/6 && event.getY()<rect.height()*2/6){
						this.game.reset();
						setState(GameStates.start);
						game.setGenerate(false);
					}
					else if (event.getY()>rect.height()*2/6 && event.getY()<rect.height()*3/6){
						thread.setRunning(false);
						((Activity)getContext()).finish();
					}
				}
				menue.setIsTouched(false);
				menue.repaint();
				return true;
			}
			break;
		case store:
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				game.setGenerate(false);
				game.getPlayer().setShooting(false);
				game.getPlayer().setDy(0);
				this.setState(GameStates.start);
			}
			break;
		case pause: 
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				this.setState(GameStates.game);
			}
			break;
		case gameover:
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				this.setState(GameStates.menue);
			}
		}
		return super.onTouchEvent(event);
	}
	
	public void Update(){
		switch (state){
		case game:this.game.tick();break;
		case menue:break;
		case pause:pause.tick();break;
		case start:this.game.tick(); this.s.update();break;
		case gameover: this.gameover.tick();
		}
	}
	
	public void draw(Canvas canvas){
		switch(state){
		case game:
			this.game.draw(canvas);
			this.game.getDps().draw(canvas);
			break;
		case menue:
			this.menue.draw(canvas);
			break;
		case store:
			this.store.draw(canvas);
			;break;
		case pause:
			this.game.draw(canvas);
			this.game.getDps().draw(canvas);
			this.pause.draw(canvas);
			break;
		case start:
			game.draw(canvas);
			s.draw(canvas);
			break;
		case gameover:
			this.gameover.draw(canvas);
			break;
		}
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		if (state == GameStates.game){
			float [] values = adjustAccelOrientation(display.getRotation(), event.values);
			float dx = values[1];
			dx = Math.abs(dx);
			if(firsty == 20){firsty = dx;}
			if (dx-0.5 > firsty){
				keys[1] = 1;
				game.getPlayer().move(keys);
			}
			else if(dx+0.5 < firsty){
				keys[3] = 1;
				game.getPlayer().move(keys);
			}else{
				keys[1] = 0;
				keys[3] = 0;
				keys[0] = 1;
				game.getPlayer().stop(keys);
			}
		}
	}
	
	public void setState(GameStates state){
		if (state == GameStates.game){
			try {
				this.game.getPlayer().load(ma.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.firsty = 20;
			game.setGenerate(true);
			this.game.getPlayer().setShooting(false);
			thread.setRunning(true);
		}
		else if(state == GameStates.store){
			store.update();
		}
		else if (state == GameStates.menue){
			ma.Save(game.getPlayer().getSave());
		}
		else if(state == GameStates.gameover){
			this.gameover.setExpnow(game.getPlayer().getExperience(), game.getPlayer().getLevel());
		}
		this.state = state;
	}
	
	public GameStates getState(){
		return this.state;
	}
	
	private float[] adjustAccelOrientation(float displayRotation, float[] eventValues) 
	{ 
	    float[] adjustedValues = new float[3];

	    final float axisSwap[][] = {
	    {  1,  -1,  0,  1  },     // ROTATION_0 
	    {-1,  -1,  1,  0  },     // ROTATION_90 
	    {-1,    1,  0,  1  },     // ROTATION_180 
	    {  1,    1,  1,  0  }  }; // ROTATION_270 

	    final float[] as = axisSwap[(int) displayRotation]; 
	    adjustedValues[0]  =  (float)as[0] * eventValues[ (int) as[2] ]; 
	    adjustedValues[1]  =  (float)as[1] * eventValues[ (int) as[3] ]; 
	    adjustedValues[2]  =  eventValues[2];

	    return adjustedValues;
	}

	public Game getGame() {
		return this.game;
	}
}
