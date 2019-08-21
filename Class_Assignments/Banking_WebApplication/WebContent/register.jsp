<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
		<div class="wrapper1">
		<h1>New User Registration</h1>
		<form action="Register" method="post">
	      <div class="form-group">
		    <label for="firstName">First Name</label>
		    <input type="text" required="required" class="form-control" name="firstName" id="firstName" placeholder="First name">
		  </div>
		  <div class="form-group">
		    <label for="lastName">Last Name</label>
		    <input type="text" required="required" class="form-control" name="lastName" id="lastName" placeholder="Last name">
		  </div>
	      <div class="form-group">
		    <label for="email">Email address</label>
		    <input type="email" required="required" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Email address">
		  </div>
		  <div class="form-group">
		    <label for="password">Password</label>
		    <input type="password" required="required" class="form-control" name="password" id="password" placeholder="Password">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a href="index.jsp" class="btn btn-secondary">Back to Login</a>
		</form>
		</div>
	</body>
</html>