package edu.cmpe275.team13.persistence;

import java.util.List;

import edu.cmpe275.team13.beans.IssueBook;
import edu.cmpe275.team13.beans.Transaction;
import edu.cmpe275.team13.beans.Waitlist;

/**
 * Interface to interact with DB for all checkout, return, renew transactions.
 */
public interface TransactionDAO {
	/**
	 * perform transaction
	 * @param transaction
	 */
	public void performTransaction(Transaction transaction);
	
	/**
	 * returns todays transactions.
	 * @param patron_id
	 * @return
	 */
	public int getTodaysTransaction(int patron_id);
	
	/**
	 * get books which are not returned.
	 * @param patron_id
	 * @return
	 */
	public List<IssueBook> getPendingBooks(int patron_id);
	
	/**
	 * get books which are waitlisted
	 * @param patron_id
	 * @return
	 */
	public List<Waitlist> getWaitlistedBooks(int patron_id);
	
	/**
	 * renew a book
	 * @param isbn
	 * @param patron
	 */
	public void renewBook(Long isbn, int patron);
	
	/**
	 * updates the reservation. 
	 */
	public void updateReservations();
	
	/**
	 * send the mail when book is due.
	 */
	public void updateEmail();
}
