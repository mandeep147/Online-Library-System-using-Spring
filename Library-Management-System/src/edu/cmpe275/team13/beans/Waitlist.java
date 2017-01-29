package edu.cmpe275.team13.beans;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This bean represents bean for waitlist table in the system.
 */
@Entity
@Table(name = "waitlist")
public class Waitlist {
	
	@EmbeddedId
	private WaitlistId id;
	
	private Timestamp join_date;
	
	/**
	 * Default constructor 
	 */
	public Waitlist() {
		this.id = null;
		this.setJoin_date(null);

	}
	
	/**
	 * Parameterized constructor.
	 * @param id
	 * @param ts
	 */
	public Waitlist(WaitlistId id, Timestamp ts) {
		this.id = id;
		this.setJoin_date(ts);
	}

	/**
	 * @return the id
	 */
	public WaitlistId getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(WaitlistId id) {
		this.id = id;
	}

	/**
	 * returns the join date.
	 * @return
	 */
	public Timestamp getJoin_date() {
		return join_date;
	}

	/**
	 * sets the join date.
	 * @param join_date
	 */
	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
}
