<%@ page import="ru.job4j.tracker.RoleValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
    <title>Create new user</title>
</head>
<body>
<h1>Create new user</h1>
<form action='${pageContext.servletContext.contextPath}/action' method='post'>
    <input type='hidden' name='action' value='add'/>
    ID:<br/>
    <input type='text' name='id'/><br/><br/>
    Name:<br/>
    <input type='text' name='name'/><br/><br/>
    Login:<br/>
    <input type='text' name='login'/><br/><br/>
    Password:<br/>
    <input type='password' name='password'/><br/><br/>
    Email:<br/>
    <input type='email' name='email'/><br/><br/>
    Create date:<br/>
    <input type='date' name='createDate'/><br/><br/>
    Role:<br/>
    <select name="role">
        <c:forEach var="role" items="${RoleValidateService.getInstance().findAll()}">
        <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select><br/><br/>
        <input type='submit' value='CREATE'/>
</form>
<br/>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="To main page" />
</form>
</body>
</html>
