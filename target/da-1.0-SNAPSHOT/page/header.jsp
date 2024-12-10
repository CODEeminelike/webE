<%@ page import="dataDAO.OrdersDAO" %>
<%@ page import="dataModel.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>

<html>
<head>
    <title>L?ch s? ??n hàng</title>
</head>
<body>
    <h1>L?ch s? ??n hàng</h1>
    
    <%
        // L?y email t? session (Gi? s? email này ?ã ???c l?u trong session khi ng??i dùng ??ng nh?p)
        String email = (String) session.getAttribute("email");

        // Ki?m tra xem email có t?n t?i trong session không
        if (email != null && !email.isEmpty()) {
            try {
                // T?o ??i t??ng OrdersDAO ?? g?i ph??ng th?c getOrderByEmail
                OrdersDAO ordersDAO = new OrdersDAO();
                List<Orders> ordersList = ordersDAO.getOrderByEmail(email); // L?y danh sách ??n hàng t? email

                // Ki?m tra n?u có ??n hàng
                if (ordersList.isEmpty()) {
                    out.println("<p>Không có ??n hàng nào ???c tìm th?y.</p>");
                } else {
                    // Hi?n th? danh sách ??n hàng trong b?ng
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>ID</th>");
                    out.println("<th>Ngày</th>");
                    out.println("<th>Gi?</th>");
                    out.println("<th>Email</th>");
                    out.println("<th>??a ch?</th>");
                    out.println("<th>T?ng giá tr?</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");

                    // L?p qua danh sách ??n hàng và hi?n th? thông tin c?a m?i ??n hàng
                    for (Orders order : ordersList) {
                        out.println("<tr>");
                        out.println("<td>" + order.getId() + "</td>");
                        out.println("<td>" + order.getDate() + "</td>");
                        out.println("<td>" + order.getTime() + "</td>");
                        out.println("<td>" + order.getEmail() + "</td>");
                        out.println("<td>" + order.getAddress() + "</td>");
                        out.println("<td>" + order.getTotalPrice() + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</tbody>");
                    out.println("</table>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<p>?ã x?y ra l?i khi truy v?n d? li?u: " + e.getMessage() + "</p>");
            }
        } else {
            out.println("<p>Vui lòng ??ng nh?p tr??c khi xem l?ch s? ??n hàng.</p>");
        }
    %>

    <br>
    <a href="index.jsp">Tr? v? trang ch?</a>
</body>
</html>
