<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/14
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    </head>
    <body>
        <button onclick="getJson()">获取user</button>
        <button onclick="getJson2()">获取user2</button>
        <button onclick="getJson3()">获取user3</button>
        <button onclick="sendJson()">发送user</button>
    </body>

    <script type="text/javascript">
        // 接送服务端发来的json数据
        function getJson() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json01/getJson01",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
        // 接送服务端发来的json数据
        function getJson2() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json01/getJson03",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
        // 接送服务端发来的json数据
        function getJson3() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json01/getJson04",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }

        // 客户端向服务端发送json数据
        function sendJson() {
            var user = {id:100111,userName:"李逵",password:"xiaokui",registerTime:Date};
            var jsonUser = JSON.stringify(user);
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/json01/getJson02",
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
