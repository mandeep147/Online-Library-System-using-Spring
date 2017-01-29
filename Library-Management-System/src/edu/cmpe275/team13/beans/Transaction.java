package edu.cmpe275.team13.beans;

import java.sql.Date;
import java.util.List;

/**
 * This class represents a bean for either checkout or return transaction. 
 */
public class Transaction {
	
	private List<Book> books;
	
	private Patron patron;
	
	private Date transaction_date;
	
	/**
	 * if is checkout is false, then it represents that the transaction is return transaction.
	 */
	private boolean isCheckout = true;
	
	public Transaction() {
		this.books = null;
		patron = null;
		transaction_date = new Date(0);
		this.setCheckout(true);
	}

	/**
	 * public constructor for Transaction.
	 * @param books
	 * @param patron
	 * @param transaction_date
	 * @param isCheckout
	 */
	public Transaction(List<Book> books, Patron patron, Date transaction_date, boolean isCheckout) {
		super();
		this.books = books;
		this.patron = patron;
		this.transaction_date = transaction_date;
		this.setCheckout(isCheckout);
	}

	/**
	 * @return the transactions
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/**
	 * @return the patron_id
	 */
	public Patron getPatron() {
		return patron;
	}

	/**
	 * @param patron_id the patron_id to set
	 */
	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	/**
	 * @return the transaction_date
	 */
	public Date getTransaction_date() {
		return transaction_date;
	}

	/**
	 * @param transaction_date the transaction_date to set
	 */
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCheckout() {
		return isCheckout;
	}

	/**
	 * 
	 * @param isCheckout
	 */
	public void setCheckout(boolean isCheckout) {
		this.isCheckout = isCheckout;
	}
}
