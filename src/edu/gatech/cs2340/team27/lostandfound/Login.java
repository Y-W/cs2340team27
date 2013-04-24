package edu.gatech.cs2340.team27.lostandfound;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import edu.gatech.cs2340.team27.lostandfound.data.Users;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;
import edu.gatech.cs2340.team27.lostandfound.model.Communication.LogStatus;

/**
 * Login page check username password can lock user if enter wrong password
 * three times
 * 
 * @author all
 * 
 */
public class Login extends Activity {

	/**
	 * a simplified name for edu.gatech.cs2340.team27.lostandfound.MESSAGE
	 */
	public final static String STATUS_MESSAGE = "edu.gatech.cs2340.team27.lostandfound.MESSAGE";
	private MediaPlayer example;
	@Override
	/**
	 * build in method
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Communication.setAppContext(this.getApplicationContext());
		example = new MediaPlayer().create(this, R.raw.everytime);
		example.start();
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
	 * get username and password, and see if the password is correct if it is
	 * correct, redirects to home page if the password is incorrect, pop up
	 * wrong message if the password is incorrect for three times in a row, lock
	 * the account
	 * 
	 * @param view
	 *            default
	 * @throws ClassNotFoundException
	 *             when class is not found
	 * @throws IOException
	 *             when file is not found
	 */
	public void loginAttempt(View view) throws IOException,
			ClassNotFoundException {
		Intent intent = new Intent(this, HomePage.class);
		int status = checkPwd();
		if (status == 1) {
			startActivity(intent);
		}

		else if (status == -1) {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Wrong Password.")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									passwordclear();
								}
							}).show();
			return;
		} else if (status == 0) {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Account Locked!")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									usernameclear();
									passwordclear();
								}
							}).show();
			return;
		}

	}

	/**
	 * check pw status
	 * 
	 * @return check password status
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int checkPwd() throws IOException, ClassNotFoundException {
		EditText username = (EditText) findViewById(R.id.editText1);
		EditText password = (EditText) findViewById(R.id.editText2);
		String userText = username.getText().toString();
		String psText = password.getText().toString();
		LogStatus status = edu.gatech.cs2340.team27.lostandfound.model.Communication
				.getInstance().loginAttempt(userText, psText);
		if (status == LogStatus.SUCCESS)
			return 1;// success
		else if (status == LogStatus.FAILURE)
			return -1;// fail
		else
			return 0;// locked
	}

	/**
	 * clear usename
	 */
	public void usernameclear() {
		EditText username = (EditText) findViewById(R.id.editText1);
		username.setText("");
	}

	/**
	 * clear password
	 */
	public void passwordclear() {
		EditText password = (EditText) findViewById(R.id.editText2);
		password.setText("");
	}

	/**
	 * redirects to register page
	 * 
	 * @param view
	 *            default
	 */
	public void registerAttempt(View view) {
		Intent intent = new Intent(this, Register.class);
		startActivity(intent);
	}

	/**
	 * redirects to loginAdmin page
	 */
	public void loginAdminAttempt(View view) throws IOException,
			ClassNotFoundException {
		Intent intentadmin = new Intent(this, LoginAdmin.class);

		if (checkPwd() == 1) {
			if (Users.getInstance().isPriviliged()) {
				startActivity(intentadmin);
			} else {
				new AlertDialog.Builder(this)
						.setTitle("Error")
						.setMessage("Wrong Password.")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										passwordclear();
									}
								}).show();
				return;
			}
		}

		else if (checkPwd() == -1) {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Wrong Password.")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									passwordclear();
								}
							}).show();
			return;
		} else if (checkPwd() == 0) {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Account Locked!")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									usernameclear();
									passwordclear();
								}
							}).show();
			return;
		}

		else {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Wrong Password.")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									passwordclear();
								}
							}).show();
			return;
		}

	}

}
