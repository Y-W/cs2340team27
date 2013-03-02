package edu.gatech.cs2340.team27.lostandfound.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.team27.lostandfound.data.Item;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Handles communication
 * 
 * @author Yijie Wang
 * @version 0.1
 */
public class Communication {
	private static Communication onlyInstance;
	private static Context appContext;
	private static final String prefName = "lostAndFound";
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;

	/**
	 * Returns the only instance of Communication. If there's no instance ever
	 * created, then create one.
	 * 
	 * @return the only instance of Communication
	 */
	public static Communication getInstance() {
		if (onlyInstance == null) {
			onlyInstance = new Communication();
		}
		return onlyInstance;
	}

	private Context getContext() {
		return appContext;
	}

	/**
	 * set the AppContext to be used
	 * 
	 * @param appContext
	 *            the AppContext to be used
	 */
	public static void setAppContext(Context appContext) {
		Communication.appContext = appContext;
	}

	private HashMap<String, String> data;
	private String currentUsername;
	private String currentPassword;
	private static final String filename = "Data";
	private static final String digestMethod = "SHA-1";
	private static final int lockTime = 3;

	/**
	 * default Constructor of Communication
	 */
	@SuppressWarnings("unchecked")
	protected Communication() {
		try {
			// FileInputStream in= appContext.openFileInput(filename);
			// ObjectInputStream oIn=new ObjectInputStream(in);
			// oIn.close();
			// in.close();
			pref = getContext().getSharedPreferences(prefName, 0);
			editor = pref.edit();
			data = (HashMap<String, String>) pref.getAll();
		} catch (Exception e) {
			Log.v("reading fails", "reading fails");
		}
		createAccount("test", "test", false);
	}

	/**
	 * Stores data.
	 */
	protected void finalize() throws IOException {
		// FileOutputStream out=
		// appContext.openFileOutput(filename,Context.MODE_PRIVATE);
		// ObjectOutputStream oOut=new ObjectOutputStream(out);
		// oOut.writeObject(data);
		// oOut.flush();
		// oOut.close();
		// out.close();
		submit();
	}

	private void submit() {
		for (String s : data.keySet()) {
			editor.putString(s, data.get(s));
		}
		editor.commit();
	}

	/**
	 * Status of login attempt.
	 */
	public enum LogStatus {
		SUCCESS, LOCKED, FAILURE;
	}

	/**
	 * process login attempt
	 * 
	 * @param username
	 *            intended user name
	 * @param password
	 *            intended password
	 * @return the status of login attempt
	 */
	public LogStatus loginAttempt(String username, String password) {
		// MessageDigest md=null;
		// try {
		// md = MessageDigest.getInstance(digestMethod);
		// } catch (NoSuchAlgorithmException e) {
		// //impossible to reach here
		// }
		// md.reset();
		// md.digest(password.getBytes());
		// String digest=new String(md.digest());
		String query = "USER/" + username.replace('/', '_');
		if (data.get(query) == null) {
			return LogStatus.FAILURE;
		}
		if (Integer.parseInt(data.get(query + "/COUNTER")) >= lockTime) {
			return LogStatus.LOCKED;
		}
		if (data.get(query + "/PASSWORD").equals(password)) {
			currentUsername = username;
			currentPassword = password;
			data.put(query + "/COUNTER", "0");
			submit();
			// try {
			// FileOutputStream out =
			// appContext.openFileOutput(filename,Context.MODE_PRIVATE);
			// ObjectOutputStream oOut=new ObjectOutputStream(out);
			// oOut.writeObject(data);
			// oOut.flush();
			// oOut.close();
			// out.close();
			// }
			// catch(Exception e){
			//
			// }
			return LogStatus.SUCCESS;
		} else {
			data.put(
					query + "/COUNTER",
					Integer.valueOf(
							Integer.parseInt(data.get(query + "/COUNTER")) + 1)
							.toString());
			// try {
			// FileOutputStream out =
			// appContext.openFileOutput(filename,Context.MODE_PRIVATE);
			// ObjectOutputStream oOut=new ObjectOutputStream(out);
			// oOut.writeObject(data);
			// oOut.flush();
			// oOut.close();
			// out.close();
			// }
			// catch(Exception e){
			//
			// }
			submit();
			return LogStatus.FAILURE;
		}
	}

	/**
	 * create an account
	 * 
	 * @param username
	 *            desired user name
	 * @param password
	 *            desired password
	 * @param priviliged
	 *            true if want to create an administrator, false if want to
	 *            create a normal user
	 * @return true if success, false otherwise
	 */
	public boolean createAccount(String username, String password,
			boolean priviliged) {
		String query = "USER/" + username.replace('/', '_');
		if (data.get(query) == null) {
			data.put(query, Long.toString((System.currentTimeMillis())));
			data.put(query + "/COUNTER", "0");
			// MessageDigest md=null;
			// try {
			// md = MessageDigest.getInstance(digestMethod);
			// } catch (NoSuchAlgorithmException e) {
			// //impossible to reach here
			// }
			// md.reset();
			// md.digest(password.getBytes());
			// String digest=new String(md.digest());
			data.put(query + "/PASSWORD", password);

			// FileOutputStream out;
			// try {
			// out = appContext.openFileOutput(filename,Context.MODE_PRIVATE);
			// ObjectOutputStream oOut=new ObjectOutputStream(out);
			// oOut.writeObject(data);
			// oOut.flush();
			// oOut.close();
			// out.close();
			// }
			// catch(Exception e){
			//
			// }
			submit();
			return true;
		}
		return false;
	}

	public String serialize(Object o) throws IOException {
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 XMLEncoder xmlEncoder = new XMLEncoder(out);
		 xmlEncoder.writeObject(o);
		 xmlEncoder.flush();
		 return out.toString()+"</java>";
//		FileOutputStream fileOut = new FileOutputStream("ser.ser");
//		ObjectOutputStream out = new ObjectOutputStream(fileOut);
//		out.writeObject(o);
//		out.close();
//		fileOut.close();
	}

	public Object deserialize(String str) throws IOException, ClassNotFoundException {
		 XMLDecoder xmlDecoder = new XMLDecoder(new
		 ByteArrayInputStream(str.getBytes()));
		 return xmlDecoder.readObject();
//		FileInputStream fileIn = new FileInputStream("ser.ser");
//		ObjectInputStream in = new ObjectInputStream(fileIn);
//		Object ret = in.readObject();
//		in.close();
//		fileIn.close();
//		return ret;
	}

	public void addItem(String username, Item item) throws IOException, ClassNotFoundException {
		ArrayList<Item> items = getItems(username);
		items.add(item);
		data.put(username+"/ITEMS", items);
	}
	
	public void addItem(Item item) {
		if (item.getLoser()!=null) {
			
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Item> getItems(String username) throws IOException, ClassNotFoundException {
		return (ArrayList<Item>) deserialize(data.get(username+"/ITEMS"));
	}
	
	public ArrayList<Item> getItems() {
		data.get("/ITEMS");
		return null;
	}
}
