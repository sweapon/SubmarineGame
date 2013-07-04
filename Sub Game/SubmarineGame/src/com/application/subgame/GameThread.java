package com.application.subgame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
	
	private static final String TAG = GameThread.class.getSimpleName();
	
	private boolean running;
	private SurfaceHolder surfaceholder;
	private MainGamePanel maingamepanel;
	
	private final static int 	MAX_FPS = 30;
	private final static int	MAX_FRAME_SKIPS = 30;
	private final static int	FRAME_PERIOD = 1000 / MAX_FPS;
	
	public GameThread(SurfaceHolder surface, MainGamePanel gamepanel){
		super();
		this.surfaceholder = surface;
		this.maingamepanel = gamepanel;
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	
	public void run() {
		Canvas canvas;
		Log.d(TAG, "Starting game loop");

		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;

		while (running) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {
				canvas = this.surfaceholder.lockCanvas();
				synchronized (surfaceholder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;	// resetting the frames skipped
					// update game state
					this.maingamepanel.Update();
					// render state to the screen
					// draws the canvas on the panel
					this.maingamepanel.draw(canvas);
					// calculate how long did the cycle take
					timeDiff = System.currentTimeMillis() - beginTime;
					// calculate sleep time
					sleepTime = (int)(FRAME_PERIOD - timeDiff);

					if (sleepTime > 0) {
						// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period
							// very useful for battery saving
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {}
					}

					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						// we need to catch up
						// update without rendering
						this.maingamepanel.Update();
						// add frame period to check if in next frame
						sleepTime += FRAME_PERIOD;
						framesSkipped++;
					}
				}
			} finally {
				// in case of an exception the surface is not left in
				// an inconsistent state
				if (canvas != null) {
					surfaceholder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

}
