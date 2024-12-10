/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataModel;
import dataDAO.CartItemDAO;
import dataDAO.OrdersDAO;
import dataDAO.OrdersDAO;
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
   public boolean processPayment(String cartID, String email, String address, double totalOrderPrice) throws SQLException {
    // 1. Tạo đơn hàng mới
    Orders order = new Orders();
    order.setDate(new java.sql.Date(System.currentTimeMillis())); // Lấy ngày hiện tại
    order.setTime(new java.sql.Time(System.currentTimeMillis())); // Lấy giờ hiện tại
    order.setEmail(email); // Email người dùng
    order.setAddress(address); // Địa chỉ người dùng
    order.setTotalPrice(totalOrderPrice); // Tổng giá trị đơn hàng

    // Lưu đơn hàng vào cơ sở dữ liệu
    OrdersDAO ordersDAO = new OrdersDAO();  // Sử dụng OrdersDAO thay vì OrderDAO
    boolean isOrderCreated = ordersDAO.addOrder(order);  // Lưu đơn hàng vào CSDL

    if (isOrderCreated) {
        // 2. Sau khi thanh toán thành công, xóa tất cả các CartItem trong giỏ hàng
        boolean areItemsDeleted = cartItemDAO.deleteCartItemsByCartID(cartID);  // Giả sử cartItemDAO đã được khởi tạo

        return areItemsDeleted; // Trả về kết quả xóa CartItem
    } else {
        return false; // Nếu tạo đơn hàng không thành công, trả về false
    }
}

}

