package com.ose.bookstore.model.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ose.bookstore.model.entity.Ratings;

/**
 * Session Bean implementation class RatingsDao
 */
@Stateful
@LocalBean
public class RatingsDao {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	private EntityManager entityManager;

	public RatingsDao() {
		// TODO Auto-generated constructor stub
	}

	public int bookRating(int bookId) {
		List<Ratings> ratings = getBookRating(bookId);
		int sumRating = 0;
		if (ratings.isEmpty()) {
			return 0;
		} else {
			for (int i = 0; i < ratings.size(); i++) {
				sumRating += ratings.get(i).getUserRating5();
			}
		}
		return sumRating / ratings.size();
	}

	public List<Ratings> getBookRating(int bookId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ratings> query = builder.createQuery(Ratings.class);
		Root<Ratings> c = query.from(Ratings.class);
		query.select(c).where(builder.equal(c.get("bookId"), bookId));
		return entityManager.createQuery(query).getResultList();

	}

	public void setUserRating(Ratings ratings) {

		if (ratings.getBookId() != 0) {

			if (ratings.getRatingsId() > 0) {
				System.out.println("Merging.....");

				entityManager.merge(ratings);
			} else {
				System.out.println("Persisting");
				System.out.println(ratings.getBookId());
				System.out.println(ratings.getRatingsId());
				System.out.println(ratings.getUserRating5());
				entityManager.persist(ratings);
			}
		}

	}

	public List<Ratings> getUserRating(int bookId, int userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ratings> query = builder.createQuery(Ratings.class);
		Root<Ratings> c = query.from(Ratings.class);
		query.select(c).where(
				builder.and(builder.equal(c.get("bookId"), bookId),
						builder.equal(c.get("userId"), userId)));
		return entityManager.createQuery(query).getResultList();
	}
}
