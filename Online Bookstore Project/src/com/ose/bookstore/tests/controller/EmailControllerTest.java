package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.ose.bookstore.controller.EmailController;



/**
 * Test class to test EmailController()
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
public class EmailControllerTest {

	/**
	 * Test method
	 */
	@Test
	public void testSendMessage() {
		EmailController mockedemailcontroller = mock(EmailController.class);
		assertNotNull("email null",mockedemailcontroller);
		String mockemail = new String();
		mockedemailcontroller.sendMessage(mockemail);
		verify(mockedemailcontroller).sendMessage(mockemail);
				
		
	}

}
