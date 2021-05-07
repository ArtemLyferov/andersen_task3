<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New employee</title>
</head>
<body>
<h2>Новый сотрудник:</h2>
<form method="post" action="/employees/new">
    <table>
        <tr><td>Фамилия:</td><td><input type="text" name="surname"></td></tr>
        <tr><td>Имя:</td><td><input type="text" name="name"></td></tr>
        <tr><td>Отчество:</td><td><input type="text" name="patronymic"></td></tr>
        <tr><td>Дата рождения:</td><td><input type="date" name="DOB"></td></tr>
        <tr><td>Email:</td><td><input type="text" name="email"></td></tr>
        <tr><td>Skype:</td><td><input type="text" name="skype"></td></tr>
        <tr><td>Номер телефона:</td><td><input type="text" name="phoneNumber"></td></tr>
        <tr><td>Дата трудоустройства:</td><td><input type="date" name="employmentDate"></td></tr>
        <tr><td>Опыт работы:</td><td><input type="number" name="experience"></td></tr>
        <tr>
            <td>Уровень разработчика:</td>
            <td>
                <select size="1" name="developerLevel">
                    <option value="J1">J1</option>
                    <option value="J2">J2</option>
                    <option value="J3">J3</option>
                    <option value="M1">M1</option>
                    <option value="M2">M2</option>
                    <option value="M3">M3</option>
                    <option value="S1">S1</option>
                    <option value="S2">S2</option>
                    <option value="S3">S3</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Уровень английского:</td>
            <td>
                <select size="1" name="englishLevel">
                    <option value="A1">A1</option>
                    <option value="A2">A2</option>
                    <option value="B1">B1</option>
                    <option value="B2">B2</option>
                    <option value="C1">C1</option>
                    <option value="C2">C2</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Команда:</td>
            <td>
                <select size="1" name="id">
                    <c:forEach var="team" items="${requestScope.teams}">
                        <option value="${team.id}"><c:out value="${team.teamName}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Создать">
</form>
</body>
</html>
