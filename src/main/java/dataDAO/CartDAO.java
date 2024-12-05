package dataDAO;

import dataModel.*;
import appUtil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    // Phương thức thêm mới giỏ hàng
    public boolean addCart(Cart cart) {
        String query = "INSERT INTO Cart (customerID) VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);

            statement.setInt(1, cart.getCustomterID());  // Vẫn giữ customerID là int

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, null);  // Đảm bảo đóng PreparedStatement
        }
    }

    // Hàm lấy danh sách CartItem từ cartID
    public List<CartItem> getCartItemsByCartID(String cartID) throws SQLException {
        String query = "SELECT * FROM CartItem WHERE cartID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CartItem> cartItems = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setString(1, cartID);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Lấy thông tin CartItem từ ResultSet
                CartItem cartItem = new CartItem();
                cartItem.setID(resultSet.getInt("iD"));
                cartItem.setCartID(resultSet.getString("cartID"));
                cartItem.setProduct(resultSet.getInt("productID"));
                cartItem.setMount(resultSet.getInt("mount"));

                // Thêm vào danh sách
                cartItems.add(cartItem);
            }

        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
        }

        return cartItems;
    }
    
    // Phương thức lấy thông tin giỏ hàng theo ID
    

    // Phương thức cập nhật giỏ hàng (giả sử có)
    public boolean updateCart(Cart cart) {
        // Code cập nhật giỏ hàng
        return false;  // Placeholder
    }

    // Đóng các tài nguyên PreparedStatement và ResultSet
    private void closeResources(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // Phương thức xóa tất cả các sản phẩm trong giỏ hàng
    public boolean deleteCartItemsByCartID(String cartID) throws SQLException {
        String query = "DELETE FROM CartItem WHERE cartID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtil.getConnection();  // Kết nối đến cơ sở dữ liệu
            statement = connection.prepareStatement(query);
            statement.setString(1, cartID);  // Set cartID để xóa các mục tương ứng với giỏ hàng của người dùng

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, null);  // Đảm bảo đóng PreparedStatement
        }
    }
    
    
}
