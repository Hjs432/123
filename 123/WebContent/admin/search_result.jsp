<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>搜索结果 - 都市供求信息网</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="admin-header">
        <div>都市供求信息网 - 后台管理</div>
        <div><a href="logout">退出登录</a></div>
    </div>

    <div class="admin-nav">
        <ul>
            <li><a href="index">首页</a></li>
            <li><a href="user">用户管理</a></li>
            <li><a href="info?action=pending">信息审核</a></li>
            <li><a href="info?action=paid">付费设置</a></li>
        </ul>
        <form action="search" method="post" style="float: right; margin-right: 20px;">
            <input type="text" name="keyword" placeholder="搜索信息..." style="padding: 5px;" value="${keyword}">
            <button type="submit" style="padding: 5px 10px;">搜索</button>
        </form>
    </div>

    <div class="admin-content">
        <h3>搜索结果: "${keyword}"</h3>
        <c:if test="${infos != null && !infos.isEmpty()}">
            <table class="data-table">
                <tr>
                    <th>ID</th>
                    <th>标题</th>
                    <th>类别</th>
                    <th>发布时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${infos}" var="info">
                    <tr>
                        <td>${info.id}</td>
                        <td><a href="info?action=detail&id=${info.id}">${info.title}</a></td>
                        <td>${info.categoryName}</td>
                        <td>${info.publishTime}</td>
                        <td class="${info.status == 0 ? 'status-pending' : (info.status == 1 ? 'status-approved' : 'status-rejected')}">
                            ${info.status == 0 ? '待审核' : (info.status == 1 ? '已通过' : '已拒绝')}
                        </td>
                        <td class="action-links">
                            <c:if test="${info.status == 0}">
                                <a href="info?action=approve&id=${info.id}" class="approve">审核通过</a>
                                <a href="info?action=reject&id=${info.id}" class="reject">审核拒绝</a>
                            </c:if>
                            <a href="info?action=delete&id=${info.id}" class="delete">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${infos == null || infos.isEmpty()}">
            <p>未找到相关信息</p>
        </c:if>
    </div>
</body>
</html>