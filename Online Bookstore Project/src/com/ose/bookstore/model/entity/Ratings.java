package com.ose.bookstore.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**Entity implementation class for Entity: Ratings
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Entity

public class Ratings implements Serializable {

	
	private static final long serialVersionUID = 1L;

	//fields
	
	@Id
	@GeneratedValue
	private int ratingsId;
	
	private int bookId;
	
	private int userId;
	
	private int userRating5;
	
	@Column(length = 3000)
	private String comments;

	//Getters and Setters
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserRating5() {
		return userRating5;
	}

	public void setUserRating5(int userRating5) {
		this.userRating5 = userRating5;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRatingsId() {
		return ratingsId;
	}

	public void setRatingsId(int ratingsId) {
		this.ratingsId = ratingsId;
	}
	
}
