<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SlimeChan
  Date: 20.04.2019
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список ролей</title>
</head>
<body>

    <c:forEach items="${roles}" var="role">
        <a href="/admin/roles/${role.name}">${role.name}</a><br>
    </c:forEach>

</body>
</html>
