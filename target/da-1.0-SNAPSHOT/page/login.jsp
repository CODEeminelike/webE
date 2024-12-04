<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
</head>
<body>
    <h1>Đăng Nhập</h1>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty requestScope.error}">
        <div style="color: red;">
            <strong>${requestScope.error}</strong>
        </div>
    </c:if>

    <form action="<%= request.getContextPath() %>/customerServlet" method="post">
        <input type="hidden" name="action" value="login" />
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required /><br /><br />
        
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required /><br /><br />
        
        <button type="submit">Đăng Nhập</button>
    </form>

    <p>Chưa có tài khoản? <a href="<%= request.getContextPath() %>/customerServlet?action=register">Đăng ký ngay</a></p>
</body>
</html>
