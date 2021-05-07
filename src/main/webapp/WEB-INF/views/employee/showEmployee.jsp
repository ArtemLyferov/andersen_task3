<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
</head>
<h2>Сотрудник:</h2>
<table>
    <tr><td>Фамилия:</td><td>${requestScope.employee.surname}</td></tr>
    <tr><td>Имя:</td><td>${requestScope.employee.name}</td></tr>
    <tr><td>Отчество:</td><td>${requestScope.employee.patronymic}</td></tr>
    <tr><td>Дата рождения:</td><td>${requestScope.employee.DOB}</td></tr>
    <tr><td>Email:</td><td>${requestScope.employee.email}</td></tr>
    <tr><td>Skype:</td><td>${requestScope.employee.skype}</td></tr>
    <tr><td>Номер телефона:</td><td>${requestScope.employee.phoneNumber}</td></tr>
    <tr><td>Дата трудоустройства:</td><td>${requestScope.employee.employmentDate}</td></tr>
    <tr><td>Опыт работы:</td><td>${requestScope.employee.experience}</td></tr>
    <tr><td>Уровень разработчика:</td><td>${requestScope.employee.developerLevel}</td></tr>
    <tr><td>Уровень английского:</td><td>${requestScope.employee.englishLevel}</td></tr>
    <tr><td>Команда:</td><td>${requestScope.employee.team.teamName}</td></tr>
</table>
<hr/>
    <form method="post" action="/employees/delete">
        <input type="number" hidden name="id" value="${requestScope.employee.id}"/>
        <input type="submit" value="Удалить"/>
    </form>
    <form action="/employees/edit">
        <input type="number" hidden name="id" value="${requestScope.employee.id}"/>
        <input type="submit" value="Редактировать"/>
    </form>
</body>
</html>
