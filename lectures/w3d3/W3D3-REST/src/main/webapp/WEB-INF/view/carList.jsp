<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"
%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cars currently in the shop</title>
</head>

<body>
	<h1>Cars currently in the shop</h1>
	<table>
		<c:forEach var="car" items="${cars}">
			<tr>
				<td>${car.make}</td>
				<td>${car.model}</td>
				<td>${car.year}</td>
				<td>${car.color}</td>
				<td><a href="cars/update/${car.id}">edit</a></td>
				<!-- <td>
					<td><a href="cars/delete/${car.id}" method="post">delete</a></td>
					<form action="cars/delete/${car.id}" method="post">
						<button type="submit">Delete</button>
					</form>
				</td> -->
				
			</tr>
		</c:forEach>
	</table>

	<sec:authorize access="hasRole('ADMIN')">
		<a href="cars/addCar"> Add a Car</a>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<p><a href="login">Login</a></p>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<p>Welcome Back,
			<sec:authentication property="name" />
		</p>
		<p><a href="logout">Logout</a></p>
	</sec:authorize>
</body>

</html>