package dataDAO;

import dataModel.Customer;
import appUtil.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    // Phương thức thêm mới khách hàng
    public boolean addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (fname, lname, email, cartID, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customer.getFname());
            statement.setString(2, customer.getLname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getCartID());  // Đổi kiểu từ int sang String
            statement.setString(5, customer.getPassword());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new SQLException("Không thể thêm khách hàng mới", e);
        }
    }

    // Phương thức lấy khách hàng theo Email
    public Customer getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Customer WHERE email = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setID(resultSet.getInt("iD"));
                customer.setFname(resultSet.getString("fname"));
                customer.setLname(resultSet.getString("lname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCartID(resultSet.getString("cartID"));  // Đổi kiểu từ int sang String
                customer.setPassword(resultSet.getString("password"));
                return customer;  // Trả về khách hàng nếu tìm thấy
            }
        } catch (SQLException e) {
            throw new SQLException("Không thể lấy thông tin khách hàng", e);
        }
        return null;  // Nếu không tìm thấy khách hàng
    }
}
