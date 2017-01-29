package edu.cmpe275.team13.search;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;

import edu.cmpe275.team13.beans.Book;

@javax.persistence.metamodel.StaticMetamodel(edu.cmpe275.team13.beans.Book.class)

/**
 * not used in production
 */
public class Book_ {
	public static volatile SingularAttribute<Book, Long> isbn;
	public static volatile SingularAttribute<Book, String> title;
	public static volatile SingularAttribute<Book, String> author_name;
	public static volatile SingularAttribute<Book, String> publisher_name;
	public static volatile SingularAttribute<Book, Date> year_of_publication;
	public static volatile SingularAttribute<Book, Boolean> book_status;
	public static volatile SingularAttribute<Book, Integer> created_by;
	public static volatile SingularAttribute<Book, Integer> updated_by;
}