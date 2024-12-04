<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hiển thị ảnh</title>
</head>
<body>
    <h1>Ảnh của sản phẩm</h1>
    
    <!-- Hiển thị ảnh từ đường dẫn /page/anh1.jpg -->
    <img src="<%= request.getContextPath() %>/page/1.jpg" alt="Ảnh sản phẩm" />

</body>
</html>
