package edu.cmpe275.team13.service;

import java.util.List;

import edu.cmpe275.team13.beans.IssueBook;
import edu.cmpe275.team13.beans.Transaction;
import edu.cmpe275.team13.beans.Waitlist;

public interface TransactionService {
	
	public void performTransaction(Transaction transaction);
	public List<IssueBook> getPendingBooks(int patron_id);
	public List<Waitlist> getWaitlistedBooks(int patron_id);
	public void renewBook(Long isbn, int patron);
	public void updateReservations();
	public void updateEmail();
}
