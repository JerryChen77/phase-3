<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/13
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <p>id: ${sessionScope.user.id}</p>
        <p>username: ${sessionScope.user.username}</p>
        <p>password: ${sessionScope.user.password}</p>
        <p>registerTime: ${sessionScope.user.registerTime}</p>
    </body>
</html>
