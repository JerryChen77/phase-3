<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/12
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>param01</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/param/getParam01" method="post">
            id:<input type="text" name="id">
            <br>

            昵称:<input type="text" name="username">
            <br>

            密码:<input type="password" name="password">
            <br>

            <input type="submit" value="提交">
        </form>
    </body>
</html>
