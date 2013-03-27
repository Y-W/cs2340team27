package edu.gatech.cs2340.team27.lostandfound;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * An activity that handles the matching confirmation.
 * @author Yijie Wang
 *
 */
public class MatchInfo extends Activity {
	/**
	 * the target found item
	 */
	public static Item foundItem;
	/**
	 * the target lost item
	 */
	public static Item lostItem;
	
	/**
	 * get details from founditem list
	 * set text
	 * @param savedInstanceState Android system bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_info);
		TextView tv=(TextView) this.findViewById(R.id.itemname);
    	tv.setText(foundItem.getName());
    	TextView tv4=(TextView) this.findViewById(R.id.textView4);
    	tv4.setText(foundItem.getDescri());
    	TextView tv6=(TextView) this.findViewById(R.id.dolhere);
    	tv6.setText(foundItem.getFoundDate().toString());
    	TextView tv8=(TextView) this.findViewById(R.id.lochere);
    	tv8.setText(foundItem.getLoca());
    	TextView tv10=(TextView) this.findViewById(R.id.textView6);
    	tv10.setText(foundItem.getCategory().toString());
    	TextView tv12=(TextView) this.findViewById(R.id.textView9);
    	tv12.setText(foundItem.getFounder().getEmail());
	}
	/**
	 * default method
	 * @param menu the default parameter
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_info, menu);
		return true;
	}
	/**
	 * goes back to MatchList Page
	 * @param view Android system parameter
	 */
	public void cancel(View view){
		Intent intent = new Intent(this,MatchList.class);
		startActivity(intent);
	}
	/**
	 * confirms the match
	 * @param view Android system parameter
	 */
	public void match(View view){
		Items.getInstance().match(foundItem, lostItem);
		cancel(view);
	}
	
}
