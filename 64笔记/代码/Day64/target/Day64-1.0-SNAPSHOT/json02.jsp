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
                url:"${pageContext.request.contextPath}/json02/getJson01",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
    </script>

</html>
