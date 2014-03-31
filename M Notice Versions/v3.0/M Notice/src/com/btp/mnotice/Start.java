package com.btp.mnotice;

import java.lang.reflect.Method;

import com.btp.mnotice.R;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Start extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	RadioGroup select;
	Button go;
	EditText ssid, pass;
	Boolean isclient = false;
	int numb;
	String ssidet = null, passet = null;
	WifiManager wifi;
	WifiConfiguration configure = null;
	private static final String TAG = "WiFiDemo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		initialize();

		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		try {

			Method method = wifi.getClass().getMethod("setWifiApEnabled",
					WifiConfiguration.class, boolean.class);
			method.invoke(wifi, configure, false);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		try {
			wifi.setWifiEnabled(true);
			int loopMax = 10;
			while (loopMax > 0
					&& wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLED) {
				Log.d(TAG, "disable wifi: waiting, pass: " + (10 - loopMax));
				try {
					Thread.sleep(500);
					loopMax--;
				} catch (Exception e) {

				}
			}
			Log.d(TAG, "enable wifi: done, pass: " + (10 - loopMax));
		}

		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}


	public void initialize() {
		select = (RadioGroup) findViewById(R.id.rgselections);
		go = (Button) findViewById(R.id.bgo);
		go.setOnClickListener(this);
		select.setOnCheckedChangeListener(this);
		isclient = false;
	}

	public void initializepri() {
		select = (RadioGroup) findViewById(R.id.rgselections);
		go = (Button) findViewById(R.id.bgo);
		go.setOnClickListener(this);
		select.setOnCheckedChangeListener(this);
		isclient = true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent("com.btp.mnotice.MAINACTIVITY");
		intent.putExtra("isclient", isclient);
		startActivity(intent);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.server:
			initialize();
			break;
		case R.id.client:
			initializepri();
			break;
		}

	}


}
