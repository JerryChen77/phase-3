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
        <form action="${pageContext.request.contextPath}/param/getParam10" method="post">
            昵称:<input type="text" name="username">
            <br>

            <input type="submit" value="提交">
        </form>
    </body>
</html>
