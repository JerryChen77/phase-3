<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/15
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/checkCaptcha/check01" method="get">
            <img id="capImg" src="${pageContext.request.contextPath}/captcha" alt="" onclick="refresh()">
            <br>
            <input type="text" name="captcha">
            <br>

            <input type="submit" value="提交">
        </form>
    </body>
</html>

<script type="text/javascript">
    function refresh() {
        var capImg = document.getElementById("capImg");
        capImg.src = "${pageContext.request.contextPath}/captcha?" + new Date();
    }
</script>
