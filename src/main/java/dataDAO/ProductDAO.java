package dataDAO;

import dataModel.*;
import appUtil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Phương thức thêm mới sản phẩm
    public boolean addProduct(Product product) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Product (name, descrip, linkImage, category, price) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescrip());
            statement.setString(3, product.getLinkImage());
            statement.setString(4, product.getCategory());
            statement.setDouble(5, product.getPrice());  // Thêm dòng này để chèn giá sản phẩm

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy thông tin sản phẩm theo ID
    public Product getProductById(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setDescrip(resultSet.getString("descrip"));
                    product.setLinkImage(resultSet.getString("linkImage"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));  // Lấy giá sản phẩm
                    return product;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật thông tin sản phẩm
    public boolean updateProduct(Product product) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Product SET name = ?, descrip = ?, linkImage = ?, category = ?, price = ? WHERE id = ?")) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescrip());
            statement.setString(3, product.getLinkImage());
            statement.setString(4, product.getCategory());
            statement.setDouble(5, product.getPrice());  // Thêm dòng này để cập nhật giá sản phẩm
            statement.setInt(6, product.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa sản phẩm
    public boolean deleteProduct(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Product WHERE id = ?")) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy thông tin sản phẩm theo tên
    public Product getProductByName(String name) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE name = ?")) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setDescrip(resultSet.getString("descrip"));
                    product.setLinkImage(resultSet.getString("linkImage"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));  // Lấy giá sản phẩm
                    return product;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product");
             ResultSet resultSet = statement.executeQuery()) {

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
        }
        return products;
    }
}
