<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All feedbacks</title>
</head>
<body>
<h2>Фидбеки:</h2>
<c:forEach var="feedback" items="${requestScope.feedbacks}">
    <ul>
        <li>
            <a href="/feedbacks/show?id=${feedback.id}">
                <c:out value="${feedback.employee.surname}"/> <c:out value="${feedback.employee.name}"/> <c:out
                    value="${feedback.employee.patronymic}"/> <c:out value="${feedback.feedbackDate}"/>
            </a>
        </li>
    </ul>
</c:forEach>
<hr/>
<form action="/feedbacks/new">
    <button>Добавить фидбек</button>
</form>
<form action="/">
    <button>Назад</button>
</form>
</body>
</html>
