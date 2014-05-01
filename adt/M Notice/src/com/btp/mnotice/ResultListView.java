package com.btp.mnotice;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ResultListView extends ListActivity
{
	public String[] ListItems = new String[]{};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		try
		{
			ListItems = MyHTTPD.finalResult.split("#");

			// Binding resources Array to ListAdapter
			this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, ListItems));

			ListView lv = getListView();

			// listening to single list item on click
			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
				{
					// selected item 
					String singleNotice = ((TextView) view).getText().toString();

					// Launching new Activity on selecting single List Item
					Intent i = new Intent(getApplicationContext(), SingleNotice.class);

					//extracting the details of the notice
					String[] split=singleNotice.split(":");
					String singleNoticeDetail = split[2];

					// sending data to new activity
					i.putExtra("singleNoticeDetail", singleNoticeDetail);
					startActivity(i);
				}
			});
		}
		catch(Exception e)
		{

		}

	}

}
