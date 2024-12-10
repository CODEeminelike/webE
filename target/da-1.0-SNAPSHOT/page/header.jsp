<%@ page import="dataDAO.OrdersDAO" %>
<%@ page import="dataModel.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>

<html>
<head>
    <title>L?ch s? ??n h�ng</title>
</head>
<body>
    <h1>L?ch s? ??n h�ng</h1>
    
    <%
        // L?y email t? session (Gi? s? email n�y ?� ???c l?u trong session khi ng??i d�ng ??ng nh?p)
        String email = (String) session.getAttribute("email");

        // Ki?m tra xem email c� t?n t?i trong session kh�ng
        if (email != null && !email.isEmpty()) {
            try {
                // T?o ??i t??ng OrdersDAO ?? g?i ph??ng th?c getOrderByEmail
                OrdersDAO ordersDAO = new OrdersDAO();
                List<Orders> ordersList = ordersDAO.getOrderByEmail(email); // L?y danh s�ch ??n h�ng t? email

                // Ki?m tra n?u c� ??n h�ng
                if (ordersList.isEmpty()) {
                    out.println("<p>Kh�ng c� ??n h�ng n�o ???c t�m th?y.</p>");
                } else {
                    // Hi?n th? danh s�ch ??n h�ng trong b?ng
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>ID</th>");
                    out.println("<th>Ng�y</th>");
                    out.println("<th>Gi?</th>");
                    out.println("<th>Email</th>");
                    out.println("<th>??a ch?</th>");
                    out.println("<th>T?ng gi� tr?</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");

                    // L?p qua danh s�ch ??n h�ng v� hi?n th? th�ng tin c?a m?i ??n h�ng
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
                out.println("<p>?� x?y ra l?i khi truy v?n d? li?u: " + e.getMessage() + "</p>");
            }
        } else {
            out.println("<p>Vui l�ng ??ng nh?p tr??c khi xem l?ch s? ??n h�ng.</p>");
        }
    %>

    <br>
    <a href="index.jsp">Tr? v? trang ch?</a>
</body>
</html>
