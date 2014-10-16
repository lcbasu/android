package com.basu.assettest;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AssetManager manager = getAssets();
		
		try {
			InputStream open = manager.open("Collages.jpg");
			Bitmap bitmap = BitmapFactory.decodeStream(open);
			
			ImageView imgView = (ImageView)findViewById(R.id.imageView1);
			imgView.setImageBitmap(bitmap);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
