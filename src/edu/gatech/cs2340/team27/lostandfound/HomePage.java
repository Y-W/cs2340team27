package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
	/**
	 * Homepage
	 * five buttons
	 * each button directs to a new page
	 * @author all
	 *
	 */
public class HomePage extends Activity {

	@Override
	/**
	 * build in method
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
	}

	@Override
	/**
	 * build in method
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}
	/**
	 * loserAttempt
	 * directs to lostItemInput page
	 * @param view default
	 */
	public void loserAttempt(View view){
		Intent intent = new Intent(this,LostItemInput.class);
		startActivity(intent);
	}
	/**
	 * founderAttempt
	 * directs to FoundItemInput page
	 * @param view default
	 */
	public void founderAttempt(View view){
		Intent intent = new Intent(this,FoundItemInput.class);
		startActivity(intent);
	}
	/**
	 * lostinfoAttempt
	 * directs to lostItem page
	 * @param view default
	 */
	public void lostinfoAttempt(View view){
		Intent intent = new Intent(this,LostItem.class);
		startActivity(intent);
	}
	/**
	 * foundinfoAttempt
	 * directs to FoundItem page
	 * @param view default
	 */
	public void foundinfoAttempt(View view){
		Intent intent = new Intent(this,FoundItemInfo.class);
		startActivity(intent);
	}
	
	/**
	 * log out
	 * directs to Login page
	 * @param view default
	 */
	public void logoutAttempt(View view){
		Intent intent = new Intent(this,Login.class);
		startActivity(intent);
	}

}
