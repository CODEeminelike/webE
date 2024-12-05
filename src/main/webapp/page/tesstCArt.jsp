<%@ page import="dataDAO.CartItemDAO" %>
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
    </style>
</head>
<body>

<h2>Your Cart</h2>

<% 
    // L?y email t? session (ho?c t? request parameter)
    String email = "monnkkey2004@gmail.com";
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
%>

<table>
    <thead>
        <tr>
            <th>Product ID</th>
            <th>Quantity</th>
        </tr>
    </thead>
    <tbody>
        <% 
            // Duy?t qua danh sách cartItems và hi?n th?
            for (CartItem cartItem : cartItems) {
        %>
            <tr>
                <td><%= cartItem.getProduct() %></td>
                <td><%= cartItem.getMount() %></td>
            </tr>
        <% 
            } // K?t thúc vòng l?p
        %>
    </tbody>
</table>

<% 
        } else {
            out.println("<p>Your cart is empty.</p>");
        }
    }
%>

</body>
</html>
