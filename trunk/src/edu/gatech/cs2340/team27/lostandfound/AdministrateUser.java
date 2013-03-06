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

public class AdministrateUser extends Activity {
	public static String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administrate_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.administrate_user, menu);
		return true;
	}
	
	/**
	 * redirects to UserList page
	 */
	public void userListAttempt(View view) {
		Intent intent = new Intent(this, UserList.class);
	    startActivity(intent);
	}
/**
 * delete user
 * @param view
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public void deleteuser(View view) throws IOException, ClassNotFoundException{
		if(((Admin)(Users.getInstance().getCurrentUser())).deleteUser(email)){
			new AlertDialog.Builder(this)
		    .setTitle("Success")
		    .setMessage("Delete this User Successfully.")
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        }
		     })
		     .show();
			return;
		}
		else{
			new AlertDialog.Builder(this)
		    .setTitle("Wrong")
		    .setMessage("Cannot delete yourself!")
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        }
		     })
		     .show();
			return;
		}
	}
/**
 * unlock user
 * @param view
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public void unlockuser(View view) throws IOException, ClassNotFoundException{
		if(Users.getInstance().isPriviliged()){
			((Admin)(Users.getInstance().getCurrentUser())).unlockUser(email);
			new AlertDialog.Builder(this)
		    .setTitle("Success")
		    .setMessage("Unlock this User Successfully.")
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        }
		     })
		     .show();
			return;
		}
	}

}
