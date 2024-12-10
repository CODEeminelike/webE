

<%@ page import="dataModel.Orders" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>L?ch s? ??n hàng</title>
</head>
<body>
    <h1>L?ch s? ??n hàng</h1>

    <%
        // L?y danh sách ??n hàng t? request
        List<Orders> ordersList = (List<Orders>) request.getAttribute("ordersList");
        String message = (String) request.getAttribute("message");

        // Hi?n th? thông báo n?u có
        if (message != null) {
            out.println("<p>" + message + "</p>");
        }

        // N?u có ??n hàng, hi?n th? b?ng
        if (ordersList != null && !ordersList.isEmpty()) {
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

            // L?p qua danh sách ??n hàng và hi?n th? thông tin
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
        } else {
            out.println("<p>Không có ??n hàng nào ???c tìm th?y.</p>");
        }
    %>

    <br>
    <a href="index.jsp">Tr? v? trang ch?</a>
</body>
</html>
