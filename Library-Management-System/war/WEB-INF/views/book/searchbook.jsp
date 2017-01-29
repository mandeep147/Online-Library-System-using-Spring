<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="edu.cmpe275.team13.search.BookSearch"%>
<%@ page import="edu.cmpe275.team13.beans.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: search book</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%
		Timestamp date = (Timestamp) request.getAttribute("date");
		if (date == null) {
			date = new Timestamp(new java.util.Date().getTime());
		}
	%>
	<p style="float: right;">
		<a
			href="http://1-dot-cmpe-275-term-project-team-13.appspot.com/logout">Logout</a>
		<%=date.toLocaleString()%>
	</p>

	<%
		BookSearch bs = new BookSearch();
		String name = null;
		boolean isLibrarian = false;
		if (session.getAttribute("type").equals("librarian")) {
			Librarian user = (Librarian) session.getAttribute("user");
			name = user.getLibrarian_name();
			isLibrarian = true;
		} else {
			Patron user = (Patron) session.getAttribute("user");
			name = user.getPatron_name();
		}
	%>
	<h2>
		Welcome,
		<%=name%></h2>
	<h1>Search Book:</h1>
	<p>Enter one or more attributes to search a book:</p>
	<form action="/books/searchbook/" method="get">
		<div align="center">
			<table>
				<tr>
					<div class="form-group">
					<td><label>ISBN</label></td>
					<td><input class="form-control" name="isbn" value="${bs.isbn}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Author Name</label></td>
					<td><input class="form-control" name="author_name"
						value="${bs.author_name}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Title</label></td>
					<td><input class="form-control" name="title"
						value="${bs.title}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Publisher Name</label></td>
					<td><input class="form-control" name="publisher_name"
						value="${bs.publisher_name}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Year of Publication</label></td>
					<td><input class="form-control" type="text"
						name="year_of_publication" placeholder="yyyy-mm-dd"
						value="${bs.year_of_publication}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Book Status</label></td>
					<td><input class="form-control" name="book_status"
						value="${bs.book_status}"></td>
					</div>
				</tr>

				<c:set var="bool_val"><%=isLibrarian%></c:set>
				<c:if test="${bool_val eq true}">
					<tr>
						<div class="form-group">
						<td><label>Created By</label></td>
						<td><input class="form-control" name="created_by"
							value="${bs.created_by}"></td>
						</div>
					</tr>
					<tr>
						<div class="form-group">
						<td><label>Updated By</label></td>
						<td><input class="form-control" name="updated_by"
							value="${bs.updated_by}"></td>
						</div>
					</tr>
				</c:if>
				<tr>
					<div class="form-group">
					<td><label>Keywords</label></td>
					<td><input class="form-control" name="keywords"
						value="${bs.keywords}" placeholder="Enter ; seperated keywords"></td>
					</div>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input class="btn btn-primary" type="submit"
						value="Search Book" /></td>
				</tr>
			</table>
		</div>
	</form>
	<%
		String string = "";
		if (session.getAttribute("type").equals("librarian")) {
			string += "<div align=\"center\"><form action=\"/books\" method=\"GET\">	<input class=\"btn btn-primary\" type=\"submit\" value=\"Add Book\"/></form></div>";
			string += "<div align=\"center\"><input class=\"btn btn-primary\" type=\"submit\" onclick=\"location.href='/changeSettings'\" value=\"Change Date\" /></div>";
		}
	%>
	<%=string%>

</body>
</html>