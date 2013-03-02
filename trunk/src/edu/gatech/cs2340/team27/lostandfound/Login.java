package edu.gatech.cs2340.team27.lostandfound;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;
import edu.gatech.cs2340.team27.lostandfound.model.Communication.LogStatus;

public class Login extends Activity {

	public final static String STATUS_MESSAGE = "edu.gatech.cs2340.team27.lostandfound.MESSAGE";
	
	@Override
	/**
	 * build in method
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Communication.setAppContext(this.getApplicationContext());
	}

	@Override
	/**
	 * build in method
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	/**
	 * get username and password, and see if the password is correct
	 * if it is correct, redirects to home page
	 * if the password is incorrect, pop up wrong message
	 * if the password is incorrect for three times in a row, lock the account
	 * @param view
	 */
	public void loginAttempt(View view) {
		Intent intent = new Intent(this, HomePage.class);
		EditText username = (EditText) findViewById(R.id.editText1);
		EditText password = (EditText) findViewById(R.id.editText2);
		String userText = username.getText().toString();
		String psText = password.getText().toString();
		LogStatus status = edu.gatech.cs2340.team27.lostandfound.model.Communication.getInstance().loginAttempt(userText, psText);
		if(status == LogStatus.SUCCESS) {
			startActivity(intent);
		}
		
		else if (status==LogStatus.FAILURE) {
			new AlertDialog.Builder(this)
		    .setTitle("Error")
		    .setMessage("Wrong Password.")
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            passwordclear();
		        }
		     })
		     .show();
			return;
		}
		else{
			new AlertDialog.Builder(this)
		    .setTitle("Error")
		    .setMessage("Account Locked!")
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            usernameclear();
		            passwordclear();
		        }
		     })
		     .show();
			return;
		}
		
	}
	/**
	 * clear usename
	 */
	public void usernameclear(){
		EditText username = (EditText) findViewById(R.id.editText1);
		username.setText("");
	}
	/**
	 * clear password
	 */
	public void passwordclear(){
		EditText password = (EditText) findViewById(R.id.editText2);
		password.setText("");
	}
	
	/**
	 * redirects to register page
	 * @param view
	 */
	public void registerAttempt(View view) {
		Intent intent = new Intent(this, Register.class);
	    startActivity(intent);
	}

}
