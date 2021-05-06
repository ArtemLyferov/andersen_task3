<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New employee</title>
</head>
<body>
<h2>Новый сотрудник:</h2>
<form method="post" action="/employees/new">
    <label>Фамилия: <input type="text" name="surname"></label><br>
    <label>Имя: <input type="text" name="name"></label><br>
    <label>Отчество: <input type="text" name="patronymic"></label><br>
    <label>Дата рождения: <input type="date" name="DOB"></label><br>
    <label>Email: <input type="text" name="email"></label><br>
    <label>Skype: <input type="text" name="skype"></label><br>
    <label>Номер телефона: <input type="text" name="phoneNumber"></label><br>
    <label>Дата трудоустройства: <input type="date" name="employmentDate"></label><br>
    <label>Опыт работы: <input type="number" name="experience"></label><br>
    <select size="1" name="developerLevel">
        <option disabled>Уровень разработчика:</option>
        <option value="J1">J1</option>
        <option value="J2">J2</option>
        <option value="J3">J3</option>
        <option value="M1">M1</option>
        <option value="M2">M2</option>
        <option value="M3">M3</option>
        <option value="S1">S1</option>
        <option value="S2">S2</option>
        <option value="S3">S3</option>
    </select><br>
    <select size="1" name="englishLevel">
        <option disabled>Уровень английского:</option>
        <option value="A1">A1</option>
        <option value="A2">A2</option>
        <option value="B1">B1</option>
        <option value="B2">B2</option>
        <option value="C1">C1</option>
        <option value="C2">C2</option>
    </select><br>
    <select size="1" name="id">
        <option disabled>Команда:</option>
        <c:forEach var="team" items="${requestScope.teams}">
            <option value="${team.id}"><c:out value="${team.teamName}"/></option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Создать">
</form>
</body>
</html>
