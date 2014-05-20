package com.basu.android.tnb;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		// get the references
		Button checkCommand = (Button) findViewById(R.id.bResults);
		final ToggleButton passToggle = (ToggleButton) findViewById(R.id.tbPassword);
		final EditText input = (EditText) findViewById(R.id.etCommands);
		final TextView display = (TextView) findViewById(R.id.tvResults);

		// set up the on click listener on toggle button
		passToggle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (passToggle.isChecked()) {
					input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				} else {
					input.setInputType(InputType.TYPE_CLASS_TEXT);
				}

			}
		});
		
		checkCommand.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String check = input.getText().toString();
				check = check.toLowerCase();
				if (check.contentEquals("left")) {
					display.setGravity(Gravity.LEFT);
				} else if (check.contentEquals("center")) {
					display.setGravity(Gravity.CENTER);
				} else if (check.contentEquals("right")) {
					display.setGravity(Gravity.RIGHT);
				} else if (check.contentEquals("blue")) {
					display.setTextColor(Color.BLUE);
				} else {
					display.setText("invalid");
					display.setGravity(Gravity.CENTER);
				}
			}
		});
	}
}