<%@ page import="appUtil.PBKDF2Util" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Mã hóa mật khẩu với PBKDF2</title>
</head>
<body>
    <h2>Mã hóa mật khẩu với PBKDF2</h2>

    <!-- Form để nhập mật khẩu -->
    <form method="post">
        <label for="password">Nhập mật khẩu:</label>
        <input type="password" id="password" name="password" required />
        <br>
        <input type="submit" value="Mã hóa" />
    </form>

    <%
        // Lấy mật khẩu từ form
        String password = request.getParameter("password");
        if (password != null && !password.isEmpty()) {
            try {
                // Gọi phương thức hashPassword của PBKDF2Util để mã hóa mật khẩu
                String hashedPassword = PBKDF2Util.hashPassword(password);

                // Hiển thị mật khẩu đã mã hóa
                out.println("<h3>Mật khẩu đã mã hóa:</h3>");
                out.println("<p>" + hashedPassword + "</p>");
            } catch (Exception e) {
                // Xử lý lỗi
                out.println("<p>Đã xảy ra lỗi khi mã hóa mật khẩu: " + e.getMessage() + "</p>");
            }
        }
    %>

</body>
</html>
