<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dataModel.Product" %>
<%@ page import="dataDAO.ProductDAO" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách sản phẩm theo thể loại</title>
</head>
<body>
    <h1>Danh sách sản phẩm theo thể loại</h1>

    <!-- Form để lọc sản phẩm theo category -->
    <form method="get" action="">
        <label for="category">Chọn thể loại:</label>
        <select name="category" id="category">
            <option value="Tất cả">Tất cả</option>
            <option value="Thể thao">Thể thao</option>
            <option value="Cao gót">Cao gót</option>
            <option value="Sandal">Sandal</option>
        </select>
        <button type="submit">Lọc</button>
    </form>

    <% 
        // Lấy giá trị category từ tham số URL (form submit)
        String category = request.getParameter("category");
        category = "TTT";
        if (category == null || category.trim().isEmpty()) {
            category = "Tất cả";  // Nếu không có category, mặc định là "Tất cả"
        }
        
        // Gọi phương thức getProductsByCategory từ ProductDAO để lấy danh sách sản phẩm
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getProductsByCategory(category);
    %>

    <!-- Hiển thị thông báo thể loại lọc -->
    <p><strong>Thể loại lọc: </strong>${category}</p>

    <!-- Kiểm tra nếu không có sản phẩm trong danh sách -->
    <c:if test="${empty productList}">
        <p style="color: red;">Không có sản phẩm nào cho thể loại này.</p>
    </c:if>

    <!-- Hiển thị danh sách sản phẩm nếu có -->
    <c:if test="${not empty productList}">
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
    </c:if>
</body>
</html>
