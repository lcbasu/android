package com.btp.mnotice;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity
{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.splash);
		
		ourSong = MediaPlayer.create(Splash.this, R.raw.pinjra);
		ourSong.start();
		
		Thread timer = new Thread(){
			public void run()
			{
				try
				{
					sleep(1*1000);	//sleep for 5 seconds
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					Intent openStartingPoint = new Intent("com.btp.mnotice.START");
					startActivity(openStartingPoint);
				}
			}
		};//thread ends here
		
		timer.start();	//start the thread
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}
	
}