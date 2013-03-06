package edu.gatech.cs2340.team27.lostandfound.model;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.ParseException;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.data.Users;
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
	private static final int lockTime = 3;

	/**
	 * default Constructor of Communication
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	protected Communication() throws IOException, ClassNotFoundException {
		try {
			pref = getContext().getSharedPreferences(prefName, 0);
			editor = pref.edit();
			data = (HashMap<String, String>) pref.getAll();
		} catch (Exception e) {
			Log.v("reading fails", "reading fails");
		}
		createAccount("test", "test", "", "", false);
		createAccount("admin", "admin", "", "", true);
	}

	/**
	 * Stores data.
	 */
	protected void finalize() throws IOException {
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
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public LogStatus loginAttempt(String username, String password) throws IOException, ClassNotFoundException {
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
			Users.getInstance().updateCurrentUser();
			return LogStatus.SUCCESS;
		} else {
			int locknum = Integer.parseInt(data.get(query + "/COUNTER")) + 1;
			data.put(
					query + "/COUNTER",
					Integer.valueOf(locknum).toString());
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
	 * @param realname
	 * @param phone
	 * @param priviliged
	 *            true if want to create an administrator, false if want to
	 *            create a normal user
	 * @return true if success, false otherwise
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public boolean createAccount(String username, String password, String realname, String phone, 
			boolean priviliged) throws IOException, ClassNotFoundException {
		username = username.replace('/', '_');
		String query = "USER/" + username;
		ArrayList<String> sarr = new ArrayList<String>();
		ArrayList<String> adminarr = new ArrayList<String>();
		if (data.get(query) == null) {
			if (data.get("Users")==null) data.put("Users", "");
			String str = data.get("Users");
			if (str!=null && !str.equals("")) {
				sarr = (ArrayList<String>) deserialize(str, sarr);
			}
			User newUser = new User(realname, phone, username,false);
			sarr.add(username);
			data.put("Users", serialize(sarr));
			data.put(query, Long.toString((System.currentTimeMillis())));
			data.put(query + "/INFO", serializeUser(newUser));
			data.put(query + "/COUNTER", "0");
			data.put(username + "/ITEMS", "[]");
			data.put(query + "/PASSWORD", password);
			if (priviliged) {
				if (data.get("AdminList")==null) data.put("AdminList", "");
				String adminlist = data.get("AdminList");
				if (adminlist!=null && !adminlist.equals("")) {
					adminarr = (ArrayList<String>) deserialize(adminlist, adminarr);
				}
				adminarr.add(username);
				data.put("AdminList", serialize(adminarr));
			}
			submit();
			return true;
		}
		return false;
	}

	/**
	 * unlock a user
	 * @param email The email of the user to be unlocked
	 */
	public void unlockUser(String email) {
		if (data.get(email+"/COUNTER")==null) return;
		else data.put(email+"/COUNTER","0");
		submit();
	}
	
	/**
	 * remove a user
	 * @param email The email address of the user to be removed
	 * @throws IOException
	 */
	public void removeUser(String email) throws IOException {
		ArrayList<String> sarr = new ArrayList<String>();
		if (data.get("Users")==null) data.put("Users", "");
		String str = data.get("Users");
		if (str!=null && !str.equals("")) {
			sarr = (ArrayList<String>) deserialize(str, sarr);
		}
		sarr.remove(email);
		data.put("Users", serialize(sarr));
		ArrayList<String> adminarr = new ArrayList<String>();
		if (data.get("AdminList")==null) data.put("AdminList", "");
		String adminlist = data.get("AdminList");
		if (adminlist!=null && !adminlist.equals("")) {
			adminarr = (ArrayList<String>) deserialize(adminlist, adminarr);
		}
		adminarr.remove(email);
		data.put("AdminList", serialize(adminarr));
		data.remove(email + "/INFO");
		data.remove(email + "/COUNTER");
		data.remove(email + "/PASSWORD");
		data.remove(email + "/ITEMS");
		submit();
	}
	
	public boolean checkPrivilege(String email) {
		ArrayList<String> adminarr = new ArrayList<String>();
		if (data.get("AdminList")==null) data.put("AdminList", "");
		String adminlist = data.get("AdminList");
		if (adminlist!=null && !adminlist.equals("")) {
			adminarr = (ArrayList<String>) deserialize(adminlist, adminarr);
		}
		if (adminarr.contains(email)) return true;
		else return false;
	}
	
	/**
	 * default serialize function
	 * 
	 * @param a
	 * 			The ArrayList to be serialized
	 * @return
	 * 			serializing result
	 * @throws IOException
	 */
	public String serialize(ArrayList<String> a) throws IOException {
		return serialize(a, "##");
	}
	
	/**
	 * serialize ItemStatus
	 * 
	 * @param a
	 * 			The ItemStatus to be serialized
	 * @return
	 * 			serializing result
	 */
	public String serialize(ItemStatus a) {
		return a.name();
	}
	
	/**
	 * deserialize ItemStatus
	 * 
	 * @param str  The String to be deserialized
	 * @return  deserializing result
	 */
	public ItemStatus deserialize(String str) {
		return ItemStatus.valueOf(str);
	}
	
	/**
	 * serialize date
	 * @param d  The date to be serialized
	 * @return  serializing result
	 */
	public String serialize(Date d) {
		if (d==null) return "[]";
		return DateFormat.getDateInstance().format(d);
	}
	
	/**
	 * advanced serialize function
	 * @param a  The ArrayList to be serialized
	 * @param delimiter  The delimiter to be added between entries
	 * @return serializing result
	 */
	public String serialize(ArrayList<String> a, String delimiter) {
		String ret = "";
		for (String each : a) {
			//don't escape here
			if (each==null || each.equals("")) each = "[]";
			ret = ret + each +delimiter;
		}
		return ret;
	}
	
	/**
	 * deserialize date
	 * @param str  The String to be deserialized
	 * @param ret  Any date object for overriding need
	 * @return deserializing result
	 * @throws ParseException
	 */
	public Date deserialize(String str, Date ret) throws ParseException {
		if (str==null || str.equals("[]")) return null;
		ret = DateFormat.getDateInstance().parse(str);
		return ret;
	}

	/**
	 * deserialize arraylist
	 * @param str  The String to be deserialized
	 * @param ret  Any arraylist object for overriding need
	 * @return deserializing result
	 */
	public ArrayList<String> deserialize(String str, ArrayList<String> ret) {
		return deserialize(str, ret, "##");
	}
	
	/**
	 * advanced function for deserializing arraylist
	 * @param str  The String to be deserialized
	 * @param ret  Any arraylist object for overriding need
	 * @param delimiter  The delimiter that is between entries
	 * @return deserializing result
	 */
	public ArrayList<String> deserialize(String str, ArrayList<String> ret, String delimiter) {
		ret.clear();
		if (str==null) return ret;
		String[] arr = str.split(delimiter);
		for (String each : arr) {
			if (each.equals("[]")) ret.add(null);
			else ret.add(each);
		}
		return ret;
	}

	/**
	 * add a item to certain user
	 * @param username  The email address of the user to whom the item is added
	 * @param item  The item to be added
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addItem(String username, Item item) throws IOException, ClassNotFoundException {
		ArrayList<String> items = getItems(username);
		String str = serializeItem(item);
		if (items.contains(str)) return;
		items.add(str);
		data.put(username+"/ITEMS", serialize(items));
		submit();
	}
	
	/**
	 * add the item and choose targeting users automatically
	 * @param item  The item to be added
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addItem(Item item) throws IOException, ClassNotFoundException {
		if (item.getLoser()!=null) {
			addItem(item.getLoser().getEmail(), item);
		}
		if (item.getFounder()!=null) 
			addItem(item.getFounder().getEmail(), item);
	}

	/**
	 * get all items of certain users
	 * @param username The email address of the user
	 * @return An arraylist that contains serials of all items 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getItems(String username) throws IOException, ClassNotFoundException {
		return deserialize(data.get(username+"/ITEMS"), new ArrayList<String>());
	}
	
	/**
	 * get items of all users
	 * @return An arraylist that contains items of all users
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public ArrayList<Item> getItems() throws IOException, ClassNotFoundException, ParseException {
		if (data.get("Users")==null) data.put("Users", "");
		String str = data.get("Users");
		ArrayList<String> namearr = new ArrayList<String>();
		namearr = deserialize(str, namearr);
		ArrayList<Item> ret = new ArrayList<Item>();
		if (namearr.size()==0) {
			return ret;
		}
		ArrayList<String> eachperson = new ArrayList<String>();
		for (String eachName : namearr) {
			eachperson = getItems(eachName);
			if (eachperson == null) continue;
			for (String eachItem : eachperson) {
				Item tmp = deserializeItem(eachItem);
				if (tmp==null) continue;
				if (ret.contains(tmp)) continue;
				ret.add(tmp);
			}
		}
		return ret;
	}
	
	/**
	 * serialize an item
	 * @param i The item to be serialized
	 * @return serializing result
	 * @throws IOException
	 */
	public String serializeItem(Item i) throws IOException{
		if (i==null) return "[]";
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
		return serialize(sarr, "%item%");
	}


	/**
	 * serialize an user
	 * @param u The user to be serialized
	 * @return serializing result
	 * @throws IOException
	 */
	public String serializeUser(User u) throws IOException {
		ArrayList<String> sarr = new ArrayList<String>();
		if (u==null) return "[]";
		sarr.add(u.getName());
		sarr.add(u.getPhoneNumber());
		sarr.add(u.getEmail());
//		sarr.add(new Boolean(u.isLocked()).toString());
		return serialize(sarr, "%user%");
	}
	
	/**
	 * deserialize an item
	 * @param str The string to be deserialized
	 * @return deserializing result
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Item deserializeItem(String str) throws ParseException, IOException, ClassNotFoundException {
		if (str==null || str.equals("[]")) return null;
		ArrayList<String> sarr = deserialize(str, new ArrayList<String>(), "%item%");
		ItemStatus status = deserialize(sarr.get(0));
		String name = sarr.get(1);
		String location= sarr.get(2);
		String description= sarr.get(3);
		Date lostDate = deserialize(sarr.get(4), new Date());
		Date foundDate = deserialize(sarr.get(5), new Date());
		Date resolvedDate = deserialize(sarr.get(6), new Date());
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
	
	/**
	 * deserialize a user
	 * @param str The String to be deserialized
	 * @return deserializing result
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User deserializeUser(String str) throws IOException, ClassNotFoundException {
		if (str==null || str.equals("[]")) return null;
		ArrayList<String> sarr = deserialize(str, new ArrayList<String>(), "%user%");
		String name = sarr.get(0);
		String phoneNumber = sarr.get(1);
		String email = sarr.get(2);
//		boolean isLocked = Boolean.valueOf(sarr.get(3));
		boolean isLocked;
		int locknum = Integer.parseInt(data.get(email+"/COUNTER"));
		if (locknum>=3) isLocked = true;
		else isLocked = false;
		return new User(name, phoneNumber, email, isLocked);
	}
	
	/**
	 * get all the information of certain user
	 * @param username the email address of the user
	 * @return An user object that contains all the information of certain user.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User getUser(String username) throws IOException, ClassNotFoundException {
		if (username==null || username.equals("")) return null;
		return deserializeUser(data.get("USER/" + username+"/INFO"));
	}
	
	/**
	 * get the information of current user
	 * @return An user object that contains all the information of current user.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User getCurrentUser() throws IOException, ClassNotFoundException{
		return getUser(currentUsername);
	}
	
	/**
	 * Get user information of all users
	 * @return An arraylist that contains user information.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<User> getUserList() throws IOException, ClassNotFoundException{
		if (data.get("Users")==null) data.put("Users", "");
		ArrayList<String> namearr = deserialize(data.get("Users"), new ArrayList<String>());
		ArrayList<User> ret = new ArrayList<User>();
		for (String eachName : namearr) {
			ret.add(getUser(eachName));
		}
		return ret;
	}
}
