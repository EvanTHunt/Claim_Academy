<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Create Account</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
	<div class="wrapper1">
		<h1>Open a new account here.</h1>
		<form action="CreateAccount" method="post">
	      <div class="form-group">
		    <label for="exampleInputEmail1">Account ID</label>
		    <input type="text" required="required" class="form-control" name="Id" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Account ID">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Initial balance</label>
		    <input type="number" required="required" min="0" step="0.01" class="form-control" name="initBalance" id="exampleInputPassword1" placeholder="Initial balance">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a href="home.jsp" class="btn btn-secondary">Cancel</a>
		</form>
	</div>
		<div class="alert">${param.message}</div>	
	</body>
</html>