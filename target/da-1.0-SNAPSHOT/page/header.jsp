<%@ page import="dataModel.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Sản phẩm</title>
</head>
<body>
    <div class="header">
        <ul>
            <a href="${pageContext.request.contextPath}/controller/profileServlet?action=viewProfile">Thông tin cá nhân</a>
<a href="${pageContext.request.contextPath}/controller/profileServlet?action=logout">Đăng xuất</a>

        </ul>
    </div>
    <h1>Chào mừng đến với trang sản phẩm</h1>
    <!-- Nội dung sản phẩm ở đây -->
</body>
</html>
