package com.btp.mnotice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	WifiManager wifi;
	String myip="";
	static TextView text,searchText ;
	String ssidt = "DDT", passt = "hotspotddt";
	String ssid, pass;
	ScanResult bestSignal = null;
	boolean boolifclient;
	String refreshResult = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		boolifclient = getIntent().getBooleanExtra("isclient", false);
		if(boolifclient)
			setContentView(R.layout.activity_main);
		else
			setContentView(R.layout.activity_main2);	

		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		final String serverip="192.168.43.1";
		WifiConfiguration configure = null;

		// List available networks

		if(boolifclient)
		{
			try
			{
				Method[] wmMethods = wifi.getClass().getDeclaredMethods();

				for (Method method : wmMethods)
				{
					if (method.getName().equals("setWifiApEnabled"))
					{
						try
						{
							method.invoke(wifi, null, false);
						}
						catch (IllegalArgumentException e)
						{
							e.printStackTrace();
						}
						catch (IllegalAccessException e)
						{
							e.printStackTrace();
						}
						catch (InvocationTargetException e)
						{
							e.printStackTrace();
						}
					}
				}

				wifi.setWifiEnabled(true);
				int loopMax = 10;

				while (loopMax > 0 && wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLED)
				{
					try
					{
						Thread.sleep(500);
						loopMax--;
					}
					catch (Exception e)
					{

					}
				}
				
				wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				wifi.getConnectionInfo();

				List<ScanResult> configs = wifi.getScanResults();

				for (ScanResult config : configs)
				{
					Toast.makeText(this,"hotspot SSID  :" +config.SSID.toString(),Toast.LENGTH_LONG).show();
					if (config.SSID.toString().contentEquals(ssidt) )
					{
						bestSignal = config;
					}
				}
				
				//Toast.makeText(this,"bestSignal SSID  :" +bestSignal.SSID.toString(),Toast.LENGTH_LONG).show();
				if(bestSignal.SSID.toString().contentEquals(ssidt))
				{
					Toast.makeText(this,"bestSignal SSID  :" +bestSignal.SSID.toString(),Toast.LENGTH_LONG).show();
					WifiConfiguration wc = new WifiConfiguration();
					wc.SSID = "\"" + ssidt + "\"";
					wc.preSharedKey = "\"" + passt + "\"";
					wc.status = WifiConfiguration.Status.ENABLED;
					wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
					wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
					wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
					wc.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
					wc.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
					wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
					// connect to and enable the connection
					int netId = wifi.addNetwork(wc);
					List<WifiConfiguration> list = wifi.getConfiguredNetworks();
					for( WifiConfiguration i : list )
					{
						if(i.SSID != null && i.SSID.equals("\"" + ssidt + "\""))
						{
							wifi.disconnect();
							wifi.enableNetwork(netId, true);
							wifi.reconnect();               
							break;
						}           
					}
				}

			}
			catch(Exception e)
			{

			}

			text = (TextView) findViewById(R.id.textView1);
			text.setMovementMethod(new ScrollingMovementMethod());
			
			Button placementQuery=(Button)findViewById(R.id.placementQuery);		
			placementQuery.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					refreshResult = "";
					String s="Placement";
					refreshResult = s;
					new inforequest(s,serverip).execute();
				}
			});

			Button doswQuery=(Button)findViewById(R.id.doswQuery);		
			doswQuery.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					refreshResult = "";
					String s="DOSW";
					refreshResult = s;
					new inforequest(s,serverip).execute();
				}
			});

			Button acadQuery=(Button)findViewById(R.id.acadQuery);		
			acadQuery.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					refreshResult = "";
					String s="Academics";
					refreshResult = s;
					new inforequest(s,serverip).execute();
				}
			});		

			Button recomendationQuery=(Button)findViewById(R.id.recomendationQuery);		
			recomendationQuery.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					refreshResult = "";
					String s="Recommendation";
					refreshResult = s;
					new inforequest(s,serverip).execute();
				}
			});

			Button miscQuery=(Button)findViewById(R.id.miscQuery);		
			miscQuery.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					refreshResult = "";
					String s="Misc";
					refreshResult = s;
					new inforequest(s,serverip).execute();
				}
			});		

			Button listViewButton=(Button)findViewById(R.id.listViewButton);
			listViewButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) 
				{
					Intent intent;
					intent = new Intent("com.btp.mnotice.LISTVIEWWITHBASEADAPTER");

					//intent.putExtra("isclient", isclient);
					startActivity(intent);
				}
			});

			Button searchButton=(Button)findViewById(R.id.searchButton);		
			final EditText Search = (EditText) findViewById(R.id.searchBox);

			searchButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					refreshResult = "";
					String s=Search.getText().toString();
					refreshResult = s;
					new inforequest(s,serverip).execute();
				}
			});		
		}
		else
		{
			try 
			{
				wifi.setWifiEnabled(false);
				configure = null;
				configure = new WifiConfiguration();
				configure.SSID = ssidt;
				configure.preSharedKey = passt;
				configure.status = WifiConfiguration.Status.ENABLED;
				configure.allowedGroupCiphers
				.set(WifiConfiguration.GroupCipher.TKIP);
				configure.allowedGroupCiphers
				.set(WifiConfiguration.GroupCipher.CCMP);
				configure.allowedKeyManagement
				.set(WifiConfiguration.KeyMgmt.WPA_PSK);
				configure.allowedPairwiseCiphers
				.set(WifiConfiguration.PairwiseCipher.TKIP);
				configure.allowedPairwiseCiphers
				.set(WifiConfiguration.PairwiseCipher.CCMP);
				configure.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
				// USE REFLECTION TO GET METHOD "SetWifiAPEnabled"
				Method method = wifi.getClass().getMethod("setWifiApEnabled",
						WifiConfiguration.class, boolean.class);
				method.invoke(wifi, configure, true);
			}
			catch (NoSuchMethodException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IllegalArgumentException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InvocationTargetException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		try
		{
			(new MyHTTPD(this, Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress()), "")).start();
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public class inforequest extends AsyncTask<String, String, String>
	{
		String search="";
		String server="";

		public inforequest(String search1,String serverip)
		{
			search=search1;
			server=serverip;
		}
		
		protected void onPreExecute()
		{
			
		}

		protected void onPostExecute(String result)
		{

		}
		
		@Override
		protected String doInBackground(String... arg0) 
		{
			try
			{
				myip = Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://"+server+":8765");
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("getinfo", "1"));
				nameValuePairs.add(new BasicNameValuePair("query",search));
				nameValuePairs.add(new BasicNameValuePair("clientip",myip));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				httpclient.execute(httppost);
			}
			catch(Exception e)
			{

			}
			return null;
		}
	}
}