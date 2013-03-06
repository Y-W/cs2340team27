package edu.gatech.cs2340.team27.lostandfound;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.data.Users;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UserList extends Activity {
	
	private List<User> userlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			userlist = Users.getInstance().getListofUsers();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} 
		setContentView(R.layout.activity_user_list);
		String[] usernameList=new String[userlist.size()];
		int a = 0;
		for(User i : userlist){
			usernameList[a]=i.getEmail();
			a++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, usernameList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    	AdministrateUser.email=userlist.get(position).getEmail();
		    	Intent intent = new Intent(parent.getContext(), AdministrateUser.class);
				startActivity(intent);
		    }
		}); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_list, menu);
		return true;
	}
	/**
	 * redirects to loginAdmin page
	 */
	public void loginAdminAttempt(View view) {
		Intent intent = new Intent(this, LoginAdmin.class);
	    startActivity(intent);
	}


}
