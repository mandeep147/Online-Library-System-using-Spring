package edu.cmpe275.team13.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cmpe275.team13.beans.Patron;
import edu.cmpe275.team13.exceptions.PatronNotFoundException;

/**
 * DAO layer implementation for Patron.
 */
@Service
@Repository
public class PatronDAOImpl {
	
	/**
	 * adds the patron in the system
	 * @param patron
	 */
	@Transactional
	public void createPatron(Patron patron) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		em.persist(patron);
		em.getTransaction().commit();
	}

	/**
	 * checks whether the patron is present in the system
	 * @param patron
	 * @return
	 */
	@Transactional
	public boolean isPatronPresent(Patron patron) {
		EntityManager em = EMF.get().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Patron> patrons = em.createQuery("SELECT e FROM Patron e").getResultList();
		for (Patron o : patrons) {
			if (o.getPatron_email().equalsIgnoreCase(patron.getPatron_email())
					|| o.getPatron_id() == patron.getPatron_id())
				return true;
		}
		return false;
	}
	
	/**
	 * validates the patron account.
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional
	public Patron validatePatron(String username, String password) {
		EntityManager em = EMF.get().createEntityManager();
		Query query = em.createQuery("SELECT e FROM Patron e where e.patron_email = :email AND e.patron_password = :password");
		query.setParameter("email", username);
		query.setParameter("password", password);
		try
		{
			Patron patron = (Patron) query.getSingleResult();
			if (null != patron) {
				if (username.equalsIgnoreCase(patron.getPatron_email())
						&& password.equals(patron.getPatron_password())) {
					return patron;
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get patron account by id
	 * @param patron_id
	 * @return
	 */
	@Transactional
	public Patron getPatron(int patron_id) {
		EntityManager em = EMF.get().createEntityManager();
		Patron patron = em.find(Patron.class, patron_id);
		if (patron == null) {
			throw new PatronNotFoundException();
		}
		return patron;
	}

	/**
	 * updates the patron
	 * @param patron
	 */
	@Transactional
	public void updatePatron(Patron patron) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		em.merge(patron);
		em.getTransaction().commit();
	}
}