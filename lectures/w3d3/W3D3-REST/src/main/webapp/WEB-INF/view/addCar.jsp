<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a Car</title>
    <style>
        .errorblock {
    color: red;
    font-weight: bold;
}

.error {
    color: red;
}
      </style>
</head>
<body>
    <h1>Add a Car</h1>
    <form:form modelAttribute="car" action="addCar" method="post">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <table>
            <tr>
                <td>Make:</td>
                <td>
                    <form:input path="make" />
                    <form:errors path="make" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td>Model:</td>
                <td>
                    <form:input path="model" />
                    <form:errors path="model" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td>Year:</td>
                <td>
                    <form:input path="year" />
                    <form:errors path="year" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td>Color:</td>
                <td>
                    <form:input path="color" />
                    <form:errors path="color" cssClass="error" />
                </td>
            </tr>
        </table>
        <input type="submit" value="Add Car" />
    </form:form>
</body>
</html>
