package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import edu.gatech.cs2340.team27.lostandfound.R;

public class Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
