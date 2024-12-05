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
        // T?o ??i t??ng CartItemDAO v� l?y danh s�ch CartItem t? CSDL
        CartItemDAO cartItemDAO = new CartItemDAO();
        List<CartItem> cartItems = null;

        try {
            // Truy v?n CartItems t? CSDL
            cartItems = cartItemDAO.getCartItemsByCartID(email);
        } catch (SQLException e) {
            out.println("<p>Error fetching cart items: " + e.getMessage() + "</p>");
        }

        // N?u c� CartItem, hi?n th? danh s�ch
        if (cartItems != null && !cartItems.isEmpty()) {
            double totalOrderPrice = 0;  // Bi?n t�nh t?ng gi� ??n h�ng
%>

<table>
    <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Action</th>  <!-- C?t Thao t�c -->
        </tr>
    </thead>
    <tbody>
        <% 
            // Duy?t qua danh s�ch cartItems v� hi?n th?
            CartItemDAO itemDAO = new CartItemDAO();  // ?? l?y gi� s?n ph?m
            for (CartItem cartItem : cartItems) {
                // L?y gi� c?a s?n ph?m t? ProductDAO
                double productPrice = itemDAO.getProductPrice(cartItem.getProduct());
                double totalPrice = productPrice * cartItem.getMount();
                totalOrderPrice += totalPrice;  // C?ng t?ng gi� s?n ph?m v�o t?ng ??n h�ng

                // L?y t�n s?n ph?m t? CartItemDAO
                String productName = itemDAO.getProductNameById(cartItem.getProduct());
        %>
            <tr>
                <td><%= cartItem.getProduct() %></td>
                <td><%= productName %></td>  <!-- Hi?n th? t�n s?n ph?m -->
                <td><%= cartItem.getMount() %></td>
                <td><%= productPrice %></td>
                <td><%= totalPrice %></td>
                <td>
                    <!-- N�t x�a s?n ph?m: Truy?n action=del v� product_id v�o -->
                    <form action="<%= request.getContextPath() %>/order" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="del">
                        <input type="hidden" name="product_id" value="<%= cartItem.getProduct() %>">
                        <button class="button button-danger" type="submit">-</button>
                    </form>
                </td>
            </tr>
        <% 
            } // K?t th�c v�ng l?p
        %>
    </tbody>
</table>

<!-- Hi?n th? t?ng gi� ??n h�ng -->
<div class="total-price">
    <p>Total Order Price: <%= totalOrderPrice %> VND</p>
</div>

<!-- N�t thanh to�n -->
<form action="<%= request.getContextPath() %>/order" method="post">
    <input type="hidden" name="action" value="buy">
    <!-- Th�m c�c tham s? c?n thi?t (v� d? t?ng gi� tr? gi? h�ng, v.v.) -->
    <input type="hidden" name="totalOrderPrice" value="<%= totalOrderPrice %>">
    <button class="button button-primary" type="submit">Thanh to�n</button>
</form>

<% 
        } else {
            out.println("<p>Your cart is empty.</p>");
        }
    }
%>

</body>
</html>
