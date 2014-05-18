package com.btp.mnotice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListViewWithBaseAdapter extends Activity
{

	public String[] ListItems = new String[]{};

	public class noticeDB
	{
		String header;
		String noticeID;
		String subject;
		String timestamp;
		String addressee;
		String text;
		String issuingAuthority;
		String category;
		String footer;
	}




	NoticeDBList noticeListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_with_simple_adapter);


		noticeListAdapter = new NoticeDBList();

		ListView noticeList = (ListView)findViewById(R.id.listView1);
		noticeList.setAdapter(noticeListAdapter);

		noticeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				noticeDB notice = noticeListAdapter.getNotices(arg2);



				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(), SingleNotice.class);
				
				
				String toDisplay = notice.noticeID + ":" + notice.timestamp + ":" + notice.addressee + ":" + notice.subject + ":" + notice.text + ":" + notice.issuingAuthority;


				// sending data to new activity
				i.putExtra("singleNoticeDetail", toDisplay);
				startActivity(i);





				Toast.makeText(ListViewWithBaseAdapter.this, notice.noticeID,Toast.LENGTH_LONG).show();

			}
		});
	}


	public class NoticeDBList extends BaseAdapter {

		List<noticeDB> noticesList = getDataForListView();
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return noticesList.size();
		}

		@Override
		public noticeDB getItem(int arg0) {
			// TODO Auto-generated method stub
			return noticesList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			if(arg1==null)
			{
				LayoutInflater inflater = (LayoutInflater) ListViewWithBaseAdapter.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				arg1 = inflater.inflate(R.layout.listitem, arg2,false);
			}

			TextView noticeHeader = (TextView)arg1.findViewById(R.id.textView1);
			TextView noticeSubject = (TextView)arg1.findViewById(R.id.textView2);

			noticeDB notice = noticesList.get(arg0);
			

			if(notice.header.length() > 20)
				noticeHeader.setText(notice.header.substring(0, 20)+"...");
			else
				noticeHeader.setText(notice.header);
			
			
			
			if(notice.subject.length() > 30)
				noticeSubject.setText(notice.subject.substring(0, 30)+"...");
			else
				noticeSubject.setText(notice.subject);

			return arg1;
		}

		public noticeDB getNotices(int position)
		{
			return noticesList.get(position);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_with_simple_adapter, menu);
		return true;
	}

	public List<noticeDB> getDataForListView()
	{

		ListItems = MyHTTPD.finalResult.split("#");

		List<noticeDB> noticeList = new ArrayList<noticeDB>();

		for(int i=0;i<ListItems.length;i++)
		{
			String[] singleNotice = new String[]{};

			singleNotice = ListItems[i].split(":");

			noticeDB notice = new noticeDB();




			notice.header = singleNotice[0];
			notice.noticeID = singleNotice[1];
			notice.subject = singleNotice[2];
			notice.timestamp = singleNotice[3];
			notice.addressee = singleNotice[4];
			notice.text = singleNotice[5];
			notice.issuingAuthority = singleNotice[6];
			notice.category = singleNotice[7];
			notice.footer = singleNotice[8];




			noticeList.add(notice);
		}

		return noticeList;

	}
}
