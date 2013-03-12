package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.Date;

/**
 * This class represent an item as well as its status. 
 * @author Yiqi Chen
 *
 */
public class Item {
	 
	private ItemStatus status;
	private Category category;
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
	 * @param status the status of the item
	 * @param name the name of the item
	 * @param location the location of the item
	 * @param description the input description
	 * @param date the date of the item
	 * @param user user who adds this item
	 * @param category the category of this item
	 */
	public Item(ItemStatus status, String name, String location, String description, Date date, User user, Category category) {
		this.status = status;
		this.location = location;
		this.description = description;
		this.name = name;
		if(status == ItemStatus.LOST) {
			lostDate = date;
			loser = user;
		}
		else {
			foundDate = date;
			founder = user;
		}
		this.category=category;
	}
	
	/**
	 * Gets the category of this item.
	 * @return the category of this item
	 */
	public Category getCategory(){
		return category;
	}
	
	/**
	 * Getter. 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter. 
	 * @return location
	 */
	public String getLoca() {
		return location;
	}
	
	/**
	 * Getter. 
	 * @return description
	 */
	public String getDescri() {
		return description;
	}
	
	/**
	 * Getter. 
	 * @return status
	 */
	public ItemStatus getStatus() {
		return status;
	}
	
	/**
	 * Getter. 
	 * @return lostDate
	 */
	public Date getLostDate() {
		return lostDate;
	}
	
	/**
	 * Getter. 
	 * @return foundDate
	 */
	public Date getFoundDate() {
		return foundDate;
	}
	
	/**
	 * Getter
	 * @return resolvedDate
	 */
	public Date getResolvedDate() {
		return resolvedDate;
	}
	
	/**
	 * Getter. 
	 * @return loser
	 */
	public User getLoser() {
		return loser;
	}
	
	/**
	 * Getter. 
	 * @return founder
	 */
	public User getFounder() {
		return founder;
	}
	
	/**
	 * Different item status
	 * @author Yiqi Chen
	 *
	 */
	public static enum ItemStatus{
		LOST,
		FOUND,
		RESOLVED;
	}
	/**
	 * Category of the item
	 * @author Yijie Wang
	 *
	 */
	public static enum Category{
		ANIMAL,
		CLOTH,
		ELECTRONICS,
		OTHER;
	}
}
