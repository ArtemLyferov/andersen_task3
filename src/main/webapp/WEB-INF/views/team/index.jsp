<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All teams</title>
</head>
<body>
<h1>Все команды</h1>

<<c:forEach var="team" items="${requestScope.teams}">
    <ul>
        <li>id: <c:out value="${team.id}"/></li>
        <li>название: <c:out value="${team.teamName}"/></li>
    <ul>
</c:forEach>
</body>
</html>
