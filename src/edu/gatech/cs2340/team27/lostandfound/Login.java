package edu.gatech.cs2340.team27.lostandfound;

import edu.gatech.cs2340.team27.lostandfound.R;

import edu.gatech.cs2340.team27.lostandfound.model.Communication.LoginStatus;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

	public final static String STATUS_MESSAGE = "LOGIN_STATUS_MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void loginAttempt(View view) {
		Intent intent = new Intent(this, LoginStatus.class);
		EditText username = (EditText) findViewById(R.id.editText1);
		EditText password = (EditText) findViewById(R.id.editText2);
		String userText = username.getText().toString();
		String psText = password.getText().toString();
		LoginStatus status = edu.gatech.cs2340.team27.lostandfound.model.Communication.loginAttempt(userText, psText);
		String message;
		if(true) {
			message = "Login Successful.";
		}
		else if(status == LoginStatus.FAILURE) {
			message = "Login Failed.";
		}
		else {
			message = "Account Locked'";
		}
		intent.putExtra(STATUS_MESSAGE, message);
		startActivity(intent);
	}
	
	/*public void registerAttempt() {
		Intent intent = new Intent(this, Register.class);
	    startActivity(intent);
	}*/

}
