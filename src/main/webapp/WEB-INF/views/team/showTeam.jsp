<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team</title>
</head>
<body>
<h2>Команда:</h2>
<p>${requestScope.team.teamName}</p>
<form method="post" action="/teams/delete">
    <input type="number" hidden name="id" value="${requestScope.team.id}" />
    <input type="submit" value="Удалить"/>
</form>
<form action="/teams/edit">
    <input type="number" hidden name="id" value="${requestScope.team.id}" />
    <input type="submit" value="Редактировать"/>
</form>
</body>
</html>
