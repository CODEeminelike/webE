<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Qu?n Tr?</title>
    
    <style>
        /* T?ng th? trang */
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            margin: 0;
            padding: 0;
            color: #fff;
        }

        /* Header */
        header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 15px;
            z-index: 10;
        }

        /* Navbar */
        nav ul {
            display: flex;
            justify-content: center;
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            margin: 0 20px;
            position: relative;
        }

        /* Liên k?t */
        nav ul li a,
        nav ul li button {
            color: #fff;
            text-decoration: none;
            font-size: 16px;
            text-transform: uppercase;
            font-weight: bold;
            padding: 10px 20px;
            border-radius: 30px;
            background-color: #ff5f5f;
            border: 2px solid transparent;
            transition: all 0.3s ease;
            cursor: pointer;
        }

        /* Khi rê chu?t vào các nút */
        nav ul li a:hover,
        nav ul li button:hover {
            background-color: #ff3333;
            transform: translateY(-5px);
            border-color: #fff;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        /* T?o hi?u ?ng xoay cho các nút */
        nav ul li button {
            transition: transform 0.3s ease;
        }

        nav ul li button:hover {
            transform: rotate(5deg);
        }

        /* Form ??ng xu?t */
        form {
            display: inline-block;
            margin: 0;
        }

        form input[type="hidden"] {
            display: none;
        }

        /* ??m b?o các form ???c canh gi?a */
        form button {
            padding: 10px 20px;
            background: linear-gradient(to left, #6a11cb, #2575fc);
            border: none;
            color: #fff;
            border-radius: 25px;
            transition: transform 0.3s ease;
        }

        form button:hover {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            transform: scale(1.1);
        }

        /* ??t các liên k?t n?m trong form */
        form button:focus {
            outline: none;
        }
    </style>
</head>

<body>
    <header>
        <nav>
            <ul>
                <li><a href="./TrangChu.jsp">Trang Ch?</a></li>
                
                <li>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="action" value="logout" />
                        <button type="submit">??ng Xu?t</button>
                    </form>
                </li>

                <li>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="action" value="viewUser" />
                        <button type="submit">Danh sách khách hàng</button>
                    </form>
                </li>

                <li>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="action" value="viewOrder" />
                        <button type="submit">Danh sách ??n hàng</button>
                    </form>
                </li>

                <li>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="action" value="viewProduct" />
                        <button type="submit">Danh sách S?n ph?m</button>
                    </form>
                </li>
            </ul>
        </nav>
    </header>
</body>

</html>
