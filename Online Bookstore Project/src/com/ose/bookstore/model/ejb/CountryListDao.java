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

@Stateless
@LocalBean
public class CountryListDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<CountryList> getCountry(){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CountryList> query = cb.createQuery(CountryList.class);
		Root<CountryList> c = query.from(CountryList.class);
		query.select(c);
		return entityManager.createQuery(query).getResultList();
	}
}
