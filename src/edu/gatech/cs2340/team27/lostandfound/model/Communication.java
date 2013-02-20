package edu.gatech.cs2340.team27.lostandfound.model;

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
}
