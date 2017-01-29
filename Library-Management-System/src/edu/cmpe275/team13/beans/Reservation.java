package edu.cmpe275.team13.beans;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents the Bean for Reservation object in the system.
 */
@Entity
@Table(name = "reservation")
public class Reservation {
	
	@EmbeddedId
	private ReservationId id;
	
	private Timestamp end_date;
	private boolean checked_out;
	
	public Reservation() {
		id = null;
		end_date = null;
		checked_out = false;
	}

	public Reservation(ReservationId id, Timestamp end_date, boolean checked_out) {
		super();
		this.id = id;
		this.end_date = end_date;
		this.checked_out = checked_out;
	}

	/**
	 * @return the id
	 */
	public ReservationId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ReservationId id) {
		this.id = id;
	}

	/**
	 * @return the end_date
	 */
	public Timestamp getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the checked_out
	 */
	public boolean isChecked_out() {
		return checked_out;
	}

	/**
	 * @param checked_out the checked_out to set
	 */
	public void setChecked_out(boolean checked_out) {
		this.checked_out = checked_out;
	}
}
