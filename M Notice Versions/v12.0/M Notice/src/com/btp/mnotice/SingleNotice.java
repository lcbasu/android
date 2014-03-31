package com.btp.mnotice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleNotice extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_notice_view);
        
        TextView txtProduct = (TextView) findViewById(R.id.singleNotice);
         
        Intent i = getIntent();
        
        // getting attached intent data
        String product = i.getStringExtra("singleNoticeDetail");
        
        // displaying selected product name
        txtProduct.setText(product);
	}

}
