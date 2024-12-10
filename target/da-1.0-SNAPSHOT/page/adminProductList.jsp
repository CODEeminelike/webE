<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"> <!-- ??m b?o mã hóa UTF-8 cho d?u ti?ng Vi?t -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách S?n Ph?m</title>
    <style>
        /* T?ng th? style c?a trang */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1 {
            text-align: center;
            font-size: 36px;
            margin: 20px 0;
            color: #4CAF50;
        }

        a {
            display: block;
            text-align: center;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            width: 150px;
            margin-left: auto;
            margin-right: auto;
        }

        a:hover {
            background-color: #45a049;
        }

        /* Style cho b?ng */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        table th, table td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #4CAF50;
            color: white;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        table td {
            background-color: #fff;
            color: #333;
        }

        /* Hi?u ?ng hover cho dòng trong b?ng */
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            transform: scale(1.02);
            transition: 0.3s ease-in-out;
        }

        /* Hi?u ?ng khi hover qua các ô b?ng */
        table td:hover {
            background-color: #e7f7e7;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* Responsive */
        @media (max-width: 768px) {
            table {
                width: 100%;
            }

            h1 {
                font-size: 28px;
            }

            a {
                width: 120px;
            }
        }
    </style>
</head>
<body>
    <h1>Danh Sách S?n Ph?m</h1>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên S?n Ph?m</th>
                <th>Mô T?</th>
                <th>Danh M?c</th>
                <th>Giá</th>
            </tr>
        </thead>
        <tbody>
            <!-- Duy?t qua danh sách s?n ph?m -->
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>  <!-- Hi?n th? ID s?n ph?m -->
                    <td>${product.name}</td>  <!-- Hi?n th? tên s?n ph?m -->
                    <td>${product.descrip}</td>  <!-- Hi?n th? mô t? s?n ph?m -->
                    <td>${product.category}</td>  <!-- Hi?n th? danh m?c s?n ph?m -->
                    <td>${product.price}</td>  <!-- Hi?n th? giá s?n ph?m -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="./page/adminDashboard.jsp">Quay l?i</a>
</body>
</html>
