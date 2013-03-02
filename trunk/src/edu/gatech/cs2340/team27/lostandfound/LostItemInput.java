package edu.gatech.cs2340.team27.lostandfound;

import java.util.Date;

import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.Users;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class LostItemInput extends Activity {
	/**
	 * Processes the onCreate event.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_item_input);
	}
	/**
	 * Processes the onCreateOptionsMenu event.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item_input, menu);
		return true;
	}
	/**
	 * Processes the onClick event of the confirm button.
	 * @param view
	 */
	public void confirmLostItemInfo(View view){
		String name=((TextView)(this.findViewById(R.id.editText1))).getText().toString();
		String location=((TextView)(this.findViewById(R.id.editText2))).getText().toString();
		String description=((TextView)(this.findViewById(R.id.editText3))).getText().toString();
		DatePicker dp=(DatePicker)(this.findViewById(R.id.datePicker1));
		Date date=new Date(dp.getYear() - 1900, dp.getMonth(), dp.getDayOfMonth());
		Items.getInstance().addItem(ItemStatus.LOST, name, location, description, date, null);//TODO give current user.
		Intent intent = new Intent(this,HomePage.class);
		startActivity(intent);
	}
	/**
	 * Processes the onClick event of the cancel button.
	 * @param view
	 */
	public void cancelLostItemInfo(View view){
		Intent intent = new Intent(this,HomePage.class);
		startActivity(intent);
	}
}
