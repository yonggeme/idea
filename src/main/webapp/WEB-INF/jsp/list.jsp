<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>列表</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <table class="table table-hover table-condensed table-bordered">
        <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>库存</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${seckillList}" var="s">
                <tr>
                    <td>${s.seckillId}</td>
                    <td>${s.name}</td>
                    <td>${s.number}</td>
                    <td><fmt:formatDate value="${s.startTime}" type="date" dateStyle="full"/> </td>
                    <td><fmt:formatDate value="${s.endTime}" type="date" dateStyle="full"/> </td>
                    <td><fmt:formatDate value="${s.createTime}" type="date" dateStyle="full"/> </td>
                    <td><button type="button" class="btn btn-info">商品详情页</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</html>
