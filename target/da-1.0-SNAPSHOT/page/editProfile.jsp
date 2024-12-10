<%@ page import="dataModel.Customer" %>
<%@ page import="dataDAO.CustomerDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/editProfile.css">
</head>
<body>
    <h1>Thông tin cá nhân</h1>

<%
    String email = (String) session.getAttribute("email");
    Customer customer = null;

    if (email != null) {
        CustomerDAO dao = new CustomerDAO();
        try {
            customer = dao.getCustomerByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            // Nếu có lỗi, có thể hiển thị thông báo lỗi
        }
    }

    if (customer != null) {
%>

<!-- Bảng hiển thị thông tin người dùng hiện tại -->


<form action="${pageContext.request.contextPath}/controller/profileServlet" method="post">
    <input type="hidden" name="action" value="updateProfile"/>

    <label for="fname">Tên:</label>
    <input type="text" id="fname" name="fname" value="<%= customer.getFname() %>" required/><br/>

    <label for="lname">Họ:</label>
    <input type="text" id="lname" name="lname" value="<%= customer.getLname() %>" required/><br/>

    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password" value="<%= customer.getPassword() %>" required/><br/>

    <label for="address">Địa chỉ:</label>
    <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required/><br/>

   
</form>

<%
    } else {
%>
    <p>Không tìm thấy thông tin khách hàng.</p>
<%
    }
%>


    <!-- Form để cập nhật thông tin -->
    <form action="${pageContext.request.contextPath}/controller/profileServlet" method="post">
    <input type="hidden" name="action" value="updateProfile"/>

    <label for="fname">Tên:</label>
    <input type="text" id="fname" name="fname" value="<%= customer != null ? customer.getFname() : "" %>" required/><br/>

    <label for="lname">Họ:</label>
    <input type="text" id="lname" name="lname" value="<%= customer != null ? customer.getLname() : "" %>" required/><br/>

    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password" value="<%= customer != null ? customer.getPassword() : "" %>" required/><br/>

    <label for="address">Địa chỉ:</label>
    <input type="text" id="address" name="address" value="<%= customer != null ? customer.getAddress() : "" %>" required/><br/>

    <input type="submit" value="Cập nhật"/>
    </form>

    <form action="${pageContext.request.contextPath}/controller/profileServlet" method="post">
        <input type="hidden" name="action" value="logout"/>
        <input type="submit" value="Đăng xuất"/>
    </form>
</body>
</html>
