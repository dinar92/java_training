<%@ page import="ru.job4j.tracker.ValidateService" %>
<%@ page import="ru.job4j.tracker.User" %>
<%@ page import="ru.job4j.tracker.Validate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <% Validate<User> store = ValidateService.getInstance(); %>
    <% for (User user : store.findAll()) {%>

    <tr>
        <td><%=user.toString()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="UPDATE"/>
            </form>
            <form action="<%=request.getContextPath()%>/" method="post">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="DELETE"/>
            </form>
        </td>
    </tr>

    <% } %>
</table>
<form action="<%=request.getContextPath()%>/create" method="get">
    <input type="submit" value="Create user"/>
</form>

</body>
</html>
