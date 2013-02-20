package edu.gatech.cs2340.team27.lostandfound.model;

import java.util.HashMap;

import org.apache.http.client.*;
import org.apache.http.impl.client.*;

/*
 * Handles communication
 * 
 * @author Yijie Wang
 * @version 0.1
 */
public class Communication {
	private static Communication onlyInstance;

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
	
	private HashMap<String, String> data;
	
	protected Communication(){
		
	}
	protected void finalize(){
		
	}
	
	/*
	 * Status of login attempt.
	 */
	public enum LoginStatus{
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
	public static LoginStatus loginAttempt(String username, String password){
		return LoginStatus.SUCCESS;
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
		return false;
	}
}
