<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All employees</title>
</head>
<body>
<h2>Сотрудники:</h2>
<c:forEach var="employee" items="${requestScope.employees}">
    <ul>
        <li>
            <a href="/employees/show?id=${employee.id}">
                <c:out value="${employee.surname}"/> <c:out value="${employee.name}"/> <c:out
                    value="${employee.patronymic}"/>
            </a>
        </li>
    </ul>
</c:forEach>
<hr/>
<form action="/employees/new">
    <button>Добавить сотрудника</button>
</form>
<form action="/">
    <button>Назад</button>
</form>
</body>
</html>
