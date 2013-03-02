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
import java.util.Date;
import java.util.HashMap;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Handles communication
 * 
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
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static Communication getInstance() throws IOException, ClassNotFoundException {
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
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	protected Communication() throws IOException, ClassNotFoundException {
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
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public boolean createAccount(String username, String password,
			boolean priviliged) throws IOException, ClassNotFoundException {
		username = username.replace('/', '_');
		String query = "USER/" + username;
		
		if (data.get(query) == null) {
			if (data.get("Users")==null) data.put("Users", "");
			String str = data.get("Users");
			ArrayList<String> sarr = (ArrayList<String>) deserialize(str);
			sarr.add(username);
			data.put("Users", serialize(sarr));
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
		ArrayList<String> items = getItems(username);
		String str = serializeItem(item);
		if (items.contains(str)) return;
		items.add(str);
		data.put(username+"/ITEMS", serialize(items));
		submit();
	}
	
	public void addItem(Item item) throws IOException, ClassNotFoundException {
		if (item.getLoser()!=null) {
			addItem(item.getLoser().getName(), item);
		}
		else 
			addItem(item.getFounder().getName(), item);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getItems(String username) throws IOException, ClassNotFoundException {
		return (ArrayList<String>) deserialize(data.get(username+"/ITEMS"));
	}
	
	public ArrayList<Item> getItems() throws IOException, ClassNotFoundException {
		if (data.get("Users")==null) data.put("Users", "");
		String str = data.get("Users");
		ArrayList<String> namearr = (ArrayList<String>) deserialize(str);
		ArrayList<Item> ret = new ArrayList<Item>();
		if (namearr.size()==0) {
//			Items.getInstance().initialize(ret);
//			return Items.getInstance();
			return ret;
		}
		ArrayList<String> eachperson = new ArrayList<String>();
		for (String eachName : namearr) {
			eachperson = getItems(eachName);
			for (String eachItem : eachperson) {
				Item tmp = deserializeItem(eachItem);
				if (ret.contains(tmp)) continue;
				ret.add(tmp);
			}
		}
//		Items.getInstance().initialize(ret);
//		return Items.getInstance();
		return ret;
	}
	
	public String serializeItem(Item i) throws IOException{
		ArrayList<String> sarr = new ArrayList<String>();
		sarr.add(serialize(i.getStatus()));
		sarr.add(i.getName());
		sarr.add(i.getLoca());
		sarr.add(i.getDescri());
		sarr.add(serialize(i.getLostDate()));
		sarr.add(serialize(i.getFoundDate()));
		sarr.add(serialize(i.getResolvedDate()));
		sarr.add(serializeUser(i.getLoser()));
		sarr.add(serializeUser(i.getFounder()));
		return serialize(sarr);
	}

	public String serializeUser(User u) throws IOException {
		ArrayList<String> sarr = new ArrayList<String>();
		sarr.add(u.getName());
		sarr.add(u.getAddress());
		sarr.add(u.getPhoneNumber());
		sarr.add(u.getEmail());
		return serialize(sarr);
	}
	
	public Item deserializeItem(String str) throws IOException, ClassNotFoundException {
		ArrayList<String> sarr = (ArrayList<String>) deserialize(str);
		ItemStatus status = (ItemStatus) deserialize(sarr.get(0));
		String name = sarr.get(1);
		String location= sarr.get(2);
		String description= sarr.get(3);
		Date lostDate = (Date) deserialize(sarr.get(4));
		Date foundDate = (Date) deserialize(sarr.get(5));
		Date resolvedDate = (Date) deserialize(sarr.get(6));
		User loser = deserializeUser(sarr.get(7));
		User founder= deserializeUser(sarr.get(8));
		Date date;
		User user;
		if (lostDate!=null) date = lostDate;
		else if (foundDate!=null) date = foundDate;
		else date = resolvedDate;
		if (loser!=null) user = loser;
		else user = founder;
		return new Item(status, name, location, description, date, user);
	}
	
	public User deserializeUser(String str) throws IOException, ClassNotFoundException {
		ArrayList<String> sarr = (ArrayList<String>) deserialize(str);
		String name = sarr.get(0);
		String address = sarr.get(1);
		String phoneNumber = sarr.get(2);
		String email = sarr.get(3);
		return new User(name, address, phoneNumber, email);
	}
}
