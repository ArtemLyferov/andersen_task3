<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New project</title>
</head>
<body>
<h2>Новый проект:</h2>
<form method="post" action="/projects/new">
    <table>
        <tr><td>Название:</td><td><input type="text" name="nameProject"></td></tr>
        <tr><td>Заказчик:</td><td><input type="text" name="customer"></td></tr>
        <tr><td>Продолжительность:</td><td><input type="number" name="duration"></td></tr>
        <tr><td>Методология:</td>
            <td>
                <select size="1" name="methodology">
                    <option value="WATERFALL_MODEL">Waterfall Model</option>
                    <option value="AGILE_MODEL">Agile Model</option>
                    <option value="V_MODEL">V-Model</option>
                    <option value="RAD">RAD</option>
                    <option value="SPIRAL">Spiral</option>
                    <option value="EXTREME_PROGRAMMING">Extreme Programming</option>
                    <option value="LEAN">Lean</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>ПМ:</td>
            <td>
                <select size="1" name="idEmployee">
                    <c:forEach var="employee" items="${requestScope.employees}">
                        <option value="${employee.id}"><c:out value="${employee.surname}"/> <c:out value="${employee.name}"/> <c:out
                                value="${employee.patronymic}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Команда:</td>
            <td>
                <select size="1" name="idTeam">
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
