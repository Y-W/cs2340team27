package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginAdmin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_admin);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_admin, menu);
		return true;
	}
	/**
	 * redirects userlist page
	 * @param view
	 */
	public void userListAttempt(View view) {
		Intent intent = new Intent(this, UserList.class);
	    startActivity(intent);
	}
	/**
	 * redirects createAdmin page
	 * @param view
	 */
	public void createAdminAttempt(View view) {
		Intent intent = new Intent(this, CreateAdmin.class);
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
