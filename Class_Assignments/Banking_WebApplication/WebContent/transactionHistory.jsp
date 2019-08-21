<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Transaction History</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
		<div class="wrapper2">
		<c:forEach var="account" items="${user.accounts}">
			<c:if test="${account.id eq param.selectedAccount}">
				<h1>Transaction history for: ${param.selectedAccount}</h1><br>
				<table class="table">
					<thead class="thead-dark">
					<tr>
						<th>Transaction type</th>
						<th>Transaction amount</th>
						<th>Ending balance</th>
					</tr>
					</thead>
					<c:forEach var="trans" items="${account.transHist}">
						<tr>
							<th>${trans.split(" ")[0]}</th>
							<th>${trans.split(" ")[1]}</th>
							<th>${trans.split(" ")[2]}</th>
						</tr>
					</c:forEach>
				</table>
				<br>
				<a href="home.jsp" class="btn btn-secondary">Back to Home</a>
			</c:if>
		</c:forEach>
		</div>
	</body>
</html>