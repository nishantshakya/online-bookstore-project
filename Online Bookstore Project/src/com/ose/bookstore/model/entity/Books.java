package com.ose.bookstore.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**Entity implementation class for Entity: Books
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Entity
public class Books implements Serializable {

	
	private static final long serialVersionUID = 1L;

	//Fields
	@Id
	@GeneratedValue
	private int bookId;
	
//	@OneToMany(mappedBy = "books")
//	private Collection<ShoppingCart> shoppingcarts;
	@Column(length = 75)
	private String title;
	
	@Column(length = 100)
	private String author;
	
	private Double price;
	
	@Column(length = 75)
	private String isbn;
	
	@Column(length = 20)
	private String edition;
	
//	@Temporal(TemporalType.DATE)
	private String pubDate;
	
	@Column(length = 30)
	private String publisher;
	
	@Column(length=2000)
	private String description;
	
	private float discount;
	
	@Column(length = 75)
	private String coverPage;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "Tag")
	@Column(length = 15)
	private List<String> tags = new ArrayList<String>();
	
	//Getters and Setters
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getCoverPage() {
		return coverPage;
	}
	public void setCoverPage(String coverPage) {
		this.coverPage = coverPage;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}


   
}
