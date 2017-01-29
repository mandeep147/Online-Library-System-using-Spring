<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<form action="/login" method="POST">
		<div align="center">
			<table>
				<tr>
				<div class="form-group">
					<td><span>Email ID</span></td>
					<td><input class="form-control"  type="text" name="email" /></td>
				</div>
				</tr>
				<tr>
				<div class="form-group">
					<td><span>Password</span></td>
					<td><input class="form-control"  type="password" name="password" /></td>
				</div>
				</tr>
				<tr>
					<td></td>
					<td><button class="btn btn-primary" type="submit" value="Submit">Submit</button></td>
				</tr>
			</table>
			<div style="color: red">${error}</div>
		</div>
	</form>
</body>
</html>