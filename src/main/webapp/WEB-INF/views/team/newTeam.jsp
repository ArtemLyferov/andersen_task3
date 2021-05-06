<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New team</title>
</head>
<body>
<h2>Новая команда:</h2>
<form method="post" action="/teams/new">
    <label>Название: <input type="text" name="teamName"></label><br>
    <input type="submit" value="Создать">
</form>
</body>
</html>
