package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.Date;

public class Item {
	 
	private ItemStatus status;
	private String name;
	private String location;
	private Date lostDate;
	private Date foundDate;
	private Date resolvedDate;
	private User loser;
	private User founder;
	
	public enum ItemStatus{
		LOST,
		FOUND,
		RESOLVED;
	}
}
