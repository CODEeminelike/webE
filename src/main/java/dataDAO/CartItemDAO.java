package dataDAO;

import dataModel.*;
import appUtil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {
    
    
     // Cập nhật số lượng sản phẩm trong giỏ hàng
    public boolean updateCartItem(String cartID, int productID) throws SQLException {
        String query = "UPDATE CartItem SET mount = mount + 1 WHERE cartID = ? AND productID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, cartID);
            statement.setInt(2, productID);

            return statement.executeUpdate() > 0;
        } finally {
            DBUtil.closeConnection();
            closeResources(statement, null);
        }
    }
    
    // Phương thức xóa hoặc giảm số lượng sản phẩm trong giỏ hàng
public boolean deleteCartItem(String cartID, int productID) throws SQLException {
    // Bước 1: Kiểm tra xem sản phẩm có trong giỏ hàng hay không
    String queryCheck = "SELECT mount FROM CartItem WHERE cartID = ? AND productID = ?";
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
        statement = connection.prepareStatement(queryCheck);
        statement.setString(1, cartID);  // Set cartID (email khách hàng)
        statement.setInt(2, productID);  // Set productID (ID của sản phẩm)

        resultSet = statement.executeQuery();

        // Kiểm tra nếu sản phẩm có trong giỏ hàng
        if (resultSet.next()) {
            int currentQuantity = resultSet.getInt("mount");

            // Bước 2: Nếu số lượng > 1, giảm số lượng đi 1
            if (currentQuantity > 1) {
                String queryUpdate = "UPDATE CartItem SET mount = mount - 1 WHERE cartID = ? AND productID = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(queryUpdate)) {
                    updateStatement.setString(1, cartID);
                    updateStatement.setInt(2, productID);
                    updateStatement.executeUpdate();
                }
                return true;
            }
            // Bước 3: Nếu số lượng = 1, xóa sản phẩm khỏi giỏ hàng
            else if (currentQuantity == 1) {
                String queryDelete = "DELETE FROM CartItem WHERE cartID = ? AND productID = ?";
                try (PreparedStatement deleteStatement = connection.prepareStatement(queryDelete)) {
                    deleteStatement.setString(1, cartID);
                    deleteStatement.setInt(2, productID);
                    deleteStatement.executeUpdate();
                }
                return true;
            }
        }
        return false;  // Trả về false nếu sản phẩm không tồn tại trong giỏ hàng
    } finally {
        DBUtil.closeConnection();  // Đảm bảo đóng kết nối
        closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
    }
}

    
    
    
    // Kiểm tra xem sản phẩm có trong giỏ hàng của khách hàng hay không
    public boolean isProductInCart(String cartID, int productID) throws SQLException {
        String query = "SELECT * FROM CartItem WHERE cartID = ? AND productID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();  // Kết nối tới cơ sở dữ liệu
            statement = connection.prepareStatement(query);
            statement.setString(1, cartID);  // Set cartID từ email
            statement.setInt(2, productID);  // Set productID

            resultSet = statement.executeQuery();

            // Nếu tìm thấy bản ghi, trả về true (sản phẩm đã có trong giỏ hàng)
            return resultSet.next();

        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
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
     private void closeResources(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // Phương thức thêm mới CartItem
    public boolean addCartItem(CartItem cartItem) {
    String query = "INSERT INTO CartItem (cartID, productID, mount) VALUES (?, ?, ?)";
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
        statement = connection.prepareStatement(query);

        statement.setString(1, cartItem.getCartID());  // Sử dụng setString để set cartID (email khách hàng)
        statement.setInt(2, cartItem.getProduct());  // Thay vì sử dụng cartItem.getProduct().getId(), giờ sử dụng trực tiếp productID
        statement.setInt(3, cartItem.getMount());  // Thiết lập số lượng sản phẩm

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

    // Phương thức lấy danh sách CartItem theo cartID
   
public String getProductNameById(int productId) throws SQLException {
    String query = "SELECT name FROM Product WHERE id = ?";
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String productName = null;

    try {
        connection = DBUtil.getConnection();
        statement = connection.prepareStatement(query);
        statement.setInt(1, productId);

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            productName = resultSet.getString("name");
        }
    } finally {
        DBUtil.closeConnection();
        closeResources(statement, resultSet);
    }
    
    return productName;
}

    // Phương thức đóng các tài nguyên PreparedStatement và ResultSet
    public double getProductPrice(int productId) throws SQLException {
    String query = "SELECT price FROM Product WHERE id = ?";
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    double price = 0.0;

    try {
        connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
        statement = connection.prepareStatement(query);
        statement.setInt(1, productId);

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            price = resultSet.getDouble("price");
        }
    } finally {
        DBUtil.closeConnection();  // Đảm bảo đóng kết nối
        closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
    }
    return price;
}

}
