package dataDAO;

import dataModel.Customer;
import appUtil.DBUtil;

import java.sql.*;

public class CustomerDAO {

    // Phương thức kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
    public boolean isEmailExist(String email) throws SQLException {
        String query = "SELECT * FROM Customer WHERE email = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            return resultSet.next();  // Nếu tìm thấy email trong CSDL, trả về true
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
        }
    }

    // Phương thức thêm khách hàng mới
    public boolean addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (fname, lname, email, cartID, password) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setString(1, customer.getFname());
            statement.setString(2, customer.getLname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getCartID());
            statement.setString(5, customer.getPassword());

            return statement.executeUpdate() > 0;  // Nếu thêm thành công, trả về true
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, null);  // Đảm bảo đóng PreparedStatement
        }
    }

    // Phương thức lấy thông tin khách hàng theo email
    public Customer getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Customer WHERE email = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setFname(resultSet.getString("fname"));
                customer.setLname(resultSet.getString("lname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCartID(resultSet.getString("cartID"));
                customer.setPassword(resultSet.getString("password"));
                return customer;
            }
        } finally {
            DBUtil.closeConnection();  // Đảm bảo đóng kết nối
            closeResources(statement, resultSet);  // Đảm bảo đóng PreparedStatement và ResultSet
        }
        return null;  // Nếu không tìm thấy khách hàng, trả về null
    }

    // Phương thức cập nhật thông tin khách hàng
   public boolean updateCustomer(Customer customer) throws SQLException {
    String query = "UPDATE Customer SET fname = ?, lname = ?, password = ? WHERE email = ?";
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getFname());
        statement.setString(2, customer.getLname());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getEmail());  // Giữ nguyên email (không thay đổi)

        return statement.executeUpdate() > 0;  // Nếu cập nhật thành công, trả về true
    } finally {
        DBUtil.closeConnection();  // Đảm bảo đóng kết nối
        closeResources(statement, null);  // Đảm bảo đóng PreparedStatement
    }
}


    // Hàm đóng các tài nguyên (PreparedStatement, ResultSet)
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
}
