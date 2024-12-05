package appUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/webe";  // Địa chỉ MySQL và tên CSDL của bạn
    private static final String USER = "root";  // Tên người dùng của MySQL
    private static final String PASSWORD = "123456";  // Mật khẩu của MySQL
    private static Connection connection;

    // Phương thức lấy kết nối
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Đăng ký driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Kết nối tới cơ sở dữ liệu
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Phương thức đóng kết nối
     public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null; // Đảm bảo set lại kết nối sau khi đóng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}