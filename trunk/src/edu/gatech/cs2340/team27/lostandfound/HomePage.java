package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}
	
	public void loserAttempt(View view){
		Intent intent = new Intent(this,LostItemInput.class);
		startActivity(intent);
	}
	
	public void founderAttempt(View view){
		Intent intent = new Intent(this,.class);
		startActivity(intent);
	}
	
	public void lostinfoAttempt(View view){
		Intent intent = new Intent(this,.class);
		startActivity(intent);
	}
	
	public void foundinfoAttempt(View view){
		Intent intent = new Intent(this,.class);
		startActivity(intent);
	}
	
	/**
	 * log out
	 * directs to Login page
	 * @param view
	 */
	public void logoutAttempt(View view){
		Intent intent = new Intent(this,Login.class);
		startActivity(intent);
	}

}
