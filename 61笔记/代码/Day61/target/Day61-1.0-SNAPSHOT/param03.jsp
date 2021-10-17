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
        <form action="${pageContext.request.contextPath}/param/getParam05">
            爱好
            <br>
            篮球:<input type="checkbox" name="hobby" value="basketball">
            足球:<input type="checkbox" name="hobby" value="football">
            乒乓:<input type="checkbox" name="hobby" value="pingpang">
            <input type="submit" value="提交">
        </form>
    </body>
</html>
