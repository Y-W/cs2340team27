package edu.gatech.cs2340.team27.lostandfound;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
/**
 * Processes the display of the details of lost items.
 * @author all
 *
 */
public class LostItemInfo extends Activity {
	/**
	 * itemname contains item name
	 */
	public static String itemname;
	/**
	 * discription contains description of item
	 */
	public static String discription;
	/**
	 * date contains lost date
	 */
	public static Date date;
	/**
	 * location contains lost location
	 */
	public static String location;
	@Override
	/**
	 * get details from lostitem list
	 * set text
	 * @param savedInstanceState Android system bundle
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_item_info);
		TextView tv=(TextView) this.findViewById(R.id.itemname);
    	tv.setText(itemname);
    	TextView tv4=(TextView) this.findViewById(R.id.textView4);
    	tv4.setText(discription);
    	TextView tv6=(TextView) this.findViewById(R.id.dolhere);
    	tv6.setText(date.toString());
    	TextView tv8=(TextView) this.findViewById(R.id.lochere);
    	tv8.setText(location);
	}

	@Override
	/**
	 * build in method
	 * @param menu Android system parameter
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item_info, menu);
		return true;
	}
	/**
	 * go back to LostItem Page
	 * @param view Android system parameter
	 */
	public void LostItemAttempt(View view){
		Intent intent = new Intent(this,LostItem.class);
		startActivity(intent);
	}

}
