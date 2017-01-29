package edu.cmpe275.team13.persistence;

import java.util.List;

import edu.cmpe275.team13.beans.Book;
import edu.cmpe275.team13.search.BookSearch;
import edu.cmpe275.team13.search.BookSpecification;

/**
 * an interface to interact with DB for books.
 */
public interface BookDAO {

	/**
	 * adds the book in the system
	 * @param book
	 * @return
	 */
	public Long addBook(Book book);

	/**
	 * updates the book in the system
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * list all the books.
	 * @return
	 */
	public List<Book> listBooks();

	/**
	 * get book by id
	 * @param isbn
	 * @return
	 */
	public Book getBookById(Long isbn);

	/**
	 * delete the book
	 * @param isbn
	 */
	public void removeBook(Long isbn);

	/**
	 * search by BookSpec
	 * @param bookSpec
	 * @param patron_id
	 * @return
	 */
	public List<Book> searchBySpec(BookSearch bookSpec, int patron_id);
	
}
