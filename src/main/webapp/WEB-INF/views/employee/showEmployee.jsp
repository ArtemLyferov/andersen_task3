<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
</head>
<body>
<h2>Сотрудник:</h2>
<p>Фамилия: ${requestScope.employee.surname}</p>
<p>Имя: ${requestScope.employee.name}</p>
<p>Отчество: ${requestScope.employee.patronymic}</p>
<p>Дата рождения: ${requestScope.employee.DOB}</p>
<p>Email: ${requestScope.employee.email}</p>
<p>Skype: ${requestScope.employee.skype}</p>
<p>Номер телефона: ${requestScope.employee.phoneNumber}</p>
<p>Дата трудоустройства: ${requestScope.employee.employmentDate}</p>
<p>Опыт работы: ${requestScope.employee.experience}</p>
<p>Уровень разработчика: ${requestScope.employee.developerLevel}</p>
<p>Уровень английского: ${requestScope.employee.englishLevel}</p>
<p>Команда: ${requestScope.employee.team.teamName}</p>
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
