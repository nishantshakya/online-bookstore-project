package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import com.ose.bookstore.controller.OrderController;
import com.ose.bookstore.helper.Cart;
import com.ose.bookstore.model.ejb.BookListDao;
import com.ose.bookstore.model.ejb.OrderDao;
import com.ose.bookstore.model.ejb.UserAccountDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.OrderDetail;
import com.ose.bookstore.model.entity.ShippingType;
import com.ose.bookstore.model.entity.UserDetails;

@RunWith(Arquillian.class)
public class OrderControllerTest {

	
	@Deployment
	public static JavaArchive createArchiveAndDeploy()
	{
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(UserAccountDao.class,UserDetails.class,ShippingType.class,OrderDetail.class,OrderDao.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}
	
	@Test
	public void testAction() {
		UserDetails mockeduserdetail = mock(UserDetails.class);
		List<Cart> mockedcartlist = mock(List.class);
		OrderDetail mockedorder = mock(OrderDetail.class);
		UserAccountDao mockeduseraccountdao = mock(UserAccountDao.class);
		OrderController oc = new OrderController();
		String shippingType="STANDARD";
		oc.action(mockeduserdetail, shippingType, mockedcartlist);
		verify(mockeduserdetail).getUserId();
	}

}
