<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${msg} a Car</title>
</head>

<body>
	<c:if test="${msg == 'Update'}">
		<form action="../cars/${car.id}" method="post">
	</c:if>
	<c:if test="${msg == 'Add'}">
		<form action="../cars" method="post">
	</c:if>
	<table>
		<tr>
			<td>Make:</td>
			<td><input type="text" name="make" value="${car.make}" /> </td>
		</tr>
		<tr>
			<td>Model:</td>
			<td><input type="text" name="model" value="${car.model}" /> </td>
		</tr>
		<tr>
			<td>Year:</td>
			<td><input type="text" name="year" value="${car.year}" /> </td>
		</tr>
		<tr>
			<td>Color:</td>
			<td><input type="text" name="color" value="${car.color}" /> </td>
		</tr>
	</table>
	<input type="submit" value="${msg}"/>
	</form>
	<c:if test="${msg == 'Update'}">
		<form action="delete?carId=${car.id}" method="post">
			<button type="submit">Delete</button>
		</form>
	</c:if>
</body>

</html>