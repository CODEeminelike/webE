/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataModel;
import dataDAO.CartItemDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class Cart implements Serializable {
    private List<CartItem> cartItem;
    private int customterID;

    // Constructor
    public Cart() {
    }

     private CartItemDAO cartItemDAO = new CartItemDAO();

    // Hàm lấy danh sách CartItem từ email của khách hàng
    public List<CartItem> getCartItemsByEmail(String email) throws SQLException {
        // Dùng email làm cartID để lấy danh sách CartItem
        return cartItemDAO.getCartItemsByCartID(email);
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public int getCustomterID() {
        return customterID;
    }

    public void setCustomterID(int customterID) {
        this.customterID = customterID;
    }

    // Phương thức xử lý thanh toán và xóa giỏ hàng
    public boolean processPayment(String cartID) throws SQLException {
        // 1. Xử lý thanh toán (giả lập thanh toán thành công)
        // Bạn có thể thêm logic xử lý thanh toán thực tế tại đây (ví dụ: lưu thông tin đơn hàng vào cơ sở dữ liệu, etc.)

        // 2. Sau khi thanh toán thành công, xóa tất cả các CartItem trong giỏ hàng
        return cartItemDAO.deleteCartItemsByCartID(cartID);
    }
}