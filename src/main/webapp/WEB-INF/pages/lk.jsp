<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css">
        <title>Профиль</title>
    </head>
    <body>
        <h1>Информация о пользователе ${user.name}</h1>
        <h2>ФИО ${user.fio}</h2>
        <h2>Номер студенческого билета ${user.id}</h2>
        <h2>Группа ${user.group}</h2>
        <c:choose>
            <c:when test="${user.hasRole('ADMIN')=='true'}">
                <textarea placeholder="Добавление новостей" style="width: 40%; height: 20%;"></textarea>
            </c:when>
        </c:choose>

        <div class="top-bar-flex">
            <a class="top-bar-button"></a>
        </div>

    </body>
</html>