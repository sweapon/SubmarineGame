package com.application.subgame;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private SensorManager senManager;
	private MainGamePanel mainGame;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.senManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.mainGame = new MainGamePanel(this); 
        this.mainGame.setKeepScreenOn(true);
        //this.mainGame.set
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(mainGame);
        Log.d(TAG,"view added");
        this.senManager.registerListener(mainGame, senManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),senManager.SENSOR_DELAY_GAME);
    }

    
    public void onDestroy(){
    	Log.d(TAG,"destroying...");
    	this.senManager.unregisterListener(mainGame);
    	super.onDestroy();
    }
    
    public void onStop(){
    	this.senManager.unregisterListener(mainGame);
    	Log.d(TAG,"Stopping...");
    	super.onStop();
    }

    
}
