package dataDAO;

import dataModel.Customer;
import appUtil.DBUtil;
import appUtil.PBKDF2Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    String query = "INSERT INTO Customer (fname, lname, email, cartID, password, address) VALUES (?, ?, ?, ?, ?, ?)";
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
        statement.setString(6, customer.getAddress());  // Add address

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
            customer.setAddress(resultSet.getString("address"));  // Add address
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
    String query = "UPDATE Customer SET fname = ?, lname = ?, password = ?, address = ? WHERE email = ?";
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = DBUtil.getConnection();  // Lấy kết nối từ DBUtil
        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getFname());
        statement.setString(2, customer.getLname());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getAddress());  // Update address
        statement.setString(5, customer.getEmail());  // Giữ nguyên email (không thay đổi)

        return statement.executeUpdate() > 0;  // Nếu cập nhật thành công, trả về true
    } finally {
        DBUtil.closeConnection();  // Đảm bảo đóng kết nối
        closeResources(statement, null);  // Đảm bảo đóng PreparedStatement
    }
}
// Phương thức kiểm tra đăng nhập của admin từ bảng Admin
public boolean loginAdmin(String username, String password) throws SQLException, Exception {
    String query = "SELECT * FROM admin WHERE username = ?";
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = DBUtil.getConnection(); // Lấy kết nối từ DBUtil
        statement = connection.prepareStatement(query);
        statement.setString(1, username);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Lấy mật khẩu đã băm từ cơ sở dữ liệu
            String storedPassword = resultSet.getString("password");

            // Kiểm tra mật khẩu người dùng nhập vào với mật khẩu đã băm
            return PBKDF2Util.checkPassword(password, storedPassword);
        }
    } finally {
        DBUtil.closeConnection(); // Đảm bảo đóng kết nối
        closeResources(statement, resultSet); // Đảm bảo đóng PreparedStatement và ResultSet
    }
    return false; // Nếu không tìm thấy username, trả về false
}


    // Phương thức lấy tất cả khách hàng
    // Phương thức lấy tất cả khách hàng
    public List<Customer> getAllCustomers() throws SQLException {
    List<Customer> customers = new ArrayList<>();
    String query = "SELECT * FROM Customer";  // SQL để lấy tất cả khách hàng
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = DBUtil.getConnection();  // Kết nối cơ sở dữ liệu
        statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setID(resultSet.getInt("id"));  // Đảm bảo lấy ID đúng
            customer.setFname(resultSet.getString("fname"));
            customer.setLname(resultSet.getString("lname"));
            customer.setEmail(resultSet.getString("email"));
            customer.setAddress(resultSet.getString("address"));
            customers.add(customer);  // Thêm vào danh sách
        }
    } finally {
        DBUtil.closeConnection();
        closeResources(statement, resultSet);
    }
    return customers;  // Trả về danh sách khách hàng
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
