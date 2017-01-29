package edu.cmpe275.team13.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * An EmbeddedId field for the Waitlist object.
 */
@Embeddable
public class WaitlistId implements Serializable {
	
	private static final long serialVersionUID = 300376766958883554L;
	
	private Long isbn;
	private int patron_id;
	
	/**
	 * default constructor
	 */
	public WaitlistId() {
		isbn = null;
		patron_id = Integer.MIN_VALUE;
	}

	/**
	 * parameterized constructor for the system.
	 * @param isbn
	 * @param patron_id
	 */
	public WaitlistId(Long isbn, int patron_id) {
		super();
		this.isbn = isbn;
		this.patron_id = patron_id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
		WaitlistId other = (WaitlistId) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (patron_id != other.patron_id)
			return false;
		return true;
	}
}
