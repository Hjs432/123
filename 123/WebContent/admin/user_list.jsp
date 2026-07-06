<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理 - 都市供求信息网</title>
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
    </div>

    <div class="admin-content">
        <h3>用户管理</h3>
        <c:if test="${users != null && !users.isEmpty()}">
            <table class="data-table">
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>注册时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>${user.createTime}</td>
                        <td>${user.status == 1 ? '正常' : '禁用'}</td>
                        <td class="action-links">
                            <c:if test="${user.status == 1}">
                                <a href="user?action=disable&id=${user.id}" class="reject">禁用</a>
                            </c:if>
                            <c:if test="${user.status == 0}">
                                <a href="user?action=enable&id=${user.id}" class="approve">启用</a>
                            </c:if>
                            <a href="user?action=delete&id=${user.id}" class="delete">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${users == null || users.isEmpty()}">
            <p>暂无用户</p>
        </c:if>
    </div>
</body>
</html>