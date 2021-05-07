<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feedback</title>
</head>
<body>
<h2>Фидбек:</h2>
<table>
    <tr><td>Описание:</td><td>${requestScope.feedback.description}</td></tr>
    <tr><td>Дата создания:</td><td>${requestScope.feedback.feedbackDate}</td></tr>
    <tr><td>Сотрудник:</td>
        <td>
            ${requestScope.feedback.employee.surname} ${requestScope.feedback.employee.name} ${requestScope.feedback.employee.patronymic}
        </td>
    </tr>
</table>
<hr/>
<form method="post" action="/feedbacks/delete">
    <input type="number" hidden name="id" value="${requestScope.feedback.id}"/>
    <input type="submit" value="Удалить"/>
</form>
<form action="/feedbacks/edit">
    <input type="number" hidden name="id" value="${requestScope.feedback.id}"/>
    <input type="submit" value="Редактировать"/>
</form>
</body>
</html>
