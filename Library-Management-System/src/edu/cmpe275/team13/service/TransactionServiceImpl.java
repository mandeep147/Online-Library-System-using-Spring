package edu.cmpe275.team13.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmpe275.team13.beans.IssueBook;
import edu.cmpe275.team13.beans.Transaction;
import edu.cmpe275.team13.beans.Waitlist;
import edu.cmpe275.team13.exceptions.TransactionLimitExceededException;
import edu.cmpe275.team13.persistence.TransactionDAO;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDAO transacationDAO;

	@Override
	public void performTransaction(Transaction transaction) {
		if (transaction.isCheckout()) {
			if (transaction.getBooks().size() > 5) {
				throw new TransactionLimitExceededException("Maximum 5 books can be checked out at a time!");
			}
			if (transaction.getPatron().getBooks_issued() + transaction.getBooks().size() > 10) {
				throw new TransactionLimitExceededException("Maximum number of books exceeded!");
			}
			System.out.println("Validating");
			int todaysTransaction = this.transacationDAO.getTodaysTransaction(transaction.getPatron().getPatron_id());
			if (todaysTransaction + transaction.getBooks().size() > 5) {
				throw new TransactionLimitExceededException("Only 5 books can be checked out in one day!");
			}
		} else {
			System.out.println("Validating!");
			if (transaction.getBooks().size() > 10) {
				throw new TransactionLimitExceededException("Maximum 10 books can be returned at a time!");
			}
		}
		this.transacationDAO.performTransaction(transaction);
	}

	@Override
	public List<IssueBook> getPendingBooks(int patron_id) {
		return this.transacationDAO.getPendingBooks(patron_id);
	}

	@Override
	public List<Waitlist> getWaitlistedBooks(int patron_id) {
		return this.transacationDAO.getWaitlistedBooks(patron_id);
	}

	@Override
	public void renewBook(Long isbn, int patron) {
		this.transacationDAO.renewBook(isbn, patron);
	}

	@Override
	public void updateReservations() {
		this.transacationDAO.updateReservations();
		
	}

	@Override
	public void updateEmail() {
		this.transacationDAO.updateEmail();
	}

}
