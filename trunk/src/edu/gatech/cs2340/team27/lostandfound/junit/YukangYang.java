package edu.gatech.cs2340.team27.lostandfound.junit;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team27.lostandfound.data.User;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class YukangYang {

	public Communication get() throws IOException, ClassNotFoundException {
		return Communication.getInstance();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void userSerializationTest() throws IOException,
			ClassNotFoundException {
		Communication.debug = true;
		assertEquals("[]", get().serializeUser(null));
		User a = new User("name", "111", "email", false);
		assertEquals("name%user%111%user%email%user%", get().serializeUser(a));
	}

}
