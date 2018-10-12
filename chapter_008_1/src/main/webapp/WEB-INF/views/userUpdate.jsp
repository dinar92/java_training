<%@ page import="ru.job4j.tracker.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
    <title>Create new user</title>
</head>
<body>
<h1>Edit user's data</h1>
<form action='${pageContext.servletContext.contextPath}/action' method='post'>
    <c:set var="user" value="${ValidateService.getInstance().findById(param.id)}"/>
    <input type='hidden' name='action' value='update'/>
    ID:<br/>
    <input type='text' name='id' value='${user.id}' readonly/><br/><br/>
    Name:<br/>
    <input type='text' name='name' value='${user.name}'/><br/><br/>
    Login:<br/>
    <input type='text' name='login' value='${user.login}'/><br/><br/>
    Password:<br/>
    <input type='password' name='password' value="${user.password}"/><br/><br/>
    Email:<br/>
    <input type='email' name='email' value='${user.email}'/><br/><br/>
    Create date:<br/>
    <input type='date' name='createDate' value='${user.createDate}'/><br/><br/>
    Role:<br/>
    <input type='text' name='role' value='${user.role.id}' readonly/><br/><br/>
    <input type='submit' value='EDIT'/>
</form>
<br/>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="To main page" />
</form>
</body>
</html>