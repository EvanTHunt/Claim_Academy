<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Deposit/Withdraw funds</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
		<div class="wrapper1">
		<h1>Make a deposit or withdrawal here.</h1>
		  <form action="Deposit" method="post">
		  <div class="form-check">
		    <input class="form-check-input" type="radio" required="required" name="transType" id="exampleRadios1" value="deposit">
		    <label class="form-check-label" for="exampleRadios1">Make Deposit</label>
		  </div>
		  <div class="form-check">
		    <input class="form-check-input" type="radio" name="transType" id="exampleRadios2" value="withdrawal">
		    <label class="form-check-label" for="exampleRadios2">Make Withdrawal</label>
		  </div>
		  <br>
		  <div>
		  	<label for="exampleInputPassword1">Select account</label>
		    <select name="accountName" class="form-control" required="required">
			  <option value="" disabled selected>Select account</option>  			
			  <c:forEach var="account" items="${user.accounts}">
  				<option value="${account.id}">${account.id} - <fmt:formatNumber type = "currency" value = "${account.balance}"/></option>
  			  </c:forEach>
		    </select>
		  </div>   
		  <br> 
		  <div class="form-group">
		    <label for="exampleInputPassword1">Amount</label>
		    <input type="number" min="0" step="0.01" required="required" class="form-control" name="depositAmount" id="exampleInputPassword1" placeholder="Amount">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a href="home.jsp" class="btn btn-secondary">Cancel</a>
		</form>
		</div>
	</body>
</html>