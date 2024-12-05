<%@ page import="dataDAO.CartItemDAO" %>
<%@ page import="dataDAO.ProductDAO" %>
<%@ page import="dataModel.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        .total-price {
            font-weight: bold;
            font-size: 18px;
            margin-top: 20px;
        }
        .button {
            padding: 6px 12px;
            font-size: 14px;
            cursor: pointer;
        }
        .button-danger {
            background-color: #f44336;
            color: white;
            border: none;
        }
        .button-primary {
            background-color: #4CAF50;
            color: white;
            border: none;
        }
    </style>
</head>
<body>

<h2>Your Cart</h2>

<% 
    // L?y email t? session (ho?c t? request parameter)
    String email = (String) session.getAttribute("email");
    if (email == null || email.isEmpty()) {
        out.println("<p>You need to log in first!</p>");
    } else {
        // T?o ??i t??ng CartItemDAO và l?y danh sách CartItem t? CSDL
        CartItemDAO cartItemDAO = new CartItemDAO();
        List<CartItem> cartItems = null;

        try {
            // Truy v?n CartItems t? CSDL
            cartItems = cartItemDAO.getCartItemsByCartID(email);
        } catch (SQLException e) {
            out.println("<p>Error fetching cart items: " + e.getMessage() + "</p>");
        }

        // N?u có CartItem, hi?n th? danh sách
        if (cartItems != null && !cartItems.isEmpty()) {
            double totalOrderPrice = 0;  // Bi?n tính t?ng giá ??n hàng
%>

<table>
    <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Action</th>  <!-- C?t Thao tác -->
        </tr>
    </thead>
    <tbody>
        <% 
            // Duy?t qua danh sách cartItems và hi?n th?
            CartItemDAO itemDAO = new CartItemDAO();  // ?? l?y giá s?n ph?m
            for (CartItem cartItem : cartItems) {
                // L?y giá c?a s?n ph?m t? ProductDAO
                double productPrice = itemDAO.getProductPrice(cartItem.getProduct());
                double totalPrice = productPrice * cartItem.getMount();
                totalOrderPrice += totalPrice;  // C?ng t?ng giá s?n ph?m vào t?ng ??n hàng

                // L?y tên s?n ph?m t? CartItemDAO
                String productName = itemDAO.getProductNameById(cartItem.getProduct());
        %>
            <tr>
                <td><%= cartItem.getProduct() %></td>
                <td><%= productName %></td>  <!-- Hi?n th? tên s?n ph?m -->
                <td><%= cartItem.getMount() %></td>
                <td><%= productPrice %></td>
                <td><%= totalPrice %></td>
                <td>
                    <!-- Nút xóa s?n ph?m: Truy?n action=del và product_id vào -->
                    <form action="<%= request.getContextPath() %>/order" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="del">
                        <input type="hidden" name="product_id" value="<%= cartItem.getProduct() %>">
                        <button class="button button-danger" type="submit">-</button>
                    </form>
                </td>
            </tr>
        <% 
            } // K?t thúc vòng l?p
        %>
    </tbody>
</table>

<!-- Hi?n th? t?ng giá ??n hàng -->
<div class="total-price">
    <p>Total Order Price: <%= totalOrderPrice %> VND</p>
</div>

<!-- Nút thanh toán -->
<form action="<%= request.getContextPath() %>/order" method="post">
    <input type="hidden" name="action" value="buy">
    <!-- Thêm các tham s? c?n thi?t (ví d? t?ng giá tr? gi? hàng, v.v.) -->
    <input type="hidden" name="totalOrderPrice" value="<%= totalOrderPrice %>">
    <button class="button button-primary" type="submit">Thanh toán</button>
</form>

<% 
        } else {
            out.println("<p>Your cart is empty.</p>");
        }
    }
%>

</body>
</html>
