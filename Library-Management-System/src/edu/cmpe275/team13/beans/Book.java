package edu.cmpe275.team13.beans;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is the bean definition for the Book object.
 */
@Entity
@Table(name = "book")
public class Book {

	@Id
	private Long isbn;
	
	private String author_name;
	private String title;
	private String call_number;
	private String publisher_name;
	private Date year_of_publication;
	private String location_in_library;
	private int number_of_copies;
	private int book_status;
	private String image;
	private int available_copies;
	private int created_by;
	private int updated_by;
	private String keywords;
	
	public Book() {
		this.isbn = -1L;
		this.author_name = null;
		this.title = null;
		this.call_number = null;
		this.publisher_name = null;
		this.year_of_publication = new Date(0);
		this.location_in_library = null;
		this.number_of_copies = 0;
		this.book_status = 0;
		this.image = null;
		this.available_copies = 0;
		this.created_by = 0;
		this.updated_by = 0;
		this.keywords = null;
	}

	/**
	 * public constructor.
	 * @param isbn
	 * @param author_name
	 * @param title
	 * @param call_number
	 * @param publisher_name
	 * @param year_of_publication
	 * @param location_in_library
	 * @param number_of_copies
	 * @param book_status
	 * @param image
	 * @param available_copies
	 * @param created_by
	 * @param updated_by
	 * @param keywords
	 */
	public Book(Long isbn, String author_name, String title, String call_number, 
			String publisher_name, Date year_of_publication, String location_in_library,
			int number_of_copies, int book_status, String image, 
			int available_copies, int created_by, int updated_by,
			String keywords) {
		super();
		this.isbn = isbn;
		this.author_name = author_name;
		this.title = title;
		this.call_number = call_number;
		this.publisher_name = publisher_name;
		this.year_of_publication = year_of_publication;
		this.location_in_library = location_in_library;
		this.number_of_copies = number_of_copies;
		this.book_status = book_status;
		this.image = image;
		this.available_copies = available_copies;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.keywords = keywords;
	}

	/**
	 * @return the isbn
	 */
	public Long getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the author_name
	 */
	public String getAuthor_name() {
		return author_name;
	}

	/**
	 * @param author_name the author_name to set
	 */
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the call_number
	 */
	public String getCall_number() {
		return call_number;
	}

	/**
	 * @param call_number the call_number to set
	 */
	public void setCall_number(String call_number) {
		this.call_number = call_number;
	}

	/**
	 * @return the publisher_name
	 */
	public String getPublisher_name() {
		return publisher_name;
	}

	/**
	 * @param publisher_name the publisher_name to set
	 */
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	/**
	 * @return the year_of_publication
	 */
	public Date getYear_of_publication() {
		return year_of_publication;
	}

	/**
	 * @param year_of_publication the year_of_publication to set
	 */
	public void setYear_of_publication(Date year_of_publication) {
		this.year_of_publication = year_of_publication;
	}

	/**
	 * @return the location_in_library
	 */
	public String getLocation_in_library() {
		return location_in_library;
	}

	/**
	 * @param location_in_library the location_in_library to set
	 */
	public void setLocation_in_library(String location_in_library) {
		this.location_in_library = location_in_library;
	}

	/**
	 * @return the number_of_copies
	 */
	public int getNumber_of_copies() {
		return number_of_copies;
	}

	/**
	 * @param number_of_copies the number_of_copies to set
	 */
	public void setNumber_of_copies(int number_of_copies) {
		this.number_of_copies = number_of_copies;
	}

	/**
	 * @return the book_status
	 */
	public int getBook_status() {
		return book_status;
	}

	/**
	 * @param book_status the book_status to set
	 */
	public void setBook_status(int book_status) {
		this.book_status = book_status;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the available_copies
	 */
	public int getAvailable_copies() {
		return available_copies;
	}

	/**
	 * @param available_copies the available_copies to set
	 */
	public void setAvailable_copies(int available_copies) {
		this.available_copies = available_copies;
	}

	/**
	 * @return the created_by
	 */
	public int getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	/**
	 * @return the updated_by
	 */
	public int getUpdated_by() {
		return updated_by;
	}

	/**
	 * @param updated_by the updated_by to set
	 */
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	/**
	 * 
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}