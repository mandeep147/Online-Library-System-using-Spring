<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.cmpe275.team13.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<%
	StringBuilder sb = new StringBuilder();
	Patron patron = (Patron) session.getAttribute("user");
	List<IssueBook> issue_books = (List<IssueBook>) request.getAttribute("issue_books");
	List<Book> books = (List<Book>) request.getAttribute("books");
	List<Book> waitlist = (List<Book>) request.getAttribute("waitlist");
	request.setAttribute("books", null);
	request.setAttribute("issue_books", null);
	Timestamp date = (Timestamp) request.getAttribute("date");
	if (date == null) {
		date = new Timestamp(new java.util.Date().getTime());
	}
	if (issue_books.size() > 0) {
		sb.append(
				"<table class=\"table table-bordered\"><tr><th> Book </th><th> Title </th><th> Issue Date </th> <th> Due Date </th> <th></th></tr>");
		for (IssueBook issuebook : issue_books) {
			for (Book book : books) {
				if (issuebook.getId().getIsbn().equals(book.getIsbn())) {
					sb.append("<tr><td><img src=\"" + book.getImage() + "\"></td>");
					sb.append("<td>" + book.getTitle() + "</td>");
					sb.append("<td>" + issuebook.getId().getIssue_date().toLocaleString() + "</td>");
					sb.append("<td>" + issuebook.getDue_date().toLocaleString() + "</td>");
					String input = "<button class=\"return btn btn-warning\" id=\"return_"
							+ book.getIsbn() + "\"> Add to return list </button>";
					String input1 = "";
					if (book.getBook_status() == BookStatus.AVAILABLE
							&& issuebook.getDue_date().getTime() > new java.util.Date().getTime())
						input1 = "<br><button class=\"renew btn btn-success\"  id=\"renew_"
								+ book.getIsbn() + "\"> Renew </button>";
					sb.append("<td>" + input + input1 + "</td></tr>");
				}
			}
		}
		sb.append("</table>");
	} else {
		sb.append("No books issued as of now!");
	}
	sb.append("<h3>Books currently in your waitlist - </h3>");
	if (waitlist.size() > 0) {
		sb.append("<table class=\"table table-bordered\"><tr><th> Book </th><th> Title </th></tr>");
		for (Book book : waitlist) {
			sb.append("<tr><td><img src=\"" + book.getImage() + "\"></td>");
			sb.append("<td>" + book.getTitle() + "</td> </tr>");
		}
		sb.append("</table>");
	} else {
		sb.append("No books in the waitlist!");
	}
%>
<body>
	<p style="float: right;">
		<a
			href="http://1-dot-cmpe-275-term-project-team-13.appspot.com/logout">Logout</a>
		<%=date.toLocaleString()%>
	</p>
	<h1>
		Welcome,
		<%=patron.getPatron_name()%>
		!
	</h1>
	<br>
	<h3>Summary of books:</h3>
	<%=sb.toString()%>

	<script type="text/javascript">
		$(".return")
				.click(
						function(event) {
							var isbn = event.currentTarget.id.replace(
									"return_", "");
							var url = "http://1-dot-cmpe-275-term-project-team-13.appspot.com/books/addtocart/"
									+ isbn;
							$("#return_" + isbn).attr("disabled", "disabled");
							$.get(url);
						});
		$(".renew")
				.click(
						function(event) {
							var isbn = event.currentTarget.id.replace("renew_",
									"");
							var url = "http://1-dot-cmpe-275-term-project-team-13.appspot.com/books/renew/"
									+ isbn;
							$("#renew_" + isbn).attr("disabled", "disabled");
							$.get(url, function(success) {
								location.reload();
							}).fail(function() {
								alert("Renew failed!");
							});
						});
	</script>

	<form action="/transaction/return" method="GET">
		<input class="btn btn-success" type="submit" value="Return" />
	</form>

	<a
		href="http://1-dot-cmpe-275-term-project-team-13.appspot.com/books/search/">Search</a>

</body>
</html>