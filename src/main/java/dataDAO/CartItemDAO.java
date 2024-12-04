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

public class CartItemDAO {

    // Phương thức thêm mới CartItem
    public boolean addCartItem(CartItem cartItem) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO CartItem (cartID, productID, mount) VALUES (?, ?, ?)")) {

            statement.setInt(1, cartItem.getCartID());
            statement.setInt(2, cartItem.getProduct().getId()); // Giả sử CartItem có thuộc tính Product
            statement.setInt(3, cartItem.getMount());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy danh sách CartItem theo cartID
    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CartItem WHERE cartID = ?")) {

            statement.setInt(1, cartId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setID(resultSet.getInt("iD"));
                    cartItem.setCartID(resultSet.getInt("cartID"));

                    // Lấy thông tin sản phẩm từ ProductDAO
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.getProductById(resultSet.getInt("productID"));
                    cartItem.setProduct(product);

                    cartItem.setMount(resultSet.getInt("mount"));
                    cartItems.add(cartItem);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // Phương thức cập nhật CartItem
    public boolean updateCartItem(CartItem cartItem) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE CartItem SET productID = ?, mount = ? WHERE iD = ?")) {

            statement.setInt(1, cartItem.getProduct().getId());
            statement.setInt(2, cartItem.getMount());
            statement.setInt(3, cartItem.getID());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa CartItem
    public boolean deleteCartItem(int cartItemId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM CartItem WHERE iD = ?")) {

            statement.setInt(1, cartItemId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}