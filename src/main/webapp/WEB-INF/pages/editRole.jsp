<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SlimeChan
  Date: 20.04.2019
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.slimechan.journal.server.models.users.Role" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<html>
<head>
    <title>Настройка прав</title>
</head>
<body>

    <form action="/admin/roles/${role.name}" method="post">
        <%
//            Role r = (Role) request.getAttribute("role");
//            int id = 0;
//            if(!r.getAuthorities().isEmpty()) {
//                for (GrantedAuthority a : r.getAuthorities()) {
//                    out.println("<input type='text' name='" + id + "' value='" + a.getAuthority() + "'><input type='button' value='-'><br>");
//                    id++;
//                }
//            }else{
//                out.println("<input type='button' value='+' onclick='add()'>");
//            }
//

        %>
        <input type="text" name="authority">
        <br>
        <input type="submit" value="Add">
    </form>
</body>
</html>
