package edu.gatech.cs2340.team27.lostandfound.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class YW {

	@Before
	public void setUp(){
		Communication.debug = true;
	}
	@Test
	public void testFilter1() throws IOException, ClassNotFoundException, ParseException {
		Items.getInstance().addItem(Item.ItemStatus.FOUND, "a", "aa", "aaa", 
				new Date(), new User("A", "1", "EA", false),
				Item.Category.ANIMAL);
		assertTrue(Items.getInstance().filter(null, null, null, "b").isEmpty());
		assertTrue(Items.getInstance().filter(Item.Category.CLOTH, null, null, null).isEmpty());
		Date tmp = new Date();
		tmp.setYear(2000);
		assertTrue(Items.getInstance().filter(null, tmp, null, null).isEmpty());
		assertTrue(Items.getInstance().filter(null, null, Item.ItemStatus.LOST, null).isEmpty());
		System.out.println(Communication.getInstance().getItems());
		assertFalse(Items.getInstance().filter(null, null, null, null).isEmpty());
		assertFalse(Items.getInstance().filter(Item.Category.ANIMAL, new Date(), Item.ItemStatus.FOUND, "a").isEmpty());
	}

}
