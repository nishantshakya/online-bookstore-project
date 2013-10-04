package com.ose.bookstore.tests.controller;

import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import com.ose.bookstore.controller.OrderController;
import com.ose.bookstore.dto.Cart;
import com.ose.bookstore.model.ejb.OrderDAO;
import com.ose.bookstore.model.ejb.UserAccountDAO;
import com.ose.bookstore.model.entity.OrderDetail;
import com.ose.bookstore.model.entity.ShippingType;
import com.ose.bookstore.model.entity.UserDetails;

/**
 * Test class that tests OrderController()
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@RunWith(Arquillian.class)
public class OrderControllerTest {

	
	/**
	 * Method that creates archive for micro deployment
	 * @return ShrinkWrap archive
	 */
	@Deployment(testable=false)
	public static WebArchive createArchiveAndDeploy()
	{
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(UserAccountDAO.class,UserDetails.class,ShippingType.class,OrderDetail.class,OrderDAO.class)
				.addAsManifestResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
		                new StringAsset("<faces-config version=\"2.0\"/>"),
		                "faces-config.xml");
	}
	
	/**
	 * Test method	
	 */
	@Test
	public void testAction() {
		UserDetails mockeduserdetail = mock(UserDetails.class);
		List<Cart> mockedcartlist = mock(List.class);
		OrderDetail mockedorder = mock(OrderDetail.class);
		
		OrderController mockedordercontroller = mock(OrderController.class);
		
		String shippingType="STANDARD";
		
		String res = mockedordercontroller.action(mockeduserdetail, shippingType, mockedcartlist);
		
		verify(mockedordercontroller).action(mockeduserdetail, shippingType, mockedcartlist);		
		
		
	}

}
