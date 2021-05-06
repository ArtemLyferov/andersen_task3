<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit team</title>
</head>
<body>
<h2>Команда:</h2>
<p>${requestScope.team.teamName}</p>
<h2>Изменить:</h2>
<form method="post" action="/teams/edit">
    <label>Новое название: <input type="text" name="teamName"></label><br>
    <input type="number" hidden name="id" value="${requestScope.team.id}" />
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
