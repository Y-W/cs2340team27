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
import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Users;

public class MatchList extends Activity {

	private List<Item> matchList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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
		for (int i = 1; i < matchList.size(); i = i + 2)
			founderList.add(matchList.get(i).getName());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, founderList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				MatchInfo.foundItem = matchList.get(2 * position + 1);
				MatchInfo.lostItem = matchList.get(2 * position);
				Intent intent = new Intent(parent.getContext(), MatchInfo.class);
				startActivity(intent);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_list, menu);
		return true;
	}

	/**
	 * go back to home page
	 * 
	 * @param view
	 *            default
	 */
	public void goBack(View view) {
		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}
}
