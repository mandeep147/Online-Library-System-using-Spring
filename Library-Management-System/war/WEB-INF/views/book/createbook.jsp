<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.cmpe275.team13.beans.Book"%>
<%@ page import="java.sql.Timestamp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: create book</title>
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

	<h1>Book:</h1>
	<form action="/books/" method="post">
		<div align="center">
			<table>
				<tr>
					<div class="form-group">
					<td><label>ISBN</label></td>
					<td><input class="form-control" name="isbn"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Author Name</label></td>
					<td><input class="form-control" name="author_name"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Title</label></td>
					<td><input class="form-control" name="title"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Call Number</label></td>
					<td><input class="form-control" name="call_number"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Publisher Name</label></td>
					<td><input class="form-control" name="publisher_name"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Year of Publication</label></td>
					<td><input class="form-control" type="date"
						name="year_of_publication"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Location in Library</label></td>
					<td><input class="form-control" name="location_in_library"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Number of Copies</label></td>
					<td><input class="form-control" name="number_of_copies"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Book Status</label></td>
					<td><input class="form-control" name="book_status"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Image</label></td>
					<td><input class="form-control" type="url" name="image"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Available Copies</label></td>
					<td><input class="form-control" name="available_copies"></td>
					</div>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>Keywords</label></td>
					<td><input class="form-control" name="keywords"
						placeholder="Enter ; seperated keywords"></td>
					</div>
				</tr>
				<tr>
					<td colspan="2"><input class="btn btn-primary" type="submit"
						value="Add Book" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>