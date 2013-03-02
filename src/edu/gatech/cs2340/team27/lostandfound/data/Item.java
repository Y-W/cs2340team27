package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.Date;

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
	
	public enum ItemStatus{
		LOST,
		FOUND,
		RESOLVED;
	}
}
