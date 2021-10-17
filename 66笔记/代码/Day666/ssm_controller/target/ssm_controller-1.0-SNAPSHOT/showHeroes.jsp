<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/16
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>Hello Hero</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">英雄榜</h1>
            <a href="${pageContext.request.contextPath}/saveHero.jsp" class="btn btn-primary" style="float: right">添加</a>
            <table class="table table-hover">
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>武力值</th>
                    <th>地址</th>
                    <th>信息</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${heroes}" var="hero">
                    <tr id="${hero.id}">
                        <td>${hero.id}</td>
                        <td>${hero.name}</td>
                        <td>${hero.forceValue}</td>
                        <td>${hero.addr}</td>
                        <td>${hero.info}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/modifyHero.jsp?id=${hero.id}" class="btn btn-primary">修改</a>
                            <button type="button" class="btn btn-danger" onclick="delHero(${hero.id})">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <nav aria-label="Page navigation" style="text-align: center">
                <ul class="pagination">
                    <li>共${pageInfo.pages}页</li>

                    <c:if test="${pageInfo.pageNum!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/hero/heroes?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                        <c:forEach begin="1" end="${pageInfo.pages}" var="pageNo">
                            <li><a href="${pageContext.request.contextPath}/hero/heroes?pageNum=${pageNo}">${pageNo}</a></li>
                        </c:forEach>
                    <c:if test="${pageInfo.pageNum!=pageInfo.pages}">
                        <li>
                            <a href="${pageContext.request.contextPath}/hero/heroes?pageNum=${pageInfo.pageNum+1}" aria-label="Previous">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <li>当前第${pageInfo.pageNum}页</li>
                </ul>
            </nav>
        </div>
    </body>
</html>

<script type="text/javascript">
    function delHero(id) {

        var ret = confirm("确定删除吗?");
        if (ret){
            $.ajax({
                type:"delete",
                url:"${pageContext.request.contextPath}/hero/heroes/" + id,
                contentType:"application/json",
                success:function (ret) {
                    if (ret=="success"){
                        $("#"+id).remove();
                        alert("删除成功");
                    } else{
                        alert("删除失败")
                    }
                }
            })
        } else{
            alert("取消操作");
        }
    }
</script>
