package dataModel;

import dataDAO.CustomerDAO;
import java.sql.SQLException;

public class Customer {
    private int iD;
    private String fname;
    private String lname;
    private String email;
    private String cartID;  // Chuyển cartID từ int sang String
    private String password;

    // Constructor, getters, setters
    public Customer() {}

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Thay đổi kiểu của phương thức getter và setter cho cartID
    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Phương thức đăng ký
   public boolean register() throws SQLException {
    CustomerDAO dao = new CustomerDAO();
    
    // Kiểm tra nếu email đã tồn tại trong cơ sở dữ liệu
    if (dao.isEmailExist(this.email)) {
        // Nếu email đã tồn tại, trả về false
        return false;
    }
    
    // Nếu email chưa tồn tại, thực hiện thêm khách hàng mới
    return dao.addCustomer(this);
}


    // Phương thức đăng nhập
    // Phương thức đăng nhập
    public boolean login() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        Customer customer = dao.getCustomerByEmail(this.email);  // Lấy khách hàng theo email
        if (customer != null && customer.getPassword().equals(this.password)) {
            return true;  // Đăng nhập thành công
        }
        return false;  // Đăng nhập thất bại (không tìm thấy khách hàng hoặc mật khẩu sai)
    }
    
    public boolean updateProfile() throws SQLException {
        CustomerDAO dao = new CustomerDAO();

        // Kiểm tra nếu email mới trùng với email đã có trong cơ sở dữ liệu
        

        // Cập nhật thông tin khách hàng vào cơ sở dữ liệu
        return dao.updateCustomer(this);
    }

}
