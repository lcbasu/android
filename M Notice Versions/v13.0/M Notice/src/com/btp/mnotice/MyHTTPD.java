package com.btp.mnotice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.btp.mnotice.MyHTTPD.FileExtensionFilter;
import com.btp.mnotice.NanoHTTPD.Response.Status;

import android.R.array;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.format.Formatter;
import android.widget.Toast;

public class MyHTTPD extends NanoHTTPD {
	InputStream data;
	int counter ;
	Activity c;
	WifiManager wifi;
	String word = "";
	String my_ip="";
	String clientip="";
	String query="";
	public String my_path="";
	public static Charset charset = Charset.forName("UTF-8");
	public static CharsetEncoder encoder = charset.newEncoder();
	public static int count = 0 ; 
	public static String client_list="";
	public static String filename="default";
	public static String[] max_cpu,min_cpu,ram;
	public static ArrayList<HashMap<String, String>> song_list = new ArrayList<HashMap<String, String>>();
	//public static int countcpu = 0 ; 
	//int count = 0;
	
	static String finalResult = "";
	
	/**
	 * Constructs an HTTP server on given port.
	 */
	public MyHTTPD(Activity a, String ipaddr, String path) throws IOException {
		super(ipaddr, 8765);
		c = a;
		my_ip = ipaddr;
		my_path = path;
		// httpStuff=h;
	}

	@Override
	public Response serve(String uri, Method method,
			Map<String, String> header, final Map<String, String> parms,
			Map<String, String> files) {

		//final String ipaddr1 = parms.get("ipaddr");
		//String returned = null;
		//final String extension;
		//final String url = uri;
		//String msg2 = files.toString();
		String msg1 = " haha";

		try {
			if(parms.get("getinfo") != null){
				
				counter=0;
			clientip=parms.get("clientip");
		 query=parms.get("query");
			c.runOnUiThread(new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					try {
						Toast.makeText(c,"received notice query for "+query, Toast.LENGTH_LONG).show();
						
			            new sendinfotoclient(clientip,query).execute();
						}
					catch(Exception e){
						
					}
		 
				}
		
			
	});}
if(parms.get("query_return") != null){
				
		//counter=0;
		final String result=parms.get("result");
		final String resultListView=parms.get("resultListView");
		 
			c.runOnUiThread(new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					try {
						finalResult = "";
						finalResult = finalResult.concat(resultListView);
						if(finalResult.length() == 0)
							Toast.makeText(c,"Empty Search Result", Toast.LENGTH_LONG).show();
						MainActivity.text.setText(result);
						SearchActivity.text.setText(result);
			//new sendinfotoclient(clientip,query).execute();
			}
					catch(Exception e){
						
					}
		 
				}
		
			
	});}
			
			
			
		
		} catch (Exception e) {
			msg1 = "<p>" + e.getMessage().toString() + "</p>";
		}

		
		
		return null;
		}

	
	
		
		
		


public class sendinfotoclient extends AsyncTask<String, String, String> {
	String clientip="";
	String query="";
	public sendinfotoclient(String clientip11,String query11){
		clientip=clientip11;
		query=query11;
	}
	
	
	
	protected void onPreExecute() {
		Toast.makeText(
				c,
				"Sending.."
						, Toast.LENGTH_LONG).show();
	}
	
	
	
	protected void onPostExecute(String result) {
		
     }
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String info="";
		String infoListView="";
		
		try{
			
			File set1 = new File(Environment.getExternalStorageDirectory().getPath()
					+ "/inputfile.txt");		
				if(set1.canRead()){
				BufferedReader buffer1 = new BufferedReader(new FileReader(set1));
				String l = "";
				int count = 1;
				while ((l = buffer1.readLine()) != null) {
					String[] split=l.split(":");
					if(split[0].contentEquals(query)){
						info = info + query +  " : "+ split[1]+"\n";
						infoListView = infoListView + query  + " : "+ split[1] +":" + split[2] +"#";
					}
				}
		
				buffer1.close();
				}
				
		}
		catch(Exception e){
			
		}
		try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://"+clientip+":8765");
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("query_return", "1"));
				nameValuePairs.add(new BasicNameValuePair("result",info));
				nameValuePairs.add(new BasicNameValuePair("resultListView",infoListView));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				httpclient.execute(httppost);
				
				}
		        
	
		catch(Exception e){
			
		}
				
				
		
		return clientip;
	}
	
}


/**
 * Class to filter files which are having .mp3 extension
 * */
class FileExtensionFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return (name.endsWith(".mp3") || name.endsWith(".MP3"));
	}
}
}