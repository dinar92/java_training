<%@ page import="ru.job4j.tracker.ValidateService" %>
<%@ page import="ru.job4j.tracker.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head> 
    <title>Create new user</title> 
    </head> 
<body> 
<h1>Edit user's data</h1> 
<form action='<%=request.getContextPath()%>/' method='post'>
    <% User user = ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id")));%>
    <input type='hidden' name='action' value='update' /> 
    ID:<br /> 
    <input type='text' name='id' value='<%=user.getId()%>' readonly /><br /><br />
    Name:<br /> 
    <input type='text' name='name' value='<%=user.getName()%>' /><br /><br />
    Login:<br /> 
    <input type='text' name='login' value='<%=user.getLogin()%>' /><br /><br />
    Email:<br />
    <input type='email' name='email' value='<%=user.getEmail()%>' /><br /><br />
    Create date:<br /> 
    <input type='date' name='createDate' value='<%=user.getCreateDate()%>' /><br /><br />
    <input type='submit' value='EDIT' /> 
    </form> 
</body> 
</html>