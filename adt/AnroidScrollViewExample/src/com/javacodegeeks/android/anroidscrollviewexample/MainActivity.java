package com.javacodegeeks.android.anroidscrollviewexample;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		
		
		//String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date(0));
		
        TextView noticeID = (TextView) findViewById(R.id.textView1);
        noticeID.setText(dateNow);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
