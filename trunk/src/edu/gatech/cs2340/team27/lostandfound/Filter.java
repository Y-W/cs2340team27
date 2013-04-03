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
import android.widget.TextView;
import edu.gatech.cs2340.team27.lostandfound.data.Item.Category;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.data.Items;

/**
 * This class allow users to filter the items to be displayed
 * @author Yiqi Chen
 */
public class Filter extends Activity {

	ArrayAdapter<String> cateAdapter;
	ArrayAdapter<String> statusAdapter;
	Category cate;
	ItemStatus status;
	Date date;
	String name;
	boolean filterCate;
	boolean filterStatus;
	boolean filterDate;
	boolean filterName;
	

	/**
	 * Create the page
	 * @param savedInstanceState Default android parameter
	 */

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
		CheckBox checkStatus=(CheckBox)findViewById(R.id.checkBox1); 
		CheckBox checkCate=(CheckBox)findViewById(R.id.checkBox2); 
		CheckBox checkDate=(CheckBox)findViewById(R.id.checkBox3); 
		CheckBox checkName=(CheckBox)findViewById(R.id.checkBox4); 
		
		checkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
			/**
			 * Action caused by the event
			 * @param buttonView Default checkbox parameter
			 * @param isChecked Whether the box is checked
			 */
			@Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(isChecked){ 
                    filterStatus = true;
                } 
                else {
                	filterStatus = false;
                }
            } 
        }); 
		checkCate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
			/**
			 * Action caused by the event
			 * @param buttonView Default checkbox parameter
			 * @param isChecked Whether the box is checked
			 */
			@Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(isChecked){ 
                	filterCate = true;
                } 
                else {
                	filterCate = false;
                }
            } 
        }); 
		checkDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
			/**
			 * Action caused by the event
			 * @param buttonView Default checkbox parameter
			 * @param isChecked Whether the box is checked
			 */
			@Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
            	if(isChecked){ 
                	filterDate = true;
                } 
                else {
                	filterDate = false;
                }
            } 
        }); 
		checkName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
			/**
			 * Action caused by the event
			 * @param buttonView Default checkbox parameter
			 * @param isChecked Whether the box is checked
			 */
			@Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(isChecked){ 
                    filterName = true;
                } 
                else {
                	filterName = false;
                }
            } 
        }); 
	}

	/**
	 * Inflate the menu
	 * @param menu default
	 * @return successful
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}
	
	/**
	 * Action caused by clicking "filter"
	 * @param view Default android parameter
	 * @throws IOException throw when file is not found
	 * @throws ClassNotFoundException throw when class is not found
	 * @throws ParseException throw when parse is incorrect
	 */
	public void filterItems(View view) throws IOException, ClassNotFoundException, ParseException {
		DatePicker dp=(DatePicker)(this.findViewById(R.id.datePicker1));
		date=new Date(dp.getYear() - 1900, dp.getMonth(), dp.getDayOfMonth());
		name=((TextView)(this.findViewById(R.id.editText1))).getText().toString();
		if(!filterStatus) {
			status = null;
		}
		if(!filterCate) {
			cate = null;
		}
		if(!filterDate) {
			date = null;
		}
		if(!filterName){
			name=null;
		}
		DisplayItem.setList(Items.getInstance().filter(cate, date, status,name));
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
	
	/**
	 * Action caused by selecting an item
	 * @author Yiqi Chen
	 */
	private class cateSelectedListener implements OnItemSelectedListener {
		
		/**
		 * Action caused by selecting an item
		 * @param arg0 default
		 * @param arg1 default
		 * @param arg2 default
		 * @param arg3 default
		 */
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
  
		/**
		 * Action when nothing is selected. 
		 * @param arg0 default
		 */
		public void onNothingSelected(AdapterView<?> arg0) {
        	cate = null;
        }  
	}
	
	/**
	 * Action caused by selecting an item
	 * @author Yiqi Chen
	 */
	private class statusSelectedListener implements OnItemSelectedListener {
		
		/**
		 * Action caused by selecting an item
		 * @param arg0 default
		 * @param arg1 default
		 * @param arg2 default
		 * @param arg3 default
		 */
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
  
		/**
		 * Action when nothing is selected. 
		 * @param arg0 default
		 */
		public void onNothingSelected(AdapterView<?> arg0) {
        	status = null;
        }  
	}

}
