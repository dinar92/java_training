<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head> 
    <title>Create new user</title> 
    </head> 
<body> 
<h1>Create new user</h1> 
<form action='<%=request.getContextPath()%>/' method='post'>
    <input type='hidden' name='action' value='add' /> 
    ID:<br /> 
    <input type='text' name='id' /><br /><br /> 
    Name:<br /> 
    <input type='text' name='name' /><br /><br /> 
    Login:<br /> 
    <input type='text' name='login' /><br /><br /> 
    Email:<br /> 
    <input type='email' name='email' /><br /><br /> 
    Create date:<br /> 
    <input type='date' name='createDate' /><br /><br /> 
    <input type='submit' value='CREATE' /> 
    </form> 
</body> 
</html>
