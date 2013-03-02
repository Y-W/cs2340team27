package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LostItemInput extends Activity {
	/**
	 * Processes the onCreate event.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_item_input);
	}
	/**
	 * Processes the onCreateOptionsMenu event.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item_input, menu);
		return true;
	}
	/**
	 * Processes the onClick event of the confirm button.
	 * @param view
	 */
	public void confirmLostItemInfo(View view){
		//TODO - addUserInfo
		Intent intent = new Intent(this,HomePage.class);
		startActivity(intent);
	}
	/**
	 * Processes the onClick event of the cancel button.
	 * @param view
	 */
	public void cancelLostItemInfo(View view){
		Intent intent = new Intent(this,HomePage.class);
		startActivity(intent);
	}
}
