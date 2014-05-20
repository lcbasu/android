package com.basu.android.tnb;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
	
	String classes[] = { "StartingPoint", "TextPlay", "example2", "example3", "example4", "example5" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// setup list adapter
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		// get the clicked class
		String clickedClass = classes[position];
		
		try {
			// setup class variable
			Class menuClass = Class.forName("com.basu.android.tnb." + clickedClass);
			//start new intent
			Intent newIntent = new Intent(Menu.this, menuClass);
			startActivity(newIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
