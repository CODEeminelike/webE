<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách sản phẩm</title>
</head>
<body>
    <h1>Danh sách sản phẩm</h1>

    <!-- Hiển thị thông báo thể loại lọc -->
    <c:if test="${not empty categoryMessage}">
        <p><strong>${categoryMessage}</strong></p>
    </c:if>

    <!-- Hiển thị thông báo nếu không có sản phẩm -->
    <c:if test="${not empty message}">
        <p><strong>${message}</strong></p>
    </c:if>

    <!-- Danh sách sản phẩm sẽ được hiển thị ở đây -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Ảnh sản phẩm</th>
                <th>Giá</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td><img src="${product.linkImage}" alt="${product.name}" width="100" height="100"/></td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
