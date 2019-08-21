<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Make Withdrawal</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
		<h1>Make a withdrawal here.</h1>
		<form action="Withdraw" method="post">
		  <div>
		    <select name="accountName" class="form-control" required="required">
			  <option value="" disabled selected>Select account</option>  			
			  <c:forEach var="account" items="${user.accounts}">
  				<option value="${account.id}">${account.id} - $${account.balance}</option>
  			  </c:forEach>
		    </select>
		  </div>    
		  <div class="form-group">
		    <label for="exampleInputPassword1">Withdrawal amount</label>
		    <input type="number" min="0" step="0.01" class="form-control" name="withdrawAmount" id="exampleInputPassword1" placeholder="Withdrawal amount">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a href="home.jsp" class="btn btn-secondary">Cancel</a>
		</form>
	</body>
</html>