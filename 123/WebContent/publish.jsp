<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布信息 - 都市供求信息网</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="header">
        <div class="header-top">
            <a href="publish">发布信息</a>
            <a href="admin/login">进入后台</a>
            <span>收藏本页 - 联系我们</span>
        </div>
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="index" class="logo">都市供求信息网 DOUSHI GONG QIU XIN XI</a>
        </div>
    </div>

    <div class="nav">
        <ul>
            <li><a href="index">首页</a></li>
            <li><a href="category?id=10">寻求启示</a></li>
            <li><a href="category?id=1">招聘信息</a></li>
            <li><a href="category?id=2">求职信息</a></li>
            <li><a href="category?id=3">培训信息</a></li>
            <li><a href="category?id=4">房屋信息</a></li>
            <li><a href="category?id=7">招商引资</a></li>
            <li><a href="category?id=8">公寓信息</a></li>
            <li><a href="category?id=9">车辆信息</a></li>
            <li><a href="category?id=5">求购信息</a></li>
            <li><a href="category?id=6">出售信息</a></li>
            <li><a href="category?id=11">家教信息</a></li>
            <li><a href="category?id=12">其他</a></li>
        </ul>
    </div>

    <div class="banner">
        <div>都市供求信息供与求 积聚于此看勿忧...</div>
        <div class="banner-links">
            <a href="category?id=1">招聘</a>
            <a href="category?id=7">招商</a>
            <a href="category?id=2">求职</a>
        </div>
    </div>

    <div class="container">
        <div class="sidebar">
            <div class="calendar">
                <h3 class="section-title">日历</h3>
                <div class="calendar-header">
                    <span><</span>
                    <span>八月 2023</span>
                    <span>></span>
                </div>
                <table class="calendar-table">
                    <tr><th>日</th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr>
                    <tr><td>30</td><td>31</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td></tr>
                    <tr><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td></tr>
                    <tr><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td></tr>
                    <tr><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td></tr>
                    <tr><td>27</td><td><strong>28</strong></td><td>29</td><td>30</td><td>31</td><td>1</td><td>2</td></tr>
                </table>
            </div>

            <div>
                <h3 class="section-title">信息快速搜索</h3>
                <form action="search" method="post" class="search-form">
                    <label>关键字:</label>
                    <input type="text" name="keyword" placeholder="请输入关键字">
                    <label>条件:</label>
                    <select name="condition">
                        <option value="title">标题</option>
                        <option value="content">内容</option>
                    </select>
                    <label>搜索类型:</label>
                    <div style="display: flex; gap: 10px;">
                        <label><input type="radio" name="searchType" value="full"> 全字匹配</label>
                        <label><input type="radio" name="searchType" value="fuzzy" checked> 模糊搜索</label>
                    </div>
                    <button type="submit">搜索</button>
                </form>
            </div>
        </div>

        <div class="main-content">
            <h3 class="section-title">发布信息</h3>
            <c:if test="${error != null}">
                <div style="color: #e74c3c; margin-bottom: 15px;">${error}</div>
            </c:if>
            <form action="publish" method="post">
                <div class="form-group">
                    <label>信息类别:</label>
                    <select name="categoryId">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>信息标题:</label>
                    <input type="text" name="title" maxlength="40" placeholder="信息标题最多不得超过40个字符">
                    <div class="hint">[信息标题最多不得超过40个字符]</div>
                </div>
                <div class="form-group">
                    <label>信息内容:</label>
                    <textarea name="content" maxlength="500" placeholder="信息内容最多不得超过500个字符"></textarea>
                    <div class="hint">[信息内容最多不得超过500个字符]</div>
                </div>
                <div class="form-group">
                    <label>联系电话:</label>
                    <input type="text" name="contactPhone" placeholder="多个电话以逗号分隔">
                    <div class="hint">[多个电话以逗号分隔!]</div>
                </div>
                <div class="form-group">
                    <label>联系人:</label>
                    <input type="text" name="contactName">
                </div>
                <div class="form-group">
                    <label>E-mail:</label>
                    <input type="text" name="contactEmail">
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn-primary">发布</button>
                    <button type="reset" class="btn-secondary">重填</button>
                </div>
            </form>
        </div>
    </div>

    <div class="footer">
        <p>都市供求信息网 版权所有</p>
    </div>
</body>
</html>