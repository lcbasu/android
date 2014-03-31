package com.btp.mnotice;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultListView extends ListActivity
{
	public String[] ListItems = new String[]{};
	static String finalResult = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		try
		{
			ListItems = finalResult.split("#");
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, ListItems);
			setListAdapter(adapter);
		}
		catch(Exception e)
		{
			
		}
	}

}
