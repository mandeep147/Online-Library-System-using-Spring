package edu.cmpe275.team13.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the Bean for Librarian object in the system.
 */
@Entity
@Table(name = "librarian")
public class Librarian implements Serializable {
	
	private static final long serialVersionUID = -3748073777433741113L;
	
	@Id
	private int librarian_id;
	
	private String librarian_email;
	private String librarian_password;
	private String librarian_name;
	private boolean librarian_verified;
	
	/**
	 * 
	 */
	public Librarian() {
		this.librarian_email = null;
		this.librarian_password = null;
		this.librarian_name = null;
		this.librarian_id = Integer.MIN_VALUE;
		this.librarian_verified = false;
	}

	/**
	 * 
	 * @param librarian_email
	 * @param librarian_password
	 * @param librarian_name
	 * @param librarian_id
	 * @param librarian_verified
	 */
	public Librarian(String librarian_email, String librarian_password, 
			String librarian_name, int librarian_id, boolean librarian_verified) {
		super();
		this.librarian_email = librarian_email;
		this.librarian_password = librarian_password;
		this.librarian_name = librarian_name;
		this.librarian_id = librarian_id;
		this.librarian_verified = librarian_verified;
	}

	/**
	 * @return the librarian_email
	 */
	public String getLibrarian_email() {
		return librarian_email;
	}

	/**
	 * @param librarian_email the librarian_email to set
	 */
	public void setLibrarian_email(String librarian_email) {
		this.librarian_email = librarian_email;
	}

	/**
	 * @return the librarian_password
	 */
	public String getLibrarian_password() {
		return librarian_password;
	}

	/**
	 * @param librarian_password the librarian_password to set
	 */
	public void setLibrarian_password(String librarian_password) {
		this.librarian_password = librarian_password;
	}

	/**
	 * @return the librarian_name
	 */
	public String getLibrarian_name() {
		return librarian_name;
	}

	/**
	 * @param librarian_name the librarian_name to set
	 */
	public void setLibrarian_name(String librarian_name) {
		this.librarian_name = librarian_name;
	}

	/**
	 * @return the librarian_id
	 */
	public int getLibrarian_id() {
		return librarian_id;
	}

	/**
	 * @param librarian_id the librarian_id to set
	 */
	public void setLibrarian_id(int librarian_id) {
		this.librarian_id = librarian_id;
	}

	/**
	 * @return the librarian_verified
	 */
	public boolean isLibrarian_verified() {
		return librarian_verified;
	}

	/**
	 * @param librarian_verified the librarian_verified to set
	 */
	public void setLibrarian_verified(boolean librarian_verified) {
		this.librarian_verified = librarian_verified;
	}
}
