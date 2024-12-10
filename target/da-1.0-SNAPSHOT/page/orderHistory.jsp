<%@ page import="dataModel.Orders" %>
<%@ page import="java.util.List" %>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>L?ch S? ??n Hàng</title>

    <style>
        /* T?ng th? trang */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8;
            color: #333;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
            color: #4A90E2;
            font-size: 36px;
        }

        p {
            text-align: center;
            font-size: 18px;
            color: #d9534f;
        }

        /* B?ng ??n hàng */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        table thead {
            background-color: #4A90E2;
            color: white;
        }

        table th, table td {
            padding: 12px 15px;
            text-align: center;
            border: 1px solid #ddd;
        }

        table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tbody tr:hover {
            background-color: #f1f1f1;
            transform: scale(1.02);
            transition: transform 0.3s ease-in-out;
        }

        table th {
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
        }

        table td {
            font-size: 14px;
        }

        /* Nút tr? v? */
        a {
            display: block;
            width: 180px;
            margin: 30px auto;
            padding: 10px 0;
            text-align: center;
            background-color: #4A90E2;
            color: white;
            text-decoration: none;
            border-radius: 25px;
            font-size: 18px;
            font-weight: bold;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        a:hover {
            background-color: #357ABD;
            transform: scale(1.05);
        }

        /* Thông báo l?i */
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            padding: 15px;
            margin: 20px auto;
            width: 80%;
            border-radius: 5px;
            text-align: center;
            font-size: 18px;
        }
    </style>
</head>

<body>
    <h1>L?ch S? ??n Hàng</h1>

    <%
        // L?y danh sách ??n hàng t? request
        List<Orders> ordersList = (List<Orders>) request.getAttribute("ordersList");
        String message = (String) request.getAttribute("message");

        // Hi?n th? thông báo n?u có
        if (message != null) {
            out.println("<div class='error-message'>" + message + "</div>");
        }

        // N?u có ??n hàng, hi?n th? b?ng
        if (ordersList != null && !ordersList.isEmpty()) {
            out.println("<table>");
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

    <a href="./page/productList.jsp">Tr? v? trang ch?</a>
</body>
</html>
