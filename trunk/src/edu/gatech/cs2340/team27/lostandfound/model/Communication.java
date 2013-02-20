package edu.gatech.cs2340.team27.lostandfound.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import android.content.Context;

/*
 * Handles communication
 * 
 * @author Yijie Wang
 * @version 0.1
 */
public class Communication {
	private static Communication onlyInstance;
	private static Context appContext;

	/*
	 * Returns the only instance of Communication. If there's no instance ever created, then create one.
	 * 
	 * @return the only instance of Communication
	 */
	public static Communication getInstance(){
		if(onlyInstance==null){
			onlyInstance=new Communication();
		}
		return onlyInstance;
	}
	
	public static void setAppContext(Context appContext){
		Communication.appContext=appContext;
	}
	
	private HashMap<String, String> data;
	private String currentUsername;
	private String currentPassword;
	private static final String filename="Data";
	private static final String digestMethod="SHA-1";
	private static final int lockTime=3;
	
	@SuppressWarnings("unchecked")
	protected Communication(){
		try {
			FileInputStream in= appContext.openFileInput(filename);
			ObjectInputStream oIn=new ObjectInputStream(in);
			oIn.close();
			in.close();
			data=(HashMap<String, String>) oIn.readObject();
		} catch (Exception e) {
			data=new HashMap<String, String>();
		}
		data.put("USER/test", "0");
		data.put("USER/test/PASSWORD", "23");
		data.put("USER/test/COUNTER", "0");
	}
	protected void finalize() throws IOException{
		FileOutputStream out= appContext.openFileOutput(filename,Context.MODE_PRIVATE);
		ObjectOutputStream oOut=new ObjectOutputStream(out);
		oOut.writeObject(data);
		oOut.close();
		out.close();
	}

	/*
	 * Status of login attempt.
	 */
	public enum LogStatus{
		SUCCESS,
		LOCKED,
		FAILURE;
	}
	
	/*
	 * process login attempt
	 * 
	 * @param username intended user name
	 * @param password intended password
	 * @return the status of login attempt
	 */
	public LogStatus loginAttempt(String username, String password){
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance(digestMethod);
		} catch (NoSuchAlgorithmException e) {
			//impossible to reach here
		}
		md.reset();
		md.digest(password.getBytes());
		String digest=new String(md.digest());
		String query="USER/"+username.replace('/', '_');
		if(data.get(query)==null){
			return LogStatus.FAILURE;
		}
		if(Integer.getInteger(data.get(query+"/COUNTER"))>lockTime){
			return LogStatus.LOCKED;
		}
		if(data.get(query+"/PASSWORD").equals(digest)){
			currentUsername=username;
			currentPassword=password;
			return LogStatus.SUCCESS;
		}
		else{
			data.put(query+"/COUNTER", 
					Integer.valueOf(Integer.getInteger(data.get(query+"/COUNTER")).intValue()+1).toString()
					);
			return LogStatus.FAILURE;
		}
	}
	
	/*
	 * create an account
	 * 
	 * @param username	desired user name
	 * @param password	desired password
	 * @param priviliged	true if want to create an administrator, false if want to create a normal user
	 * @return	true if success, false otherwise
	 */
	public boolean createAccount(String username, String password, boolean priviliged){
		String query="USER/"+username.replace('/', '_');
		if(data.get(query)==null){
			data.put(query,Long.toString((System.currentTimeMillis())));
			data.put(query+"/COUNTER", "0");
			MessageDigest md=null;
			try {
				md = MessageDigest.getInstance(digestMethod);
			} catch (NoSuchAlgorithmException e) {
				//impossible to reach here
			}
			md.reset();
			md.digest(password.getBytes());
			String digest=new String(md.digest());
			data.put(query+"/PASSWORD", digest);
			return true;
		}
		return false;
	}
}
