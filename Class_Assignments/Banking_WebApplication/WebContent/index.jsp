<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
	<div class="wrapper1">
		<h1>Welcome to the Java Bank Login page!</h1>
		<form action="Login" method="post">
	      <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input type="email" required="required" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" required="required" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<br>
		<p><a href="register.jsp">Not a registered user? Click here to register</a></p>
	</div>	
	<div class="alert">${param.message}</div>	
	</body>
</html>