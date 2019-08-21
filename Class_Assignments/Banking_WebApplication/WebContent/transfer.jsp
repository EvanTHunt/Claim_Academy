<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Transfer Funds</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
  		$(document).ready(function () {

        	$('select').change(function () {

            	if ($('select option[value="' + $(this).val() + '"]:selected').length > 1)
            	{
                	alert('You have alrady selected this item. You must select a different account.')
            	}
        	});
    	});
		</script>
	</head>
	<body>
		<div class="wrapper1">
		<h1>Transfer funds here.</h1>
		<form action="Transfer" method="post">
		  <div>
		  	<label for="exampleInputPassword1">Transfer FROM</label>
		    <select name="accountFrom" class="form-control" required="required">
			  <option value="" disabled selected>Select account</option>  			
			  <c:forEach var="account" items="${user.accounts}">
  				<option value="${account.id}">${account.id} - <fmt:formatNumber type = "currency" value = "${account.balance}"/></option>
  			  </c:forEach>
		    </select>
		  </div> 
		  <div>
		  	<label for="exampleInputPassword1">Transfer TO</label>
		    <select name="accountTo" class="form-control" required="required">
			  <option value="" disabled selected>Select account</option>  			
			  <c:forEach var="account" items="${user.accounts}">
  				<option value="${account.id}">${account.id} - <fmt:formatNumber type = "currency" value = "${account.balance}"/></option>
  			  </c:forEach>
		    </select>
		  </div>  
		  <br> 
		  <div class="form-group">
		    <label for="exampleInputPassword1">Transfer amount</label>
		    <input type="number" min="0" step="0.01" required="required" class="form-control" name="transferAmount" id="exampleInputPassword1" placeholder="Transfer amount">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a href="home.jsp" class="btn btn-secondary">Cancel</a>
		</form>
		</div>
	</body>
</html>