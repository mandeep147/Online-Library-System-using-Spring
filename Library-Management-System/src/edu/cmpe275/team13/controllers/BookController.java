package edu.cmpe275.team13.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.team13.beans.AppSettings;
import edu.cmpe275.team13.beans.Book;
import edu.cmpe275.team13.beans.Librarian;
import edu.cmpe275.team13.exceptions.BookNotFoundException;
import edu.cmpe275.team13.exceptions.UnauthorizedAccessException;
import edu.cmpe275.team13.search.BookSearch;
import edu.cmpe275.team13.service.BookService;
import edu.cmpe275.team13.service.TransactionService;

/**
 * This controller is for handling transactions related to book.
 */
@Controller
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@Autowired
	private TransactionService trservice;
	
	@Autowired
	private AppSettings appSettings;

	/**
	 * @return the bookservice
	 */
	public BookService getBookservice() {
		return bookservice;
	}

	/**
	 * @param bookservice
	 *            the bookservice to set
	 */
	public void setBookservice(BookService bookservice) {
		this.bookservice = bookservice;
	}

	/**
	 * returns the book by id.
	 * @param isbn
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public String getBook(@PathVariable long isbn, Model model, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		Book book = bookservice.getBookById(isbn);
		model.addAttribute("book", book);
		model.addAttribute("date", appSettings.getAppDate());
		return "book/showbook";
	}

	/**
	 * get book by id for librarian.
	 * @param isbn
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "librarian/{isbn}", method = RequestMethod.GET)
	public String getBookLibrarian(@PathVariable long isbn, Model model, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		if(!session.getAttribute("type").equals("librarian")) {
			throw new UnauthorizedAccessException();
		}
		Book book = bookservice.getBookById(isbn);
		model.addAttribute("book", book);
		model.addAttribute("date", appSettings.getAppDate());
		return "book/updatebook";
	}

	/**
	 * Delete book by id. 
	 * @param isbn
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable Long isbn, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		if(!session.getAttribute("type").equals("librarian")) {
			throw new UnauthorizedAccessException();
		}
		this.bookservice.removeBook(isbn);
		return "redirect:/index.jsp";
	}
	
	/**
	 * add the book to cart 
	 * @param isbn
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addtocart/{isbn}", method = RequestMethod.GET) 
	public ResponseEntity<Void> addToCart(@PathVariable Long isbn, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		List<Long> book_list = (List<Long>) session.getAttribute("book_list");
		if(null == book_list) {
			book_list = new ArrayList<Long>(0);
		}
		book_list.add(isbn);
		System.out.println("Size of the book_list" + book_list.size());
		session.setAttribute("book_list", book_list);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * update the book.
	 * @param isbn
	 * @param book
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/{isbn}", method = RequestMethod.POST)
	public String updateBook(@PathVariable Long isbn, @ModelAttribute Book book, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		if(!session.getAttribute("type").equals("librarian")) {
			throw new UnauthorizedAccessException();
		}
		Book book_db = this.bookservice.getBookById(isbn);
		if (null == book_db) {
			throw new BookNotFoundException();
		}
		book_db.setAuthor_name(book.getAuthor_name());
		book_db.setTitle(book.getTitle());
		book_db.setCall_number(book.getCall_number());
		book_db.setPublisher_name(book.getPublisher_name());
		book_db.setYear_of_publication(book.getYear_of_publication());
		book_db.setLocation_in_library(book.getLocation_in_library());
		book_db.setNumber_of_copies(book.getNumber_of_copies());
		book_db.setImage(book.getImage());
		book_db.setKeywords(book.getKeywords());
		book_db.setUpdated_by((int) session.getAttribute("user_id"));
		this.bookservice.updateBook(book_db);
		return "redirect:/books/search";
	}

	/**
	 * list all books.
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String listBooks(HttpSession session, Model model) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		if(!session.getAttribute("type").equals("librarian")) {
			throw new UnauthorizedAccessException();
		}
		model.addAttribute("date", appSettings.getAppDate());
		return "book/createbook";
	}

	/**
	 * search book 
	 * @param map
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/searchbook", method = RequestMethod.GET)
	public String search(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		int created_by = (map.get("created_by") == null || map.get("created_by").trim().length() == 0) ? Integer.MIN_VALUE : Integer.parseInt(map.get("created_by"));
		int updated_by = (map.get("updated_by") == null || map.get("updated_by").trim().length() == 0) ? Integer.MIN_VALUE : Integer.parseInt(map.get("updated_by"));
		Long isbn = (map.get("isbn") == null || map.get("isbn").trim().length() == 0) ? null : Long.parseLong(map.get("isbn"));
		Date year = (map.get("year_of_publication") == null || map.get("year_of_publication").trim().length() == 0) ? null : Date.valueOf(map.get("year_of_publication"));
		int status = (map.get("book_status") == null || map.get("book_status").trim().length() == 0) ? Integer.MIN_VALUE : Integer.parseInt(map.get("book_status"));
		String[] keywords = (map.get("keywords") == null || map.get("keywords").trim().length() == 0) ? null : (";" + map.get("keywords")).split(";");
		BookSearch booksearch = new BookSearch(map.get("title").trim(), map.get("author_name").trim(), map.get("publisher_name").trim(),
				isbn, year, status, created_by, updated_by, keywords);
		List<Book> books = this.bookservice.searchBySpec(booksearch, (int) session.getAttribute("user_id"));
		model.addAttribute("books", books);
		model.addAttribute("date", appSettings.getAppDate());
		return "book/searchresults";
	}

	/**
	 * return search book page.
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchBook(HttpSession session, Model model) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		model.addAttribute("date", appSettings.getAppDate());
		return "book/searchbook";
	}

	/**
	 * creates a new book in the system
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createBook(@ModelAttribute Book book, HttpSession session) {
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		if(!session.getAttribute("type").equals("librarian")) {
			throw new UnauthorizedAccessException();
		}
		book.setCreated_by(((Librarian) session.getAttribute("user")).getLibrarian_id());
		Long isbn = this.bookservice.addBook(book);
		return "redirect:/books/" + isbn;
	}
	
	/**
	 * renews the book
	 * @param isbn
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/renew/{isbn}")
	public String renewBook(@PathVariable Long isbn, HttpSession session){
		if(null == session || session.getAttribute("type") == null) {
			return "login";
		}
		if(session.getAttribute("type").equals("librarian")) {
			throw new UnauthorizedAccessException();
		}
		int patron = (int) session.getAttribute("user_id");
		this.trservice.renewBook(isbn, patron);
		return "redirect:/transaction/summary";
	}
}
