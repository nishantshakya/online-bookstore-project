package com.ose.bookstore.model.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ose.bookstore.model.entity.Ratings;
//import javax.ejb.Stateless;
/**
 * Session Bean implementation class RatingsDao to deal with ratings
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Stateless
@LocalBean
public class RatingsDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public RatingsDAO() {
	}

	/**Average book rating of a book
	 * @param bookId current book id
	 * @return the round-off average rating
	 */
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

	/**List of ratings for a book
	 * @param bookId
	 * @return query result list for matching book id
	 */
	public List<Ratings> getBookRating(int bookId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ratings> query = builder.createQuery(Ratings.class);
		Root<Ratings> c = query.from(Ratings.class);
		query.select(c).where(builder.equal(c.get("bookId"), bookId));
		return entityManager.createQuery(query).getResultList();

	}

	/**Sets rating to a book for a specific user
	 * @param ratings out of 5
	 */
	public void setUserRating(Ratings ratings) {
		if (ratings.getBookId() != 0) {
			if (ratings.getRatingsId() > 0) {
				System.out.println("Merging.....");
				entityManager.merge(ratings);
			} else {
				System.out.println("Persisting");
				entityManager.persist(ratings);
			}
		}
	}

	/**User Rating for a book
	 * @param bookId
	 * @param userId
	 * @return user rating of a book
	 */
	public List<Ratings> getUserRating(int bookId, int userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ratings> query = builder.createQuery(Ratings.class);
		Root<Ratings> c = query.from(Ratings.class);
		query.select(c).where(builder.and(builder.equal(c.get("bookId"), bookId),
							builder.equal(c.get("userId"), userId)));
		return entityManager.createQuery(query).getResultList();
	}
}
