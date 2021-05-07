<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit feedback</title>
</head>
<body>
<h2>Фидбек:</h2>
<table>
    <tr><td>Описание:</td><td>${requestScope.feedback.description}</td></tr>
    <tr><td>Дата создания:</td><td>${requestScope.feedback.feedbackDate}</td></tr>
    <tr><td>Сотрудник:</td>
        <td>
            ${requestScope.feedback.employee.surname} ${requestScope.feedback.employee.name} ${requestScope.feedback.employee.patronymic}
        </td>
    </tr>
</table>
<hr/>
<h2>Изменить:</h2>
<form method="post" action="/feedbacks/edit">
    <table>
        <tr><td>Описание:</td><td><input type="text" name="description"></td></tr>
        <tr><td>Дата создания:</td><td><input type="date" name="feedbackDate"></td></tr>
        <tr>
            <td>Сотрудник:</td>
            <td>
                <select size="1" name="idEmployee">
                    <c:forEach var="employee" items="${requestScope.employees}">
                        <option value="${employee.id}"><c:out value="${employee.surname}"/> <c:out value="${employee.name}"/> <c:out
                                value="${employee.patronymic}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="number" hidden name="id" value="${requestScope.feedback.id}"/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
