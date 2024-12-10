<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Ng??i Dùng</title>
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
            color: #4CAF50;
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
            background-color: #4CAF50;
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
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }

        a:hover {
            background-color: #45a049;
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
        <h1>Danh Sách Ng??i Dùng</h1>
        
        <table>
            <thead>
                <tr>
                    <th>H? và Tên</th>
                    <th>Email</th>
                    <th>??a ch?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.fname} ${customer.lname}</td>
                        <td>${customer.email}</td>
                        <td>${customer.address}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <a href="./page/adminDashboard.jsp">Quay l?i Dashboard</a>
    </div>

</body>
</html>
