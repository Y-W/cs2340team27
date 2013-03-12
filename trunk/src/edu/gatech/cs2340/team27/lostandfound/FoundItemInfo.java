package edu.gatech.cs2340.team27.lostandfound;

import java.util.Date;

import edu.gatech.cs2340.team27.lostandfound.data.Item;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FoundItemInfo extends Activity {
	/**
	 * itemname contains item name
	 */
	public static String itemname;
	/**
	 * discription contains description of item
	 */
	public static String discription;
	/**
	 * date contains found date
	 */
	public static Date date;
	/**
	 * location contains found location
	 */
	public static String location;
	/**
	 * catagory contains the catagory
	 */
	public static Item.Category catagory;
	
	
	/**
	 * get details from founditem list
	 * set text
	 * @param savedInstanceState Android system bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found_item_info);
		TextView tv=(TextView) this.findViewById(R.id.textView1);
    	tv.setText(itemname);
    	TextView tv4=(TextView) this.findViewById(R.id.textView4);
    	tv4.setText(discription);
    	TextView tv6=(TextView) this.findViewById(R.id.textView6);
    	tv6.setText(date.toString());
    	TextView tv8=(TextView) this.findViewById(R.id.textView8);
    	tv8.setText(location);
    	TextView tv10=(TextView) this.findViewById(R.id.textView10);
    	tv10.setText(catagory.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.found_item_info, menu);
		return true;
	}
	/**
	 * go back to FoundItem Page
	 * @param view Android system parameter
	 */
	public void FoundItemAttempt(View view){
		Intent intent = new Intent(this,DisplayItem.class);
		startActivity(intent);
	}

}
