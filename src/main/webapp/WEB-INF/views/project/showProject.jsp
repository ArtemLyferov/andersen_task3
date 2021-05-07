<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project</title>
</head>
<body>
<h2>Проект:</h2>
<table>
  <tr><td>Название:</td><td>${requestScope.project.nameProject}</td></tr>
  <tr><td>Заказчик:</td><td>${requestScope.project.customer}</td></tr>
  <tr><td>Продолжительность:</td><td>${requestScope.project.duration}</td></tr>
  <tr><td>Методология:</td><td>${requestScope.project.methodology}</td></tr>
  <tr><td>ПМ:</td>
    <td>
      ${requestScope.project.projectManager.surname} ${
        requestScope.project.projectManager.name} ${
        requestScope.project.projectManager.patronymic}
    </td>
  </tr>
  <tr><td>Команда:</td><td>${requestScope.project.team.teamName}</td></tr>
</table>
<hr/>
<form method="post" action="/projects/delete">
  <input type="number" hidden name="id" value="${requestScope.project.id}"/>
  <input type="submit" value="Удалить"/>
</form>
<form action="/projects/edit">
  <input type="number" hidden name="id" value="${requestScope.project.id}"/>
  <input type="submit" value="Редактировать"/>
</form>
</body>
</html>
