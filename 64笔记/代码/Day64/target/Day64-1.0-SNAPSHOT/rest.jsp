<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/15
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    </head>
    <body>
        <button onclick="getUsers()">查询所有</button>
        <button onclick="putUsers()">修改用户</button>
    </body>
</html>

<script type="text/javascript">
    // 接送服务端发来的json数据
    function getUsers() {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/rest/users",
            contentType:"application/json",
            success:function (ret) {
                console.log(ret);
            }
        })
    }
    // 接送服务端发来的json数据
    function putUsers() {
        $.ajax({
            type:"put",
            url:"${pageContext.request.contextPath}/rest/users",
            contentType:"application/json",
            success:function (ret) {
                console.log(ret);
            }
        })
    }
</script>
