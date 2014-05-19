package com.basu.android.tnb;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	
	MediaPlayer splashSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		// set up the sound for the splash screen
		splashSong = MediaPlayer.create(Splash.this, R.raw.splash);
		splashSong.start();

		// set up thread and the timer
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);	// sleep for 2 seconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openMenu = new Intent("com.basu.android.tnb.MENU");
					startActivity(openMenu);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		splashSong.release();	// releases the resources associated with Media Player
		finish();	// kills the splash activity
	}
}
