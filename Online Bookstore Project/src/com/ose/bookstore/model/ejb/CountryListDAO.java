package com.ose.bookstore.model.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ose.bookstore.model.entity.CountryList;

/**
 * Generates country list to be displayed in country drop down menu
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */

@Stateless
@LocalBean
public class CountryListDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**Gets country list from country table
	 * @return
	 */
	public List<CountryList> getCountry(){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CountryList> query = cb.createQuery(CountryList.class);
		Root<CountryList> c = query.from(CountryList.class);
		query.select(c);
		return entityManager.createQuery(query).getResultList();
	}
}
