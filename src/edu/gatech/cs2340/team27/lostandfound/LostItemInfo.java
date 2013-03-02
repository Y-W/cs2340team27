package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LostItemInfo extends Activity {

	@Override
	/**
	 * build in method
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_item_info);
	}

	@Override
	/**
	 * build in method
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item_info, menu);
		return true;
	}
	/**
	 * go back to LostItem Page
	 * @param view
	 */
	public void LostItemAttempt(View view){
		Intent intent = new Intent(this,LostItem.class);
		startActivity(intent);
	}

}
