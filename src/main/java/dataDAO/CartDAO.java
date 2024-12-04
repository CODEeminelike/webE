/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataDAO;



import dataModel.*;
import appUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    // Phương thức thêm mới giỏ hàng
    public boolean addCart(Cart cart) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Cart (customerID) VALUES (?)")) {

            statement.setInt(1, cart.getCustomterID());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy thông tin giỏ hàng theo ID
    public Cart getCartById(int cartId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cart WHERE cartID = ?")) {

            statement.setInt(1, cartId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Cart cart = new Cart();
                    // Lấy danh sách CartItem từ CartItemDAO
                    CartItemDAO cartItemDAO = new CartItemDAO();
                    List<CartItem> cartItems = cartItemDAO.getCartItemsByCartId(cartId);
                    cart.setCartItem(cartItems);
                    cart.setCustomterID(resultSet.getInt("customerID"));
                    return cart;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật giỏ hàng (thường không cần thiết vì chỉ cần cập nhật CartItem)
    public boolean updateCart(Cart cart) {
        // Triển khai nếu cần thiết
        return false;
    }

    // Phương thức xóa giỏ hàng
    public boolean deleteCart(int cartId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Cart WHERE cartID = ?")) {

            statement.setInt(1, cartId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}