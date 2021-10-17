<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/19
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>Modify Hero</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">修改英雄</h1>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/hero/modifyHero" method="post">
                <div class="form-group">
                    <label class="col-sm-4 control-label" disabled>id</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="id" name="id" placeholder="请输入ID" value="${param.id}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">姓名</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">武力值</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="forceValue" name="forceValue" placeholder="武力值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">地址</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="addr" name="addr" placeholder="地址">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">信息</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="info" name="info" placeholder="描述信息">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-4">
                        <button type="submit" class="btn btn-primary">修改</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

<script type="text/javascript">
    $(function () {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/hero/heroes/${param.id}",
            contextType:"application/json",
            success:function (ret) {
                $("#name").val(ret.name);
                $("#forceValue").val(ret.forceValue);
                $("#addr").val(ret.addr);
                $("#info").val(ret.info);
            }
        })
    })
</script>
