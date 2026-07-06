<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>信息详情 - 都市供求信息网</title>
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
        <h3>信息详情</h3>
        <c:if test="${info != null}">
            <div class="info-detail">
                <div class="meta">
                    信息类别: ${info.categoryName}<br>
                    发布时间: ${info.publishTime}<br>
                    状态: ${info.status == 0 ? '待审核' : (info.status == 1 ? '已通过' : '已拒绝')}<br>
                    付费: ${info.isPaid == 1 ? '是' : '否'}
                </div>
                <h2>${info.title}</h2>
                <div class="content">
                    ${info.content}
                </div>
                <div class="contact">
                    <strong>联系电话:</strong> ${info.contactPhone}<br>
                    <strong>联系人:</strong> ${info.contactName}<br>
                    <strong>E-mail:</strong> ${info.contactEmail}
                </div>
            </div>
        </c:if>
        <div style="margin-top: 20px;">
            <a href="index">返回列表</a>
        </div>
    </div>
</body>
</html>