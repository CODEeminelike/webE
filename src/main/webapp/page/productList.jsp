<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách sản phẩm</title>
    <!-- Thêm liên kết tới file CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/productList.css">

</head>
<body>
     <!-- Link đến trang thông tin cá nhân và đăng xuất -->
    <div class="header">
        <ul>
            <li><a href="${pageContext.request.contextPath}/page/editProfile.jsp">Thông tin cá nhân</a></li>
            <li><a href="${pageContext.request.contextPath}/page/logout.jsp">Đăng xuất</a></li>
            <li><a href="${pageContext.request.contextPath}/page/Cart2.jsp">Giỏ Hàng</a></li>
        </ul>
    </div>
    <h1>Danh sách sản phẩm</h1>
    
    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty message}">
        <div style="color: red; font-weight: bold;">
            ${message}
        </div>
    </c:if>
    
     <!-- Hiển thị thông báo thể loại lọc -->
    <c:if test="${not empty categoryMessage}">
        <p><strong>${categoryMessage}</strong></p>
    </c:if>
    
    <!-- Form để lọc sản phẩm theo category -->
    <form action="<%= request.getContextPath() %>/productList" method="get">
        <label for="category">Chọn thể loại:</label>
        <select name="category" id="category">
            <option value="Tất cả" <c:if test="${category == 'Tất cả'}">selected</c:if>>Tất cả</option>
            <option value="Thể thao" <c:if test="${category == 'Thể thao'}">selected</c:if>>Thể thao</option>
            <option value="Cao gót" <c:if test="${category == 'Cao gót'}">selected</c:if>>Cao gót</option>
            <option value="Sandal" <c:if test="${category == 'Sandal'}">selected</c:if>>Sandal</option>
        </select>
        <button type="submit">Lọc</button>
    </form>

    <br>

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
            <!-- Duyệt qua danh sách sản phẩm và hiển thị -->
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td><img src="${product.linkImage}" alt="${product.name}" width="100" height="100"/></td>
                    <td>${product.price}</td>
                    <!-- Thêm vào giỏ hàng -->
                    <td>
                        <!-- Form để gửi ID sản phẩm tới servlet -->
                        <form action="${pageContext.request.contextPath}/cartServlet" method="get"> 
                            <input type="hidden" name="productId" value="${product.id}"/>
                            <button type="submit">Thêm vào giỏ</button>
                        </form>
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>