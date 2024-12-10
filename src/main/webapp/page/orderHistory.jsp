

<%@ page import="dataModel.Orders" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>L?ch s? ??n h�ng</title>
</head>
<body>
    <h1>L?ch s? ??n h�ng</h1>

    <%
        // L?y danh s�ch ??n h�ng t? request
        List<Orders> ordersList = (List<Orders>) request.getAttribute("ordersList");
        String message = (String) request.getAttribute("message");

        // Hi?n th? th�ng b�o n?u c�
        if (message != null) {
            out.println("<p>" + message + "</p>");
        }

        // N?u c� ??n h�ng, hi?n th? b?ng
        if (ordersList != null && !ordersList.isEmpty()) {
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

            // L?p qua danh s�ch ??n h�ng v� hi?n th? th�ng tin
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
            out.println("<p>Kh�ng c� ??n h�ng n�o ???c t�m th?y.</p>");
        }
    %>

    <br>
    <a href="index.jsp">Tr? v? trang ch?</a>
</body>
</html>
