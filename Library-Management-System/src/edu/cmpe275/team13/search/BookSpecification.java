package edu.cmpe275.team13.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import edu.cmpe275.team13.beans.Book;

/**
 * not used in production
 */
public class BookSpecification implements Specification<Book> {

	private BookSearch criteria;

	public BookSpecification(BookSearch criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		final List<Predicate> predicates = new ArrayList<Predicate>();
		if (criteria.getIsbn() != null) {
			predicates.add(cb.equal(root.get(Book_.isbn), criteria.getIsbn()));
		}
		if (criteria.getTitle() != null) {
			predicates.add(cb.like(root.get(Book_.title), "%" + criteria.getTitle() + "%"));
		}
		if (criteria.getAuthor_name() != null) {
			predicates.add(cb.like(root.get(Book_.author_name), "%" + criteria.getAuthor_name() + "%"));
		}
		if (criteria.getPublisher_name() != null) {
			predicates.add(cb.like(root.get(Book_.publisher_name), "%" + criteria.getPublisher_name() + "%"));
		}
		if (criteria.getYear_of_publication() != null) {
			predicates.add(cb.equal(root.get(Book_.year_of_publication), criteria.getYear_of_publication()));
		}
		predicates.add(cb.equal(root.get(Book_.book_status), criteria.getBook_status()));
		if (criteria.getUpdated_by() != Integer.MIN_VALUE) {
			predicates.add(cb.equal(root.get(Book_.updated_by), criteria.getUpdated_by()));
		}
		if (criteria.getCreated_by() != Integer.MIN_VALUE) {
			predicates.add(cb.equal(root.get(Book_.created_by), criteria.getCreated_by()));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
