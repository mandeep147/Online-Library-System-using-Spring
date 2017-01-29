package edu.cmpe275.team13.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Embeddable;

/**
 * This class represents the EmbeddedId for the class Reservation.
 */
@Embeddable
public class ReservationId implements Serializable {

	private static final long serialVersionUID = -6680359815685418121L;
	
	private Long isbn;
	private int patron_id;
	private Timestamp start_date;
	
	/**
	 * default constructor for the system.
	 */
	public ReservationId() {
		isbn = null;
		patron_id = Integer.MIN_VALUE;
		start_date = null;
	}

	/**
	 * parameterized constructor for ReservationId
	 * @param isbn
	 * @param patron_id
	 * @param start_date
	 */
	public ReservationId(Long isbn, int patron_id, Timestamp start_date) {
		super();
		this.isbn = isbn;
		this.patron_id = patron_id;
		this.start_date = start_date;
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
	 * @return the start_date
	 */
	public Timestamp getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
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
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
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
		ReservationId other = (ReservationId) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (patron_id != other.patron_id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}
}
