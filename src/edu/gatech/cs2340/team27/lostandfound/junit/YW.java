package edu.gatech.cs2340.team27.lostandfound.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.gatech.cs2340.team27.lostandfound.data.Item;
import edu.gatech.cs2340.team27.lostandfound.data.Items;
import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class YW {

	@BeforeClass 
	public static void setUp() throws IOException, ClassNotFoundException, ParseException{
		Communication.debug = true;
		Communication.getInstance().createAccount("EA", "EA", "A", "1", false);
		Communication.getInstance().createAccount("EB", "EB", "B", "2", false);
		Communication.getInstance().createAccount("EC", "EC", "C", "3", false);
		Items.getInstance().addItem(Item.ItemStatus.FOUND, "a", "aa", "aaa", 
				new Date(), new User("A", "1", "EA", false),
				Item.Category.ANIMAL);
		Items.getInstance().addItem(Item.ItemStatus.LOST, "b", "bb", "bbb", 
				new Date(), new User("B", "2", "EB", false),
				Item.Category.CLOTH);
		Items.getInstance().addItem(Item.ItemStatus.RESOLVED, "c", "cc", "ccc", 
				new Date(), new User("C", "3", "EC", false),
				Item.Category.ELECTRONICS);
		System.out.println(Communication.getInstance().getItems());
	}
	@Test
	public void testFilter1() throws IOException, ClassNotFoundException, ParseException {
		assertEquals(Items.getInstance().filter(null, null, null, "a").size(), 1);
	}
	
	@Test
	public void testFilter2() throws IOException, ClassNotFoundException, ParseException {
		assertEquals(Items.getInstance().filter(Item.Category.CLOTH, null, null, null).size(), 1);
	}
	
	@Test
	public void testFilter3() throws IOException, ClassNotFoundException, ParseException {
		Date tmp = new Date();
		tmp.setYear(2100);
		assertTrue(Items.getInstance().filter(null, tmp, null, null).isEmpty());
	}
	
	@Test
	public void testFilter4() throws IOException, ClassNotFoundException, ParseException {
		assertEquals(Items.getInstance().filter(null, null, Item.ItemStatus.LOST, null).size(), 1);
	}
	
	@Test
	public void testFilter5() throws IOException, ClassNotFoundException, ParseException {
		assertEquals(Items.getInstance().filter(null, null, null, null).size(), 3);
		assertEquals(Items.getInstance().filter(Item.Category.ANIMAL, new Date(), Item.ItemStatus.FOUND, "a").size(), 1);
	}
}
