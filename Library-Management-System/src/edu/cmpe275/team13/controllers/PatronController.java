package edu.cmpe275.team13.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.team13.beans.AppSettings;
import edu.cmpe275.team13.beans.Librarian;
import edu.cmpe275.team13.beans.Patron;
import edu.cmpe275.team13.exceptions.DuplicateUserException;
import edu.cmpe275.team13.persistence.LibrarianDAOImpl;
import edu.cmpe275.team13.persistence.PatronDAOImpl;
import edu.cmpe275.team13.service.TransactionService;
import edu.cmpe275.util.Mailmail;

/**
 * This handles the user related operations.
 */
@Controller
public class PatronController {

	@Autowired
	private PatronDAOImpl patronDAO;

	@Autowired
	private LibrarianDAOImpl libDao;

	@Autowired
	private TransactionService trService;
	
	private Mailmail mail = new Mailmail();

	/**
	 * return the welcome page
	 * @return
	 */
	@RequestMapping(value = "/")
	public String getWelcomePage() {
		return "home";
	}

	/**
	 * logout the user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		if (session != null)
			session.invalidate();
		return "patronLogin";
	}

	/**
	 * return the login page.
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getPatronLogin(Locale locale, Model model) {
		return "patronLogin";
	}

	/**
	 * login the user
	 * @param email
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String patronLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpSession session) throws NoSuchAlgorithmException {

		if (email.contains("@sjsu.edu")) {
			Librarian librarian = libDao.validateLibrarian(email, getSHADigest(password));
			if (librarian == null) {
				model.addAttribute("error", "Username and password do not match");
			} else {
				if (librarian.isLibrarian_verified()) {
					session.setAttribute("user", librarian);
					session.setAttribute("type", "librarian");
					session.setAttribute("user_id", librarian.getLibrarian_id());
					return "book/searchbook";
				} else {
					model.addAttribute("error", "Please verify your email.");
				}
			}
		} else {
			Patron patron = patronDAO.validatePatron(email, getSHADigest(password));
			if (patron == null) {
				model.addAttribute("error", "Username and password do not match");
			} else {
				if (patron.isPatron_verified()) {
					session.setAttribute("user", patron);
					session.setAttribute("type", "patron");
					session.setAttribute("user_id", patron.getPatron_id());
					// return "patrondashboard";
					return "redirect:/transaction/summary";
				} else {
					model.addAttribute("error", "Please verify your email.");
				}
			}
		}
		return "login";
	}

	/**
	 * activate the librarian when clicked on activation link.
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/librarianActivationlink", method = RequestMethod.GET)
	public String librarianUpdateActivationLink(@RequestParam("key") String key, Model model) {

		byte[] b = Base64.decodeBase64(key);
		String decodedKey = new String(b);
		if (decodedKey.contains(":")) {
			String[] s = decodedKey.split(":");
			int patron_id = Integer.parseInt(s[0]);
			Librarian librarian = libDao.getLibrarian(patron_id);
			librarian.setLibrarian_verified(true);
			libDao.updateLibrarian(librarian);
			sendConfirmationMail(librarian.getLibrarian_email());
		}
		return "home";
	}

	/**
	 * return signup page.
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) {
		return "home";
	}

	/**
	 * sign up the user.
	 * @param patron_email
	 * @param patron_password
	 * @param patron_name
	 * @param patron_id
	 * @param model
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String createPatron(@RequestParam("email") String patron_email,
			@RequestParam("password") String patron_password, @RequestParam("name") String patron_name,
			@RequestParam("studentid") int patron_id, Model model) throws NoSuchAlgorithmException {
		// System.out.println("sdsd");

		if (patron_email.contains("@sjsu.edu")) {
			Librarian librarian = new Librarian();
			librarian.setLibrarian_name(patron_name);
			librarian.setLibrarian_email(patron_email);
			librarian.setLibrarian_id(patron_id);
			patron_password = getSHADigest(patron_password);
			librarian.setLibrarian_password(patron_password);
			if (!libDao.isLibrarianPresent(librarian)) {
				libDao.createLibrarian(librarian);
			} else {
				throw new DuplicateUserException();
			}
			String link = "http://1-dot-cmpe-275-term-project-team-13.appspot.com/librarianActivationlink?key=";
			String s = librarian.getLibrarian_id() + ":" + librarian.getLibrarian_email() + ":" + "shrutSalt";
			link = link + Base64.encodeBase64String(s.getBytes());
			String sender = "librarymanagement275@gmail.com";
			String receiver = librarian.getLibrarian_email();
			mail.sendMail(sender, receiver, "SJPL: Library Membership Activation Link", link);

		}

		else {
			Patron patron = new Patron();
			patron.setPatron_name(patron_name);
			patron.setPatron_email(patron_email);
			patron.setPatron_id(patron_id);
			patron_password = getSHADigest(patron_password);
			patron.setPatron_password(patron_password);

			if (!patronDAO.isPatronPresent(patron)) {

				patronDAO.createPatron(patron);
				String link = "http://1-dot-cmpe-275-term-project-team-13.appspot.com/activationlink?key=";
				String s = patron.getPatron_id() + ":" + patron.getPatron_email() + ":" + "shrutSalt";
				System.out.println("string:" + s);
				link = link + Base64.encodeBase64String(s.getBytes());
				String sender = "librarymanagement275@gmail.com";
				String receiver = patron.getPatron_email();
				mail.sendMail(sender, receiver, "Library Membership Activation Link", link);
				System.out.println("success");
			} else {
				throw new DuplicateUserException();
			}
		}

		return "home";
	}

	/**
	 * activate the user. 
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/activationlink", method = RequestMethod.GET)
	public String updateActivationLink(@RequestParam("key") String key, Model model) {
		byte[] b = Base64.decodeBase64(key);
		String decodedKey = new String(b);
		if (decodedKey.contains(":")) {
			String[] s = decodedKey.split(":");
			int patron_id = Integer.parseInt(s[0]);
			Patron patron = patronDAO.getPatron(patron_id);
			patron.setPatron_verified(true);
			patronDAO.updatePatron(patron);
			sendConfirmationMail(patron.getPatron_email());
		}
		return "home";
	}

	/**
	 * sends the confirmation mail
	 * @param to
	 */
	private void sendConfirmationMail(String to) {
		String sender = "librarymanagement275@gmail.com";
		mail.sendMail(sender, to, "Team - 13 - Library Management - Activation Successfull",
				"Hi, your account is now activated. Please login to access your account!");

	}
	
	

	/**
	 * encrypt the password.
	 * @param string
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String getSHADigest(String string) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] bytes = md.digest(string.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/**
	 * return the change date page.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeSettings", method = RequestMethod.GET)
	public String modifyDateSteeings(Model model) {
		AppSettings appset = AppSettings.getInstance();
		java.util.Date today = new java.util.Date();
		Timestamp changedDate = new Timestamp(today.getTime());
		if(appset.getAppDate() != null) {
			appset.setAppDate(changedDate);
		}
		model.addAttribute("date", appset.getAppDate());
		trService.updateEmail();
		trService.updateReservations();
		return "settings";
	}

	/**
	 * change the date in the system.
	 * @param date
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeDate", method = RequestMethod.POST)
	public String modifyDate(@RequestParam("changedDate") String date, Model model) {
		AppSettings appset = AppSettings.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Timestamp changedDate = null;
		try {
			changedDate = new Timestamp(dateFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		appset.setAppDate(changedDate);
		model.addAttribute("date", appset.getAppDate());
		trService.updateEmail();
		trService.updateReservations();
		return "book/searchbook";
	}
}
