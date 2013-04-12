package edu.gatech.cs2340.team27.lostandfound.junit;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.team27.lostandfound.model.Communication;
import edu.gatech.cs2340.team27.lostandfound.model.Communication.LogStatus;

public class YukangYang {

	public Communication get() throws IOException, ClassNotFoundException {
		return Communication.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		Communication.debug = true;
		Communication.getInstance().createAccount("EA", "EA", "A", "1", false);
		Communication.getInstance().createAccount("EB", "EB", "B", "2", false);
	}

	@Test
	public void loginAttemptTest1() throws IOException, ClassNotFoundException {
		//user doesn't exist
		assertEquals(LogStatus.FAILURE, get().loginAttempt("haha", "111"));
	}
	
	@Test
	public void loginAttemptTest2() throws IOException, ClassNotFoundException {
		//user is locked
		get().loginAttempt("EA", "111");
		get().loginAttempt("EA", "111");
		get().loginAttempt("EA", "111");
		assertEquals(LogStatus.LOCKED, get().loginAttempt("EA", "EA"));
	}
	
	@Test
	public void loginAttemptTest3() throws IOException, ClassNotFoundException {
		//login success
		assertEquals(LogStatus.SUCCESS, get().loginAttempt("EB", "EB"));
	}
	
	@Test
	public void loginAttemptTest4() throws IOException, ClassNotFoundException {
		//wrong password
		assertEquals(LogStatus.FAILURE, get().loginAttempt("EB", "111"));
	}
}
