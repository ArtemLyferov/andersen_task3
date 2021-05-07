<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All projects</title>
</head>
<body>
<h2>Проекты:</h2>
<c:forEach var="project" items="${requestScope.projects}">
    <ul>
        <li><a href="/projects/show?id=${project.id}"><c:out value="${project.nameProject}"/></a></li>
    </ul>
</c:forEach>
<hr/>
<form action="/projects/new">
    <button>Добавить проект</button>
</form>
<form action="/">
    <button>Назад</button>
</form>
</body>
</html>
