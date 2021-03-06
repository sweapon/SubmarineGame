package com.application.subgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private SensorManager senManager;
	private MainGamePanel mainGame;
	private Display display;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        
        this.senManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.display = getWindowManager().getDefaultDisplay();
        this.mainGame = new MainGamePanel(this,display,this); 
        this.mainGame.setKeepScreenOn(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(mainGame);
    }

    public String load() throws IOException{
    	
		BufferedReader reader = new BufferedReader(new FileReader(new File(getFilesDir().getCanonicalPath() +"/saveGame")));
		
		String readline = reader.readLine();
		String load = "";
		while((readline) != null){
			load = load + readline;
			readline = reader.readLine();
			//Log.d(TAG,readline);
		}
		//Log.d(TAG,load);
		return load;
       }
    
    public void Save(String save){
    	Log.d(TAG,"n� driver den � lagrer gamet...");
    	try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(getFilesDir().getCanonicalPath() +"/saveGame")));
			writer.write(save);
			writer.close();
		}catch(Exception e){};
    }
    
    
    public void onDestroy(){
    	Log.d(TAG,"destroying...");
    	Save(mainGame.getGame().getPlayer().getSave());
    	this.senManager.unregisterListener(mainGame);
    	super.onDestroy();
    }
    
    public void onStop(){
    	this.senManager.unregisterListener(mainGame);
    	Log.d(TAG,"Stopping...");
    	super.onStop();
    }
    
    public void onPause(){
    	this.senManager.unregisterListener(mainGame);
    	this.mainGame.surfaceDestroyed(mainGame.getHolder());
    	Log.d(TAG,"pauseing...");
    	if (this.mainGame.getState() == GameStates.game){
    		this.mainGame.setState(GameStates.pause);
    	}
    	super.onPause();
    	
    }
    
    public void onResume(){
    	this.senManager.registerListener(mainGame, senManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
    	Log.d(TAG,"resuming,...");
    	super.onResume();
    }
    
    public void onStart(){
    	this.senManager.registerListener(mainGame, senManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
    	super.onStart();
    }
}
