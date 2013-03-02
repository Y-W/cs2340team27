package edu.gatech.cs2340.team27.lostandfound.model;

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
		ArrayList<String> sarr;
		if (data.get(query) == null) {
			if (data.get("Users")==null) data.put("Users", "");
			String str = data.get("Users");
			if (str.equals("") || str==null) {
				sarr = new ArrayList<String>();
			}
			else {
				sarr = (ArrayList<String>) deserialize(str);
			}
			sarr.add(username);
			data.put("Users", serialize(sarr));
			data.put(query, Long.toString((System.currentTimeMillis())));
			data.put(query + "/COUNTER", "0");
			data.put(query + "/ITEMS", "");
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
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bout);
		out.writeObject(o);
		out.close();
		byte[] strin = bout.toByteArray(); 
        return new String(Base64.encode(strin));
	}

	public Object deserialize(String str) throws IOException, ClassNotFoundException {
		if (str==null) return null;
		if (str.equals("")) return null;
		byte[] restoredBytes = Base64.decode(str); 
  	  ByteArrayInputStream bin = new ByteArrayInputStream(restoredBytes);
       ObjectInputStream in = new ObjectInputStream(bin);
       return in.readObject();
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
		if (item.getFounder()!=null) 
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
			if (eachperson == null) continue;
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

class Base64 {
    static byte[] encodeData;
    static String charSet = 
  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    static {
    	encodeData = new byte[64];
	for (int i = 0; i<64; i++) {
	    byte c = (byte) charSet.charAt(i);
	    encodeData[i] = c;
	}
    }

    private Base64() {}

    /**
     * base-64 encode a string
     * @param s		The ascii string to encode
     * @returns		The base64 encoded result
     */

    public static String
    encode(String s) {
        return encode(s.getBytes());
    }

    /**
     * base-64 encode a byte array
     * @param src	The byte array to encode
     * @returns		The base64 encoded result
     */

    public static String
    encode(byte[] src) {
	return encode(src, 0, src.length);
    }

    /**
     * base-64 encode a byte array
     * @param src	The byte array to encode
     * @param start	The starting index
     * @param len	The number of bytes
     * @returns		The base64 encoded result
     */

    public static String
    encode(byte[] src, int start, int length) {
        byte[] dst = new byte[(length+2)/3 * 4 + length/72];
        int x = 0;
        int dstIndex = 0;
        int state = 0;	// which char in pattern
        int old = 0;	// previous byte
        int len = 0;	// length decoded so far
	int max = length + start;
        for (int srcIndex = start; srcIndex<max; srcIndex++) {
	    x = src[srcIndex];
	    switch (++state) {
	    case 1:
	        dst[dstIndex++] = encodeData[(x>>2) & 0x3f];
		break;
	    case 2:
	        dst[dstIndex++] = encodeData[((old<<4)&0x30) 
	            | ((x>>4)&0xf)];
		break;
	    case 3:
	        dst[dstIndex++] = encodeData[((old<<2)&0x3C) 
	            | ((x>>6)&0x3)];
		dst[dstIndex++] = encodeData[x&0x3F];
		state = 0;
		break;
	    }
	    old = x;
	    if (++len >= 72) {
	    	dst[dstIndex++] = (byte) '\n';
	    	len = 0;
	    }
	}

	/*
	 * now clean up the end bytes
	 */

	switch (state) {
	case 1: dst[dstIndex++] = encodeData[(old<<4) & 0x30];
	   dst[dstIndex++] = (byte) '=';
	   dst[dstIndex++] = (byte) '=';
	   break;
	case 2: dst[dstIndex++] = encodeData[(old<<2) & 0x3c];
	   dst[dstIndex++] = (byte) '=';
	   break;
	}
	return new String(dst);
    }

    /**
     * A Base64 decoder.  This implementation is slow, and 
     * doesn't handle wrapped lines.
     * The output is undefined if there are errors in the input.
     * @param s		a Base64 encoded string
     * @returns		The byte array eith the decoded result
     */

    public static byte[]
    decode(String s) {
      int end = 0;	// end state
      if (s.endsWith("=")) {
	  end++;
      }
      if (s.endsWith("==")) {
	  end++;
      }
      int len = (s.length() + 3)/4 * 3 - end;
      byte[] result = new byte[len];
      int dst = 0;
      try {
	  for(int src = 0; src< s.length(); src++) {
	      int code =  charSet.indexOf(s.charAt(src));
	      if (code == -1) {
	          break;
	      }
	      switch (src%4) {
	      case 0:
	          result[dst] = (byte) (code<<2);
	          break;
	      case 1: 
	          result[dst++] |= (byte) ((code>>4) & 0x3);
	          result[dst] = (byte) (code<<4);
	          break;
	      case 2:
	          result[dst++] |= (byte) ((code>>2) & 0xf);
	          result[dst] = (byte) (code<<6);
	          break;
	      case 3:
	          result[dst++] |= (byte) (code & 0x3f);
	          break;
	      }
	  }
      } catch (ArrayIndexOutOfBoundsException e) {}
      return result;
    }

    /**
     * Test the decoder and encoder.
     * Call as <code>Base64 [string]</code>.
     */

//    public static void
//    main(String[] args) {
//    	System.out.println("encode: " + args[0]  + " -> (" 
//    	    + encode(args[0]) + ")");
//    	System.out.println("decode: " + args[0]  + " -> (" 
//    	    + new String(decode(args[0])) + ")");
//    }
}
