package edu.gatech.cs2340.team27.lostandfound;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.data.Users;

public class MatchList extends Activity {

	private List<Item> matchList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			matchList = Users.getInstance().getPotentialMatch();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} catch (ParseException e) {
		}
		setContentView(R.layout.activity_match_list);
		List<String> founderList = new LinkedList<String>();
		for(Item i : matchList){
			founderList.add(i.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, founderList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    	
		    	Intent intent = new Intent(parent.getContext(), AdministrateUser.class);
				startActivity(intent);
		    }
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_list, menu);
		return true;
	}

}
