<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ</title>
</head>
<body>
    <h1>Chào mừng bạn đến với trang chủ</h1>
    
    <!-- Nút Xem Sản Phẩm -->
    <a href="<%= request.getContextPath() %>/productList">
        <button type="button">Xem Sản Phẩm</button>
    </a>

</body>
</html>
