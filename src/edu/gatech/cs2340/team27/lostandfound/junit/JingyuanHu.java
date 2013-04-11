package edu.gatech.cs2340.team27.lostandfound.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class JingyuanHu {

	@Before
	public void setUp() throws Exception {
		Communication.debug = true;
	}

	@Test
	public void test1() throws IOException, ClassNotFoundException,
			ParseException {
		Item a = new Item(ItemStatus.FOUND, "APPLE", "COC", "An Apple",
				new Date(), new User("name", "123", "emaila", false),
				Item.Category.OTHER);
		Item b = new Item(ItemStatus.LOST, "APPLE", "COC", "apple", new Date(),
				new User("nameb", "1111", "emailb", false), Item.Category.OTHER);
		assertTrue(Items.getInstance().match(a, b));

	}
	@Test
	public void test2() throws IOException, ClassNotFoundException,
			ParseException {
		Item a = new Item(ItemStatus.FOUND, "APPLE", "COC", "An Apple",
				new Date(), new User("name", "123", "emaila", false),
				Item.Category.OTHER);
		Item b = new Item(ItemStatus.LOST, "apple", "COC", "apple", new Date(),
				new User("nameb", "1111", "emailb", false), Item.Category.OTHER);
		assertTrue(Items.getInstance().match(a, b));

	}
	@Test
	public void test3() throws IOException, ClassNotFoundException,
			ParseException {
		Item a = new Item(ItemStatus.FOUND, "APPLE", "COC", "An Apple",
				new Date(), new User("name", "123", "emaila", false),
				Item.Category.OTHER);
		Item c = new Item(ItemStatus.LOST, "Banana", "GT", "A banana",
				new Date(), new User("namec", "1234", "emailc", false),
				Item.Category.OTHER);
		assertFalse(Items.getInstance().match(a, c));

	}
	@Test
	public void test4() throws IOException, ClassNotFoundException,
			ParseException {
		Item a = new Item(ItemStatus.FOUND, "APPLE", "COC", "An Apple",
				new Date(), new User("name", "123", "emaila", false),
				Item.Category.OTHER);
		Item c = new Item(ItemStatus.LOST, "Apple", "GT", "A banana",
				new Date(), new User("namec", "1234", "emailc", false),
				Item.Category.ELECTRONICS);
		assertTrue(Items.getInstance().match(a, c));

	}
	
	@Test
	public void test5() throws IOException, ClassNotFoundException,
			ParseException {
		Item a = new Item(ItemStatus.FOUND, "APPLE", "COC", "An Apple",
				new Date(), new User("name", "123", "emaila", false),
				Item.Category.OTHER);
		Item c = new Item(ItemStatus.LOST, "aPplE", "GT", "A banana",
				new Date(), new User("namec", "1234", "emailc", false),
				Item.Category.OTHER);
		assertTrue(Items.getInstance().match(a, c));

	}

}
