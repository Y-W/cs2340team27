package edu.gatech.cs2340.team27.lostandfound;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import edu.gatech.cs2340.team27.lostandfound.data.Item.Category;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.Users;

public class FoundItemInput extends Activity {

	ArrayAdapter<String> adapter;
	Category cate;
	static Bitmap imgTmp;

	/**
	 * Processes the onCreate event.
	 * 
	 * @param savedInstanceState
	 *            Android system parameter
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found_item_input);
		String[] category = { "Animal", "Cloth", "Electronics", "Other" };
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, category);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		spinner.setVisibility(View.VISIBLE);

	}

	/**
	 * Processes the onCreateOptionsMenu event.
	 * 
	 * @param menu
	 *            Android system parameter
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.found_item_input, menu);
		return true;
	}

	/**
	 * Processes the onClick event of the confirm button.
	 * 
	 * @param view
	 *            Android system parameter
	 * @throws ClassNotFoundException
	 *             throw when class is not found
	 * @throws IOException
	 *             throw when file is not found
	 * @throws ParseException
	 *             throw when parse input fails
	 */
	public void confirmFoundItemInfo(View view) throws IOException,
			ClassNotFoundException, ParseException {

		
	    
		String name = ((TextView) (this.findViewById(R.id.editText1)))
				.getText().toString();
		String location = ((TextView) (this.findViewById(R.id.editText2)))
				.getText().toString();
		String description = ((TextView) (this.findViewById(R.id.editText3)))
				.getText().toString();
		DatePicker dp = (DatePicker) (this.findViewById(R.id.datePicker1));
		Date date = new Date(dp.getYear() - 1900, dp.getMonth(),
				dp.getDayOfMonth());
		if (cate != null) {
			Items.getInstance().addItem(ItemStatus.FOUND, name, location,
					description, date, Users.getInstance().getCurrentUser(),
					cate);// TODO give current user.
		} else {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Please select category")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
			return;
		}
//		File storageDir = new File(
//			    Environment.getExternalStoragePublicDirectory(
//			        Environment.DIRECTORY_PICTURES
//			    ), 
//			    name.replace(' ', '_')+date.getTime()+".jpg"
//			);
//		
//		FileOutputStream fileOutputStream = new FileOutputStream(storageDir.getPath());
		//FileOutputStream fos = openFileOutput(name.replace(' ', '_')+date.getTime()+".jpg", Context.MODE_PRIVATE);
		//FileOutputStream fos = openFileOutput("abc.jpg", Context.MODE_PRIVATE);
		File file = new File("/storage/sdcard0/DCIM/100MEDIA/abc.jpg");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		imgTmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);

		bos.flush();

		bos.close();
		fos.close();
		DisplayItem.setList(Items.getInstance().filter(null, null,
				ItemStatus.FOUND, null));
		Intent intent = new Intent(this, DisplayItem.class);
		startActivity(intent);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle extras = data.getExtras();
	    imgTmp = (Bitmap) extras.get("data");
	}

	/**
	 * Processes the onClick event of the cancel button.
	 * 
	 * @param view
	 *            Android system parameter
	 */
	public void cancelFoundItemInfo(View view) {
		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}
	
	/**
	 * Takes photo.
	 * 
	 * @param view
	 *            Android system parameter
	 */
	public void takePhoto(View view) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(takePictureIntent, 0);
	    
	}

	private class SpinnerSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
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

}
