package edu.gatech.cs2340.team27.lostandfound;

import java.io.IOException;

import edu.gatech.cs2340.team27.lostandfound.data.Admin;
import edu.gatech.cs2340.team27.lostandfound.data.Users;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CreateAdmin extends Activity {
	private EditText email;
	private EditText password;
	private EditText pswdConfirm;
	private EditText realname;
	private EditText phone;

	/**
	 * build in method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_admin);
		email = (EditText) findViewById(R.id.editText3);
		password = (EditText) findViewById(R.id.editText4);
		pswdConfirm = (EditText) findViewById(R.id.editText5);
		realname = (EditText) findViewById(R.id.editText1);
		phone = (EditText) findViewById(R.id.editText2);
	}

	/**
	 * build in method
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_admin, menu);
		return true;
	}

	/**
	 * go back to login page
	 * 
	 * @param view
	 *            default
	 */
	public void goBack(View view) {
		Intent intent = new Intent(this, LoginAdmin.class);
		startActivity(intent);
	}

	/**
	 * clear password and confirm textfields
	 */
	public void clear() {
		password.setText("");
		pswdConfirm.setText("");
	}

	/**
	 * clear email
	 */
	public void emailclear() {
		email.setText("");

	}

	/**
	 * get email, password and confirmpassword if password and confirmpassword
	 * doesnt match pop up wrong message if email is already existed pop up
	 * wrong message otherwise create a new account
	 * 
	 * @param view
	 *            default
	 * @param userText
	 *            email Text
	 * @param psText
	 *            password Text
	 * @param pConfirm
	 *            password confirm
	 * @param nameText
	 *            real name text
	 * @param phoneText
	 *            phone number text
	 * @throws ClassNotFoundException
	 *             when class is not found
	 * @throws IOException
	 *             when file is not found
	 */
	public void registerAccount(View view) throws IOException,
			ClassNotFoundException {
		String userText = email.getText().toString();
		String psText = password.getText().toString();
		String pConfirm = pswdConfirm.getText().toString();
		String nameText = realname.getText().toString();
		String phoneText = phone.getText().toString();
		if (!psText.equals(pConfirm)) {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage(
							"Confirm password is different from your password")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									clear();
								}
							}).show();
			return;
		}
		boolean registerStatus = ((Admin) (Users.getInstance().getCurrentUser()))
				.addAdmin(userText, psText, nameText, phoneText);
		if (registerStatus)
			goBack(view);
		else {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("User name already existed.")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									clear();
									emailclear();
								}
							}).show();
		}
	}

}
