<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All teams</title>
</head>
<body>
<h2>Команды:</h2>
<c:forEach var="team" items="${requestScope.teams}">
    <ul>
        <li><a href="/teams/show?id=${team.id}"><c:out value="${team.teamName}"/></a></li>
    </ul>
</c:forEach>
<hr/>
<form action="/teams/new">
    <button>Добавить команду</button>
</form>
<form action="/">
    <button>Назад</button>
</form>
</body>
</html>
