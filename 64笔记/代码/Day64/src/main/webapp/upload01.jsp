<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/14
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%--  必须设置enctype的类型 ,method必须是post --%>
        <form action="${pageContext.request.contextPath}/upload01/up01" enctype="multipart/form-data" method="post">
            选择文件:
            <input type="file" name="multipartFile" />
            <br>

            <input type="submit" value="上传">
        </form>
    </body>
</html>
