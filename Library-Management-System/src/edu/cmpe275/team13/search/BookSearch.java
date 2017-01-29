package edu.cmpe275.team13.search;

import java.sql.Date;
import java.util.List;

import edu.cmpe275.team13.beans.BookStatus;

/**
 * Bean used to search the book by specification.
 */
public class BookSearch {
	private String title;
	private String author_name;
	private String publisher_name;
	private Long isbn;
	private Date year_of_publication;
	private int book_status;
	private int created_by;
	private int updated_by;
	private String[] keywords;
	
	public BookSearch() {
		this.isbn = -1L;
		this.author_name = null;
		this.title = null;
		this.publisher_name = null;
		this.year_of_publication = new Date(0);
		this.book_status = BookStatus.AVAILABLE;
		this.created_by = 0;
		this.updated_by = 0;
		this.setKeywords(null);
	}
	
	public BookSearch(String title, String author_name, String publisher_name, 
			Long isbn, Date year_of_publication, int book_status, int created_by, 
			int updated_by, String[] keywords) {
		super();
		this.title = title;
		this.author_name = author_name;
		this.publisher_name = publisher_name;
		this.isbn = isbn;
		this.year_of_publication = year_of_publication;
		this.book_status = book_status;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.setKeywords(keywords);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookSearch [title=" + title + ", author_name=" + author_name + ", publisher_name=" + publisher_name
				+ ", isbn=" + isbn + ", year_of_publication=" + year_of_publication + ", book_status=" + book_status
				+ ", created_by=" + created_by + ", updated_by=" + updated_by + "]";
	}

	/**
	 * 
	 * @return
	 */
	public String[] getKeywords() {
		return keywords;
	}

	/**
	 * 
	 * @param keywords
	 */
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
}
