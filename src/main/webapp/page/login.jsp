<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #e9e4f0, #d3cce3);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            color: #333;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        form {
             background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 600px; /* Tăng width lên gấp đôi */
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button[type="submit"] {
            background-color: #673ab7;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #9c27b0;
        }

        .error {
            color: red;
            margin-bottom: 15px;
        }

        p {
            margin-top: 20px;
        }

        a {
            color: #673ab7;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Đăng Nhập</h1>

    <c:if test="${not empty requestScope.error}">
        <div class="error">
            <strong>${requestScope.error}</strong>
        </div>
    </c:if>

        <form action="<%= request.getContextPath() %>/customerServlet" method="post">
            <input type="hidden" name="action" value="login">
            <label>Email: </label>
            <input type="email" name="email" required>
            <label>Password: </label>
            <input type="password" name="password" required>
            <button type="submit">Login</button>
    </form>

    <p>Chưa có tài khoản? <a href="<%= request.getContextPath() %>/customerServlet?action=register">Đăng ký ngay</a></p>
</body>
</html>