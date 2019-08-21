<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
		<a class="btn btn-outline-success my-2 my-sm-0" href="Logout">Logout</a>
		  <!-- Main jumbotron for a primary marketing message or call to action -->
		  <div class="jumbotron">
		    <div class="container">
		      <h1 class="display-3">Hello, ${user.firstName}!</h1>
		      <p>Welcome to your dashboard. You may access all of your Java Bank accounts here.</p>
		    </div>
		  </div>
		
		  <div class="container">
		    <!-- Example row of columns -->
		    <div class="row">
		      <div class="col-md-4">
		        <h2>Your accounts summary</h2>
		        
		        <c:choose>
			        <c:when test="${user.getAccounts().size() > 0}">
						<table class="container">
							<tr>
								<th>Account name</th>
								<th>Balance</th>
							</tr>
							<c:forEach var="account" items="${user.getAccounts()}">
								<tr>
									<td><c:out value="${account.getId()}"/></td>
									<td><fmt:formatNumber type = "currency" value = "${account.balance}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<p>You currently have no open accounts</p>
					</c:otherwise>
				</c:choose>
		      </div>
		      <div class="col-md-4">
		        <h2>Account actions</h2> 
			      <p><a href="deposit.jsp" class="btn btn-secondary" role="button">Deposit/Withdraw funds &raquo;</a></p>
			      <c:if test="${user.accounts.size()>1}">
			      	<p><a href="transfer.jsp" class="btn btn-secondary" role="button">Transfer funds &raquo;</a></p>
			      </c:if> 
			      <div class="basicBorder">
			      <form method="post" action="TransactionHistory">
			        <select name="account" class="form-control" required="required">
			  		  <option value="" disabled selected>Select account</option>  			
			  		  <c:forEach var="account" items="${user.accounts}">
  					    <option value="${account.id}">${account.id} - <fmt:formatNumber type = "currency" value = "${account.balance}"/></option>
  			  	      </c:forEach>
		    	    </select>
		  			<button type="submit" class="btn btn-secondary">See transaction history &raquo;</button>
		    	  </form>
		    	  </div>
			  </div>

		      <div class="col-md-4">
		        <h2>Need a new account? Open one here.</h2>
		        <p><a class="btn btn-secondary" href="createaccount.jsp" role="button">Open Account &raquo;</a></p>
		      </div>
		      
		    </div>
		
		    <hr>
		
		  </div> <!-- /container -->
		
	
		<footer class="container">
		  <p>&copy; Java Bank 2019</p>
		</footer>
	</body>
</html>