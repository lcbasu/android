package com.btp.mnotice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SingleNotice extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_notice_view);
        
        TextView noticeDisplay = (TextView) findViewById(R.id.singleNotice);
         
        Intent i = getIntent();
        
        // getting attached intent data
        String notice = i.getStringExtra("singleNoticeDetail");
        
        // displaying selected product name
        
        int t = 10;
        while(t > 0)
        {
        	notice = notice.concat(" \n " + notice + " \n ");
        	t--;
        }
        noticeDisplay.setText(notice);
        noticeDisplay.setMovementMethod(new ScrollingMovementMethod());
	}

}
