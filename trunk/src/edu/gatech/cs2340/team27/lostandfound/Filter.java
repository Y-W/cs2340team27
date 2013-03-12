package edu.gatech.cs2340.team27.lostandfound;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import edu.gatech.cs2340.team27.lostandfound.data.Item.Category;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.data.Items;

public class Filter extends Activity {

	ArrayAdapter<String> cateAdapter;
	ArrayAdapter<String> statusAdapter;
	Category cate;
	ItemStatus status;
	Date date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		String[] category = {"Animal", "Cloth", "Electronics", "Other"};
		cateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
		Spinner cateSpinner = (Spinner) findViewById(R.id.spinner1);
		cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cateSpinner.setAdapter(cateAdapter);
		cateSpinner.setOnItemSelectedListener(new cateSelectedListener());
		cateSpinner.setVisibility(View.VISIBLE);
		String[] itemStatus = {"Lost", "Found"};
		statusAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,itemStatus);
		Spinner statusSpinner = (Spinner) findViewById(R.id.spinner2);
		statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		statusSpinner.setAdapter(statusAdapter);
		statusSpinner.setOnItemSelectedListener(new statusSelectedListener());
		statusSpinner.setVisibility(View.VISIBLE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}
	
	public void filterItems(View view) throws IOException, ClassNotFoundException, ParseException {
		DatePicker dp=(DatePicker)(this.findViewById(R.id.datePicker1));
		date=new Date(dp.getYear() - 1900, dp.getMonth(), dp.getDayOfMonth());
		CheckBox checkStatus=(CheckBox)findViewById(R.id.checkBox1); 
		CheckBox checkCate=(CheckBox)findViewById(R.id.checkBox2); 
		CheckBox checkDate=(CheckBox)findViewById(R.id.checkBox3); 
		checkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                    status = null;
                } 
            } 
        }); 
		checkCate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                    cate = null;
                } 
            } 
        }); 
		checkDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                    date = null;
                }
            } 
        }); 
		DisplayItem.setList(Items.getInstance().filter(cate, date, status));
		Intent intent = new Intent(this, DisplayItem.class);
	    startActivity(intent);
	}
	
	/**
	 * Processes the cancel button's onClick event.
	 * @param view default
	 */
	public void returnParent(View view) {
		Intent intent = new Intent(this, HomePage.class);
	    startActivity(intent);
	}
	
	private class cateSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
			switch(arg2) {
			case 0: 
				cate = Category.ANIMAL;
				break;
			case 1:
				cate = Category.CLOTH;
				break;
			case 2: 
				cate = Category.ELECTRONICS;
				break;
			case 3:
				cate = Category.OTHER;
			}
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {
        	cate = null;
        }  
	}
	
	private class statusSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
			switch(arg2) {
			case 0: 
				status = ItemStatus.LOST;
				break;
			case 1:
				status = ItemStatus.FOUND;
				break;
			}
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {
        	status = null;
        }  
	}

}
