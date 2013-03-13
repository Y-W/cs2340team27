package edu.gatech.cs2340.team27.lostandfound;

import java.util.LinkedList;
import java.util.List;

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
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;

/**
 * Display filtered items. 
 * @author Yiqi Chen
 */
public class DisplayItem extends Activity {

private static LinkedList<Item> list;
	
	/**
	 * Processes the onCreate event.
	 * create a namelist, arrayadapter and use listview to show a
	 * list of items
	 * when click to found item, redirect to detail info
	 * @param savedInstanceState default
	 * @param listview List of found item
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_item);
		String[] nameList=new String[list.size()];
		int a = 0;
		for(Item i : list){
			nameList[a]=i.getName();
			a++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, nameList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		    	if(list.get(position).getStatus().equals(ItemStatus.FOUND)) {
		    		FoundItemInfo.itemname=list.get(position).getName();
			    	FoundItemInfo.discription=list.get(position).getDescri();
			    	FoundItemInfo.date=list.get(position).getFoundDate();
			    	FoundItemInfo.location=list.get(position).getLoca();
			    	FoundItemInfo.catagory=list.get(position).getCategory();
			    	Intent intent = new Intent(parent.getContext(), FoundItemInfo.class);
					startActivity(intent);
		    	}
		    	else {
		    		LostItemInfo.itemname=list.get(position).getName();
			    	LostItemInfo.discription=list.get(position).getDescri();
			    	LostItemInfo.date=list.get(position).getLostDate();
			    	LostItemInfo.location=list.get(position).getLoca();
			    	LostItemInfo.catagory=list.get(position).getCategory();
			    	Intent intent = new Intent(parent.getContext(), LostItemInfo.class);
					startActivity(intent);
		    	}
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
		getMenuInflater().inflate(R.menu.display_item, menu);
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
	
	/**
	 * Pass in the filtered list
	 * @param itemList the filtered list
	 */
	public static void setList(List<Item> itemList) {
		list = (LinkedList<Item>)itemList;
	}

}
