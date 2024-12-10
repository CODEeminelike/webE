<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>??ng Nh?p</title>
    <style>
        /* Toàn b? thi?t k? */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 14px;
            margin-bottom: 5px;
            color: #555;
        }

        input {
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        input:focus {
            border-color: #FF5722;
            outline: none;
            box-shadow: 0 0 5px rgba(255, 87, 34, 0.5);
        }

        button {
            padding: 12px;
            background-color: #FF5722;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #e64a19;
        }

        .error-message {
            margin-top: 20px;
            color: red;
            font-size: 14px;
            text-align: center;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .login-container {
                padding: 20px;
                width: 90%;
            }

            h1 {
                font-size: 24px;
            }

            label {
                font-size: 12px;
            }

            input, button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h1>??ng Nh?p</h1>
        
        <form action="${pageContext.request.contextPath}/admin" method="post">
            <input type="hidden" name="action" value="loginAdmin" /> <!-- ??t action = loginAdmin -->
            <label for="username">Tên ??ng Nh?p:</label>
            <input type="text" id="username" name="username" required />

            <label for="password">M?t Kh?u:</label>
            <input type="password" id="password" name="password" required />

            <button type="submit">??ng Nh?p</button>
        </form>

        <!-- Hi?n th? thông báo l?i n?u có -->
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>
    </div>

</body>
</html>
