package edu.gatech.cs2340.team27.lostandfound;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MatchInfo extends Activity {
	public static Item foundItem;
	public static Item lostItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_info, menu);
		return true;
	}

}
