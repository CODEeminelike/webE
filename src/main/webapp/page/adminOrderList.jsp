<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách ??n Hàng</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #FF5722;
            font-size: 28px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #FF5722;
            color: #fff;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #FF5722;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }

        a:hover {
            background-color: #e64a19;
        }

        .container {
            max-width: 1100px;
            margin: 0 auto;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            table {
                font-size: 14px;
            }

            h1 {
                font-size: 24px;
            }

            a {
                font-size: 14px;
                padding: 8px 15px;
            }
        }

    </style>
</head>
<body>

    <div class="container">
        <h1>Danh Sách ??n Hàng</h1>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ngày</th>
                    <th>Th?i Gian</th>
                    <th>Email</th>
                    <th>??a Ch?</th>
                    <th>T?ng Ti?n</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${order}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.date}</td>
                        <td>${order.time}</td>
                        <td>${order.email}</td>
                        <td>${order.address}</td>
                        <td>${order.totalPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <a href="./page/adminDashboard.jsp">Quay l?i Dashboard</a>
    </div>

</body>
</html>
