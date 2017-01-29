package edu.cmpe275.team13.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.persistence.Embeddable;

/**
 * An embeddedId for the class IssueBook.
 */
@Embeddable
public class IssueBookID implements Serializable {
	
	private static final long serialVersionUID = 322696952500433829L;
	
	@Column(name = "isbn")
	private Long isbn;
	
	@Column(name = "patron_id")
	private int patron_id;
	
	@Column(name = "issue_date")
	private Timestamp issue_date;
	
	public IssueBookID() {
		
	}
	
	public IssueBookID(Long isbn, int patron_id, Timestamp issue_date) {
		super();
		this.isbn = isbn;
		this.patron_id = patron_id;
		this.issue_date = issue_date;
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
	 * @return the patron_id
	 */
	public int getPatron_id() {
		return patron_id;
	}
	/**
	 * @param patron_id the patron_id to set
	 */
	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}
	/**
	 * @return the issue_date
	 */
	public Timestamp getIssue_date() {
		return issue_date;
	}
	/**
	 * @param issue_date the issue_date to set
	 */
	public void setIssue_date(Timestamp issue_date) {
		this.issue_date = issue_date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((issue_date == null) ? 0 : issue_date.hashCode());
		result = prime * result + patron_id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueBookID other = (IssueBookID) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (issue_date == null) {
			if (other.issue_date != null)
				return false;
		} else if (!issue_date.equals(other.issue_date))
			return false;
		if (patron_id != other.patron_id)
			return false;
		return true;
	}
}
