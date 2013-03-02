package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LostItem extends Activity {
	/**
	 * Processes the onCreate event.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_item);
		String[] temp={"a", "b", "c"};
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, temp);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        
		    }

		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item, menu);
		return true;
	}

}
