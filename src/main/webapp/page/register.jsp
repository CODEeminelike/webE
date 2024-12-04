<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
</head>
<body>
    <h1>Đăng Ký Tài Khoản Mới</h1>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty requestScope.error}">
        <div style="color: red;">
            <strong>${requestScope.error}</strong>
        </div>
    </c:if>

    <form action="<%= request.getContextPath() %>/customerServlet" method="post">
        <input type="hidden" name="action" value="register" />
        
        <label for="fname">Họ:</label>
        <input type="text" id="fname" name="fname" required /><br /><br />

        <label for="lname">Tên:</label>
        <input type="text" id="lname" name="lname" required /><br /><br />
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required /><br /><br />
        
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required /><br /><br />

        <label for="confirmPassword">Xác nhận mật khẩu:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required /><br /><br />
        
        <button type="submit">Đăng Ký</button>
    </form>

    <p>Đã có tài khoản? <a href="<%= request.getContextPath() %>/customerServlet?action=login">Đăng nhập ngay</a></p>
</body>
</html>
