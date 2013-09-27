package com.ose.bookstore.tests.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ose.bookstore.model.ejb.BookListDao;
import com.ose.bookstore.model.entity.Books;

@RunWith(Arquillian.class)
public class BookListDaoTest {
	
	@Inject
	BookListDao bookListDao;
	
	@Inject
	Books book;
	
	@Deployment
	public static JavaArchive createArchiveAndDeploy()
	{
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(BookListDao.class,Books.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}
	
	
	

	@Test
	public void test() {
		
		//if(bookListDao.getBookList().isEmpty())
		//	System.out.println("booklist empty");
		//System.out.println("booklist size = "+bookListDao.getBookList().size());
		//Assert.assertNull("booklist empty",bookListDao.getBookList());
		//assertEquals(13,bookListDao.getBookList().size());
		
		assertEquals("First",bookListDao.getBook(1).getEdition());
		
		book = bookListDao.getBook(1);
		assertEquals("John Duckett",book.getAuthor());
	}

}
