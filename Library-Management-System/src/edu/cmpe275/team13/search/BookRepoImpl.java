package edu.cmpe275.team13.search;

import java.util.List;

import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import edu.cmpe275.team13.beans.Book;
/**
 * not used in production
 */
@Service
public class BookRepoImpl implements BookRepository<Book> {

	@Override
	public long count(Specification<Book> spec) {
		/*EntityManager em = EMF.get().createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		
		//Session session;
		//Criteria criteria = session.createCriteria(Book.class);
		
		Selection<? extends Book> root = (Selection<? extends Book>) query.from(Book.class);
		query.where(spec.toPredicate((Root<Book>) root, query, builder));
		return em.createQuery(query.select(root).toString()).getResultList().size();*/
		return 0;
	}

	@Override
	public Page<Book> findAll(Specification<Book> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll(Specification<Book> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findOne(Specification<Book> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll(Specification<Book> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
