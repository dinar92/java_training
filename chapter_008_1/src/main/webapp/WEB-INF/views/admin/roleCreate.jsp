<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.tracker.RoleValidateService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Create new role</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>

<c:if test="${requestScope.error != null}">
    <div style="background-color: red">
        <c:out value="${requestScope.error}" />
    </div>
    </br>
</c:if>

<b>Please, enter new role:</b><br/><br/>
<form action="${pageContext.servletContext.contextPath}/admin/roleCreate" method="post">
    <input type="number" name="id"/><br/><br/>
    <input type="text" name="name"/><br/><br/>
    <input type="submit" value="CREATE"/>
</form>
<br/><br/><br/>
<b>Existing roles:</b>
<table>
    <tr>
        <th>ID</th>
        <th>Role</th>
    </tr>
    <c:forEach var="role" items="${RoleValidateService.getInstance().findAll()}">
        <tr>
            <td>${role.id}</td>
            <td>${role.name}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="To main page" />
</form>
</body>
</html>
