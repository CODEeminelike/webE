package dataDAO;

import dataModel.Product;
import appUtil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Phương thức thêm mới sản phẩm
    public boolean addProduct(Product product) {
        String query = "INSERT INTO Product (name, descrip, linkImage, category, price) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescrip());
            statement.setString(3, product.getLinkImage());
            statement.setString(4, product.getCategory());
            statement.setDouble(5, product.getPrice());  // Thêm giá sản phẩm

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

    // Phương thức lấy sản phẩm theo category
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE category = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescrip(resultSet.getString("descrip"));
                product.setLinkImage(resultSet.getString("linkImage"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
        }
        return products;
    }

    // Phương thức lấy sản phẩm theo ID
    public Product getProductById(int productId) {
        String query = "SELECT * FROM product WHERE id = ?";
        Product product = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescrip(resultSet.getString("descrip"));
                product.setLinkImage(resultSet.getString("linkImage"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
        }
        return product;
    }

    // Phương thức cập nhật thông tin sản phẩm
    public boolean updateProduct(Product product) {
        String query = "UPDATE Product SET name = ?, descrip = ?, linkImage = ?, category = ?, price = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescrip());
            statement.setString(3, product.getLinkImage());
            statement.setString(4, product.getCategory());
            statement.setDouble(5, product.getPrice());
            statement.setInt(6, product.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, null);  // Đảm bảo đóng PreparedStatement
        }
    }

    // Phương thức xóa sản phẩm theo ID
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM Product WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setInt(1, productId);

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

    // Phương thức đóng các tài nguyên khác như PreparedStatement và ResultSet
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
    
    // Phương thức lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        // Lấy kết nối thủ công từ DBUtil
        connection = DBUtil.getConnection();
        statement = connection.prepareStatement("SELECT * FROM Product");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setDescrip(resultSet.getString("descrip"));
            product.setLinkImage(resultSet.getString("linkImage"));
            product.setCategory(resultSet.getString("category"));
            product.setPrice(resultSet.getDouble("price"));  // Lấy giá sản phẩm
            products.add(product);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đảm bảo kết nối được đóng trong phần finally
        DBUtil.closeConnection();
    }

    return products;
}
    
}
