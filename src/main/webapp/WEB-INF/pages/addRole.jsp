<%--
  Created by IntelliJ IDEA.
  User: SlimeChan
  Date: 19.04.2019
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Конфигурация ролей</title>
</head>
<body>

    <form action="/admin/roles/add" method="post">
        <input type="text" name="name">
        <br>
        <input type="submit" value="Add">
    </form>

</body>
</html>
