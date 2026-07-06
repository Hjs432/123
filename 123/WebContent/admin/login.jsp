<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理登录 - 都市供求信息网</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="login-form">
        <h2>都市供求信息网 - 后台管理</h2>
        <c:if test="${error != null}">
            <div class="error">${error}</div>
        </c:if>
        <form action="login" method="post">
            <div class="form-group">
                <label>用户名:</label>
                <input type="text" name="username" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label>密码:</label>
                <input type="password" name="password" placeholder="请输入密码">
            </div>
            <div class="form-actions">
                <button type="submit" class="btn-primary">登录</button>
            </div>
        </form>
        <p style="text-align: center; margin-top: 15px; font-size: 12px; color: #888;">
            默认用户名: admin 密码: 123456
        </p>
    </div>
</body>
</html>