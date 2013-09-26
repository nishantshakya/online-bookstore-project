package com.ose.bookstore.model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ose.bookstore.model.entity.Login;

/**
 * Session Bean implementation class LoginDao
 */
@Stateless
@LocalBean
public class LoginDao {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager entityManager;
	
    public LoginDao() {
        // TODO Auto-generated constructor stub
    }
    
    public void createAccount(Login login){
    	entityManager.persist(login);
    }

}
