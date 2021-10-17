<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/12
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/param/getParam07">
            id:<input type="text" name="users[0].id">
            <br>
            昵称:<input type="text" name="users[0].username">
            <br>
            密码:<input type="password" name="users[0].password">
            <br>
            生日:<input type="text" name="users[0].registerTime">
            <br>

            <hr>

            id:<input type="text" name="users[1].id">
            <br>
            昵称:<input type="text" name="users[1].username">
            <br>
            密码:<input type="password" name="users[1].password">
            <br>
            生日:<input type="text" name="users[1].registerTime">
            <br>
            <input type="submit" value="提交">
        </form>
    </body>
</html>
