<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #e9e4f0, #d3cce3); /* Gradient nền */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            color: #333;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
        }

        a {
            text-decoration: none; /* Loại bỏ gạch chân mặc định của thẻ a */
        }

        button {
            background-color: #673ab7; /* Màu tím */
            color: white;
            padding: 12px 20px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease; /* Hiệu ứng chuyển đổi mượt mà */
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); /* Hiệu ứng đổ bóng */
        }

        button:hover {
            background-color: #9c27b0; /* Màu tím đậm hơn khi hover */
            transform: translateY(-2px); /* Nâng nút lên khi hover */
        }
    </style>
</head>
<body>
    <h1>Chào mừng bạn đến với trang chủ</h1>

    <a href="<%= request.getContextPath() %>/customerServlet?action=login">
        <button type="button">Đăng Nhập</button>
    </a>

    <a href="<%= request.getContextPath() %>/customerServlet?action=register">
        <button type="button">Đăng Ký</button>
    </a>

</body>
</html>