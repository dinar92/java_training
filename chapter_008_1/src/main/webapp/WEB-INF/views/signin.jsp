<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Signin</title>
</head>
<body>
<c:if test="${requestScope.error != null}">
    <div style="background-color: red">
   <c:out value="${requestScope.error}" />
    </div>
    </br>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login:
    <input type="text" name="login"/>
    Password:
    <input type="password" name="password"/>
    <input type="submit" value="Signin"/>
</form>
</body>
</html>
