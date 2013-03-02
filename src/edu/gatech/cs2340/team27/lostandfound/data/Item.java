package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.Date;

/**
 * This class represent an item as well as its status. 
 * @author Yiqi Chen
 *
 */
public class Item {
	 
	private ItemStatus status;
	private String name;
	private String location;
	private String description;
	private Date lostDate;
	private Date foundDate;
	private Date resolvedDate;
	private User loser;
	private User founder;

	/**
	 * Constructor. 
	 * @param status
	 * @param name
	 * @param location
	 * @param description
	 * @param date
	 * @param user
	 */
	public Item(ItemStatus status, String name, String location, String description, Date date, User user) {
		this.status = status;
		this.location = location;
		this.description = description;
		if(status == ItemStatus.LOST) {
			lostDate = date;
			loser = user;
		}
		else {
			foundDate = date;
			founder = user;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getLoca() {
		return location;
	}
	
	public String getDescri() {
		return description;
	}
	
	public ItemStatus getStatus() {
		return status;
	}
	
	public Date getLostDate() {
		return lostDate;
	}
	
	public Date getFoundDate() {
		return foundDate;
	}
	
	public User getLoser() {
		return loser;
	}
	
	public User getFounder() {
		return founder;
	}
	
	public enum ItemStatus{
		LOST,
		FOUND,
		RESOLVED;
	}
}
