package edu.gatech.cs2340.team27.lostandfound;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * LostItem page
 * show lost item list
 * @author all
 *
 */
public class LostItem extends Activity {
	
	private ArrayList<Item> lostlist;
	
	/**
	 * Processes the onCreate event.
	 * create a namelist, arrayadapter and use listview to show a
	 * list of lost items
	 * when click to lost item, redirect to detail info
	 * @param savedInstanceState default
	 * @param listview List of lost item
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			lostlist = Items.getInstance().getLost();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} catch (ParseException e) {
		}
		setContentView(R.layout.activity_lost_item);
		String[] nameList=new String[lostlist.size()];
		int a = 0;
		for(Item i : lostlist){
			nameList[a]=i.getName();
			a++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, nameList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		    	LostItemInfo.itemname=lostlist.get(position).getName();
		    	LostItemInfo.discription=lostlist.get(position).getDescri();
		    	LostItemInfo.date=lostlist.get(position).getLostDate();
		    	LostItemInfo.location=lostlist.get(position).getLoca();
		    	LostItemInfo.catagory=lostlist.get(position).getCategory();
		    	Intent intent = new Intent(parent.getContext(), LostItemInfo.class);
				startActivity(intent);
		    }
		}); 
	}

	/**
	 * Processes the onCreateOptionsMenu event.
	 * @param menu menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lost_item, menu);
		return true;
	}
	
	/**
	 * Processes the cancel button's onClick event.
	 * @param view default
	 */
	public void returnParent(View view) {
		Intent intent = new Intent(this, HomePage.class);
	    startActivity(intent);
	}

}
