package edu.cmpe275.team13.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmpe275.team13.beans.AppSettings;
import edu.cmpe275.team13.beans.Book;
import edu.cmpe275.team13.beans.BookStatus;
import edu.cmpe275.team13.beans.Reservation;
import edu.cmpe275.team13.exceptions.BookNotFoundException;
import edu.cmpe275.team13.exceptions.DeleteBookNotPermitted;
import edu.cmpe275.team13.exceptions.DuplicateBookException;
import edu.cmpe275.team13.search.BookSearch;

/**
 * Implementation of the BookDAO interface.
 */
@Service
public class BookDAOImpl implements BookDAO {

	@Autowired
	private AppSettings appSettings;

	/**
	 * Adds a new book in the system.
	 */
	@Override
	public Long addBook(Book book) {
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Book oldBook = em.find(Book.class, book.getIsbn());
		if(null != oldBook) {
			throw new DuplicateBookException();
		}
		try {
			
			tx.begin();
			em.persist(book);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			Logger log = Logger.getLogger("Adding a book to DB");
			log.log(Level.WARNING, "Rolling Back:", e);
			tx.rollback();
		} finally {
			em.close();
		}
		return book.getIsbn();
	}

	/**
	 * updates the book with given ID.
	 */
	@Override
	public void updateBook(Book book) {
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(book);
			em.getTransaction().commit();
		} catch (Exception e) {
			Logger log = Logger.getLogger("Updating a book to DB");
			log.log(Level.WARNING, "Rolling Back:", e);
			tx.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * returns all books in the system.
	 */
	@Override
	public List<Book> listBooks() {
		return null;
	}

	/**
	 * returns the book with given Id from the system.
	 */
	@Override
	public Book getBookById(Long isbn) {
		System.out.println("Getting book by id: " + isbn);
		EntityManager em = EMF.get().createEntityManager();
		Book book = em.find(Book.class, isbn);
		if (null == book) {
			throw new BookNotFoundException("Book with ISBN " + isbn + " not found!");
		}
		em.close();
		return book;
	}

	/**
	 * deletes an book from the system.
	 */
	@Override
	public void removeBook(Long isbn) {
		EntityManager em = EMF.get().createEntityManager();
		Book book = em.find(Book.class, isbn);
		if (null == book) {
			throw new BookNotFoundException("Book with ISBN " + isbn + " not found in the system!");
		}
		if (book.getNumber_of_copies() != book.getAvailable_copies()) {
			throw new DeleteBookNotPermitted("Book with ISBN " + isbn + " can not be deleted!");
		}
		em.getTransaction().begin();
		em.remove(book);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * searches the book by bookspec in the system
	 */
	@Override
	public List<Book> searchBySpec(BookSearch bookSpec, int patron_id) {
		if (null == bookSpec) {
			return new ArrayList<Book>(0);
		}
		EntityManager em = EMF.get().createEntityManager();
		boolean isbn_set = false, author_name_set = false, title_set = false, publisher_name_set = false,
				year_set = false, updated_by_set = false, created_by_set = false, is = false, keywords_set = false,
				book_status_set = false;
		String queryString = "SELECT e FROM Book e ";
		if (bookSpec.getIsbn() != null) {
			if (!is) {
				queryString += "where e.isbn = :isbn ";
				is = !is;
			} else {
				queryString += " AND e.isbn = :isbn ";
			}
			isbn_set = true;
		}
		if (bookSpec.getAuthor_name() != null && bookSpec.getAuthor_name().length() != 0) {
			if (!is) {
				queryString += "where e.author_name like :author_name";
				is = !is;
			} else {
				queryString += " AND e.author_name like :author_name";
			}
			author_name_set = true;
		}
		if (bookSpec.getTitle() != null && bookSpec.getTitle().length() != 0) {
			if (!is) {
				queryString += "where e.title like :title";
				is = !is;
			} else {
				queryString += " AND e.title like :title";
			}
			title_set = true;
		}
		if (bookSpec.getPublisher_name() != null && bookSpec.getPublisher_name().length() != 0) {
			if (!is) {
				queryString += "where e.publisher_name like :publisher_name";
				is = !is;
			} else {
				queryString += " AND e.publisher_name like :publisher_name";
			}
			publisher_name_set = true;
		}
		if (bookSpec.getYear_of_publication() != null) {
			if (!is) {
				queryString += "where e.year_of_publication = :year";
				is = !is;
			} else {
				queryString += " AND e.year_of_publication = :year";
			}
			year_set = true;
		}
		if(bookSpec.getBook_status() != Integer.MIN_VALUE) {
			if (!is) {
				queryString += "where e.book_status = :book_status";
				is = !is;
			} else {
				queryString += " AND e.book_status = :book_status";
			}
			book_status_set = true;
		}
		if (bookSpec.getUpdated_by() != Integer.MIN_VALUE) {
			if (!is) {
				queryString += "where e.updated_by = :updated_by";
				is = !is;
			} else {
				queryString += " AND e.updated_by = :updated_by";
			}
			updated_by_set = true;
		}
		if (bookSpec.getCreated_by() != Integer.MIN_VALUE) {
			if (!is) {
				queryString += "where e.created_by = :created_by";
				is = !is;
			} else {
				queryString += " AND e.created_by = :created_by";
			}
			created_by_set = true;
		}
		if (bookSpec.getKeywords() != null && bookSpec.getKeywords().length > 0) {
			String kywrdstrng = " e.keywords like :keyword0 ";
			for (int i = 1; i < bookSpec.getKeywords().length - 1; i++) {
				kywrdstrng += " OR e.keywords like :keyword" + i + " ";
			}
			//kywrdstrng += " )";
			if (!is) {
				queryString += "where " + kywrdstrng;
				is = !is;
			} else {
				queryString += " AND " + kywrdstrng;
			}
			keywords_set = true;
		}
		Query query = em.createQuery(queryString);
		System.out.println(queryString);
		if (isbn_set) {
			query.setParameter("isbn", bookSpec.getIsbn());
		}
		if (author_name_set) {
			query.setParameter("author_name", "%" + bookSpec.getAuthor_name() + "%");
		}
		if (title_set) {
			query.setParameter("title", "%" + bookSpec.getTitle() + "%");
		}
		if (publisher_name_set) {
			query.setParameter("publisher_name", "%" + bookSpec.getPublisher_name() + "%");
		}
		if (year_set) {
			query.setParameter("year", bookSpec.getYear_of_publication());
		}
		if(book_status_set) {
			query.setParameter("book_status", bookSpec.getBook_status());
		}
		if (created_by_set) {
			query.setParameter("created_by", bookSpec.getCreated_by());
		}
		if (updated_by_set) {
			query.setParameter("updated_by", bookSpec.getUpdated_by());
		}
		if (keywords_set) {
			String[] keywords = bookSpec.getKeywords();
			for (int i = 0; i < keywords.length - 1; i++) {
				query.setParameter("keyword" + i, "%" + keywords[i + 1] +"%");
			}
		}
		System.out.println(query.toString());
		@SuppressWarnings("unchecked")
		List<Book> list = query.getResultList();
		for (Book book : list) {
			Query query1 = em.createQuery("SELECT e FROM Reservation e WHERE e.id.isbn = :isbn AND e.id.patron_id = :pid AND e.checked_out = FALSE AND e.end_date > :date");
			query1.setParameter("isbn", book.getIsbn());
			query1.setParameter("pid", patron_id);
			query1.setParameter("date", new Timestamp(appSettings.getAppDate().getTime()));
			Reservation  res = null;
			try {
				res = (Reservation) query1.getSingleResult(); 
			} catch(NoResultException e) {
				// DO NOTHING
			}
			if(null != res) {
				book.setBook_status(BookStatus.RESERVED);
			}
		}
		em.close();
		return list;
	}
}
