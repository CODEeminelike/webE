<%@ page import="dataModel.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/editProfile.css">

</head>
<body>
    <h1>Thông tin cá nhân</h1>

    <!-- Hiển thị thông báo thành công nếu có -->
<!--    <c:if test="${not empty updateSuccess}">
        <p style="color: green;">Cập nhật thông tin thành công!</p>
    </c:if>

     Hiển thị thông báo lỗi nếu có 
    <c:if test="${not empty updateError}">
        <p style="color: red;">Có lỗi xảy ra khi cập nhật thông tin!</p>
    </c:if>-->

    <!-- Form để cập nhật thông tin -->
    <form action="${pageContext.request.contextPath}/controller/profileServlet" method="post">
        <input type="hidden" name="action" value="updateProfile"/>

        <label for="fname">Tên:</label>
        <input type="text" id="fname" name="fname" value="${customer.fname}" required/><br/>

        <label for="lname">Họ:</label>
        <input type="text" id="lname" name="lname" value="${customer.lname}" required/><br/>

        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" value="${customer.password}" required/><br/>

        <input type="submit" value="Cập nhật"/>
    </form>

    <form action="${pageContext.request.contextPath}/controller/profileServlet" method="post">
        <input type="hidden" name="action" value="logout"/>
        <input type="submit" value="Đăng xuất"/>
    </form>
</body>
</html>
