package edu.cmpe275.team13.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the Bean for Patron Object in the system.
 */
@Entity
@Table(name = "patron")
public class Patron implements Serializable {
	
	private static final long serialVersionUID = 4822215297321968747L;
	private String patron_email;
	private String patron_password;
	private String patron_name;
	
	@Id
	private int patron_id;
	private boolean patron_verified;
	private int books_issued;
	
	/**
	 * 
	 */
	public Patron() {
		this.patron_email = null;
		this.patron_password = null;
		this.patron_name = null;
		this.patron_id = Integer.MIN_VALUE;
		this.patron_verified = false;
		this.books_issued = 0;
	}
	
	/**
	 * 
	 * @param patron_email
	 * @param patron_password
	 * @param patron_name
	 * @param patron_id
	 * @param patron_verified
	 * @param books_issued
	 */
	public Patron(String patron_email, String patron_password, String patron_name, 
			int patron_id, boolean patron_verified, int books_issued) {
		super();
		this.patron_email = patron_email;
		this.patron_password = patron_password;
		this.patron_name = patron_name;
		this.patron_id = patron_id;
		this.patron_verified = patron_verified;
		this.books_issued = books_issued;
	}



	/**
	 * @return the patron_email
	 */
	public String getPatron_email() {
		return patron_email;
	}

	/**
	 * @param patron_email the patron_email to set
	 */
	public void setPatron_email(String patron_email) {
		this.patron_email = patron_email;
	}

	/**
	 * @return the patron_name
	 */
	public String getPatron_name() {
		return patron_name;
	}

	/**
	 * @param patron_name the patron_name to set
	 */
	public void setPatron_name(String patron_name) {
		this.patron_name = patron_name;
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
	 * @return the patron_verified
	 */
	public boolean isPatron_verified() {
		return patron_verified;
	}

	/**
	 * @param patron_verified the patron_verified to set
	 */
	public void setPatron_verified(boolean patron_verified) {
		this.patron_verified = patron_verified;
	}

	/**
	 * @return the books_issued
	 */
	public int getBooks_issued() {
		return books_issued;
	}

	/**
	 * @param books_issued the books_issued to set
	 */
	public void setBooks_issued(int books_issued) {
		this.books_issued = books_issued;
	}

	/**
	 * @return the patron_password
	 */
	public String getPatron_password() {
		return patron_password;
	}

	public void setPatron_password(String patron_password) {
		this.patron_password = patron_password;
	}
}
