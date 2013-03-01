package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LostItemInput extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_item_input);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item_input, menu);
		return true;
	}
	/**
	 * Calls when click confirm to submit the lost item info
	 * @param view
	 */
	public void confirmLostItemInfo(View view){
		//TODO - addUserInfo
		Intent intent = new Intent(this,HomePage.class);
		startActivity(intent);
	}
	
	public void cancelLostItemInfo(View view){
		Intent intent = new Intent(this,HomePage.class);
		startActivity(intent);
	}
}
