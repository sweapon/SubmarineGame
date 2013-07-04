package com.application.subgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback,SensorEventListener{
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private GameThread thread;
	private Game game;
	private float firsty = 20;
	private int [] keys = {0,0,0,0};
	
	private Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.bubble);
	private Bitmap i = BitmapFactory.decodeResource(getResources(),R.drawable.ice);
	private Bitmap e = BitmapFactory.decodeResource(getResources(),R.drawable.monster);
	private Bitmap mm = BitmapFactory.decodeResource(getResources(),R.drawable.monstermissile);
	private Bitmap m = BitmapFactory.decodeResource(getResources(),R.drawable.missile);
	private Bitmap p = BitmapFactory.decodeResource(getResources(),R.drawable.submarine);
	private Bitmap bg = BitmapFactory.decodeResource(getResources(),R.drawable.background);
	
	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		game = new Game(this);
		thread = new GameThread(getHolder(),this);
		
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry){
			try{
				thread.join();
				retry = false;
			}catch (InterruptedException e){
				//prøv å slutte threaden en gang til :)
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent event){
		Log.d(TAG,"reakted to touch");
		if (event.getAction() == MotionEvent.ACTION_DOWN){
			if (event.getY() > getHeight() - 50){
				thread.setRunning(false);
				((Activity)getContext()).finish();
			}
			else if(event.getX() > getWidth() - 80){
				game.getPlayer().setShooting(true);
			}
		}
		else if (event.getAction() == MotionEvent.ACTION_UP){
			game.getPlayer().setShooting(false);
		}
		return super.onTouchEvent(event);
	}
	
	public void Update(){
		game.tick();
	}
	
	protected void onDraw(Canvas canvas){
		//printer først backgrunnen
		canvas.drawBitmap(bg,game.getMap().getBg().getX(), game.getMap().getBg().getY(),null);
		
		//skriver ut alle bildene som er i gamet på det gitte mappet
		canvas.drawBitmap(p,game.getPlayer().getX(), game.getPlayer().getY(),null);
		for(obj i:game.getMap().getComponents()){
			if(i.getClass() == Bubble.class){
				canvas.drawBitmap(this.b,i.getX(), i.getY(),null);
			}
			else if(i.getClass() == Missile.class){
				canvas.drawBitmap(this.m,i.getX(), i.getY(),null);	
			}
			else if(i.getClass() == Enemy.class){
				canvas.drawBitmap(this.e,i.getX(), i.getY(),null);	
			}
			else if(i.getClass() == EnemyMissile.class){
				canvas.drawBitmap(this.mm,i.getX(), i.getY(),null);	
			}
			else if(i.getClass() == Ice.class){
				canvas.drawBitmap(this.i,i.getX(), i.getY(),null);	
			}
//			else{
//				Log.d(TAG,"ikke dette fører til unødvedig mye lagg");
//				b = BitmapFactory.decodeResource(getResources(),i.getImg());
//				canvas.drawBitmap(b,i.getX(), i.getY(),null);
//			}
		}
		//skriver ut damage text som er i gamet på det gitte mappet
		
		for(DisplayDamage i:game.getMap().getText()){
			canvas.drawText(i.getText(), i.getX(), i.getY(), i.getPaint());
		}
		
		//skriver ut player stats ^^
		canvas.drawRect(game.getDps().getcontainer()[0], game.getDps().getcontainer()[1], game.getDps().getcontainer()[2], game.getDps().getcontainer()[3], game.getDps().getPaint());
		canvas.drawRect(game.getDps().getPlayerHealt()[0], game.getDps().getPlayerHealt()[1], game.getDps().getPlayerHealt()[2], game.getDps().getPlayerHealt()[3], game.getDps().getPaintHealt());
		canvas.drawText(game.getDps().getTextLevel(), 20, 65, game.getDps().getPaint());
		canvas.drawText(game.getDps().getLevel(), 120, 65, game.getDps().getPaint());
		canvas.drawText(game.getDps().getTextScore(), 400, 35, game.getDps().getPaint());
		canvas.drawText(game.getDps().getScore(), 480, 35, game.getDps().getPaint());
		/*
		//skriver ut playerstats
		GObject o = null;
		for(GObject i:game.getDps().getList()){
			o = i;
			add(o);
			
		}*/
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		Log.d(TAG,"skriver sjfdkskfjødaakj");
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		float [] values = event.values;
		float dy = values[1];
		if(firsty == 20){firsty = dy;}
		if (dy-0.5 > firsty){
			keys[1] = 1;
			game.getPlayer().move(keys);
		}
		else if(dy+0.5 < firsty){
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
