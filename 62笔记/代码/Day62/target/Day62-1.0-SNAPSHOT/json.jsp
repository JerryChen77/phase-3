<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/13
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
    </head>
    <body>
        <button onclick="getJson()">获取user</button>
        <button onclick="sendJson()">发送user</button>
    </body>

    <script type="text/javascript">

        // 接送服务端发来的json数据
        function getJson() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json/getJson01",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }

        // 客户端向服务端发送json数据
        function sendJson() {
            var user = {id:100111,username:"李逵",password:"xiaokui",registerTime:Date};
            var jsonUser = JSON.stringify(user);
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/json/getJson04",
                data:jsonUser,
                contentType:"application/json",
                dataType:"json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }

    </script>

</html>
