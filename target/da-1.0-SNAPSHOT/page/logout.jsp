<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng xuất</title>
</head>
<body>
    <%
        // Xử lý đăng xuất
        session.invalidate();  // Hủy session để người dùng được đăng xuất
        response.sendRedirect(request.getContextPath() + "/page/login.jsp");  // Chuyển hướng về trang đăng nhập
    %>
</body>
</html>
