<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.cmpe275.team13.beans.Book"%>
<%@ page import="java.sql.Timestamp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%
		Timestamp date = (Timestamp) request.getAttribute("date");
	%>
	<p style="float: right;">
		<a
			href="http://1-dot-cmpe-275-term-project-team-13.appspot.com/logout">Logout</a>
		<%=date.toLocaleString()%>
	</p>

	<%
		Book book = (Book) request.getAttribute("book");
	%>
	<form action="/books/${book.isbn}" method="post">
		<div align="center">
			<table>
				<tr>
					<div class="form-group">
						<td><label><span>ISBN</span></label></td>
						<td><input class="form-control" name="isbn"
							value="${book.isbn}" readonly></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Author Name</span></label></td>
						<td><input class="form-control" name="author_name"
							value="${book.author_name}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Title</span></label></td>
						<td><input class="form-control" name="title"
							value="${book.title}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Call Number</span></label></td>
						<td><input class="form-control" name="call_number"
							value="${book.call_number}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Publisher Name</span></label></td>
						<td><input class="form-control" name="publisher_name"
							value="${book.publisher_name}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Year of Publication</span></label></td>
						<td><input class="form-control" type="date"
							name="year_of_publication" value="${book.year_of_publication}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Location in Library</span></label></td>
						<td><input class="form-control" name="location_in_library"
							value="${book.location_in_library}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Number of Copies</span></label></td>
						<td><input class="form-control" name="number_of_copies"
							value="${book.number_of_copies}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Book Status</span></label></td>
						<td><input class="form-control" name="book_status"
							value="${book.book_status}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Image</span></label></td>
						<td><input class="form-control" type="url" name="image"
							value="${book.image}"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Available Copies</span></label></td>
						<td><input class="form-control" name="available_copies"
							value="${book.available_copies}" readonly></td>
					</div>
				</tr>

				<tr>
					<div class="form-group">
						<td><label><span>Created By</span></label></td>
						<td><input class="form-control" name="created_by"
							value="${book.created_by}" readonly></td>
					</div>
				</tr>

				<tr>
					<div class="form-group">
						<td><label><span>Updated By</span></label></td>
						<td><input class="form-control" name="updated_by"
							value="${book.updated_by}" readonly></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td><label><span>Keywords</span></label></td>
						<td><input class="form-control" name="keywords"
							value="${book.keywords}" placeholder="Enter ; seperated keywords"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<td colspan="2"><input class="btn btn-primary" type="submit"
							value="Update Book" /></td>
					</div>
				</tr>
			</table>
			<div align="center">
	</form>
	<form action="/books/${book.isbn}" method="post">
		<div align="center">
			<input type="hidden" name="_method" value="delete" /> <input
				type="submit" class="btn btn-danger" value="Delete Book" />

		</div>
	</form>
</body>
</html>