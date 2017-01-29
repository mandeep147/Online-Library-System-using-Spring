package edu.cmpe275.team13.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cmpe275.team13.beans.Book;
import edu.cmpe275.team13.persistence.BookDAO;
import edu.cmpe275.team13.search.BookSearch;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;
	
	@Transactional
	@Override
	public Long addBook(Book book) {
		return bookDAO.addBook(book);
	}

	@Transactional
	@Override
	public void updateBook(Book book) {
		bookDAO.updateBook(book);
	}

	@Transactional
	@Override
	public List<Book> listBooks() {
		return bookDAO.listBooks();
	}

	@Transactional
	@Override
	public Book getBookById(Long isbn) {
		return bookDAO.getBookById(isbn);
	}

	@Transactional
	@Override
	public void removeBook(Long isbn) {
		bookDAO.removeBook(isbn);
	}

	@Override
	public List<Book> searchBySpec(BookSearch bookSpec, int patron_id) {
		return bookDAO.searchBySpec(bookSpec, patron_id);
	}

}
