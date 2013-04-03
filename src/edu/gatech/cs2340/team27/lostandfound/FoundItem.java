package edu.gatech.cs2340.team27.lostandfound;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;

public class FoundItem extends Activity {

	private ArrayList<Item> foundlist;

	/**
	 * Processes the onCreate event. create a namelist, arrayadapter and use
	 * listview to show a list of found items when click to found item, redirect
	 * to detail info
	 * 
	 * @param savedInstanceState
	 *            default
	 * @param listview
	 *            List of found item
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			foundlist = Items.getInstance().getFound();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} catch (ParseException e) {
		}
		setContentView(R.layout.activity_found_item);
		String[] nameList = new String[foundlist.size()];
		int a = 0;
		for (Item i : foundlist) {
			nameList[a] = i.getName();
			a++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, nameList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				FoundItemInfo.itemname = foundlist.get(position).getName();
				FoundItemInfo.discription = foundlist.get(position).getDescri();
				FoundItemInfo.date = foundlist.get(position).getFoundDate();
				FoundItemInfo.location = foundlist.get(position).getLoca();
				FoundItemInfo.catagory = foundlist.get(position).getCategory();
				Intent intent = new Intent(parent.getContext(),
						FoundItemInfo.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * Processes the onCreateOptionsMenu event.
	 * 
	 * @param menu
	 *            menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.found_item, menu);
		return true;
	}

	/**
	 * Processes the cancel button's onClick event.
	 * 
	 * @param view
	 *            default
	 */
	public void returnParent(View view) {
		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}

}
