package edu.gatech.cs2340.team27.lostandfound.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Item.Category;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class YiqiChen {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddItem() throws IOException, ClassNotFoundException, ParseException {
		Communication.debug = true;
		User test1user = new User("test1username", "test1phone", "test1email", false);
		User test2user = new User("test2username", "test2phone", "test2email", false);
		Communication.getInstance().createAccount(test1user.getEmail(), "test1ps",
				test1user.getName(), test1user.getPhoneNumber(), false);
		Communication.getInstance().createAccount(test2user.getEmail(), "test2ps",
				test2user.getName(), test2user.getPhoneNumber(), false);
		Items.getInstance().addItem(ItemStatus.LOST, "test1", "test1location",
			"test1description", new Date(), test1user, Category.ANIMAL);
		Items.getInstance().addItem(ItemStatus.LOST, "test2", "test2location",
			"test2description", new Date(), test2user, Category.ANIMAL);
		ArrayList<Item> list = Items.getInstance().getList();
		assertEquals(2, list.size());
		boolean test3 = false;
		boolean test4 = false;
		for(Item item : list) {
			if(item.equals(new Item(ItemStatus.LOST, "test1", "test1location",
			"test1description", new Date(), test1user, Category.ANIMAL))) {
				test3 = true;
			}
			if(item.equals(new Item(ItemStatus.FOUND, "test4", "test4location",
			"test4description", new Date(), test1user, Category.ANIMAL))) {
				test3 = true;
			}
			
		}
		assertTrue(test3);
		assertFalse(test4);
	}

}
