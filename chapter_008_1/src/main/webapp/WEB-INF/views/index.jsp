<%@ page import="ru.job4j.tracker.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
    <title>The list of users</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>

<h1>The list of users</h1>
<table>
    <tr>
        <th><b>User name</b></th>
        <th><b>Action</b></th>
    </tr>
    <c:forEach var="user" items="${ValidateService.getInstance().findAll()}">
        <tr>
            <td>${user}
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="UPDATE"/>
                </form>
                <form action="${pageContext.servletContext.contextPath}/action" method="post">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="DELETE"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="Create user"/>
</form>

</body>
</html>
