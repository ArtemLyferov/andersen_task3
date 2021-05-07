<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New feedback</title>
</head>
<body>
<h2>Новый фидбек:</h2>
<form method="post" action="/feedbacks/new">
  <table>
    <tr><td>Описание:</td><td><input type="text" name="description"></td></tr>
    <tr><td>Дата создания:</td><td><input type="date" name="feedbackDate"></td></tr>
    <tr>
      <td>Сотрудник:</td>
      <td>
        <select size="1" name="id">
          <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}"><c:out value="${employee.surname}"/> <c:out value="${employee.name}"/> <c:out
                    value="${employee.patronymic}"/></option>
          </c:forEach>
        </select>
      </td>
    </tr>
  </table>
  <input type="submit" value="Создать">
</form>
</body>
</html>
