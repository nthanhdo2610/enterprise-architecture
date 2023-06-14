<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Books currently in the shop</title>
</head>

<body>
<h1>Books currently in the shop</h1>
<table>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.ISBN}</td>
            <td>${book.price}</td>
            <sec:authorize access="hasRole('ADMIN')">
                <td><a href="books/${book.id}">edit</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="hasRole('ADMIN')">
    <a href="books/add"> Add a Book</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <a href="logout">Logout</a>
</sec:authorize>
</body>

</html>