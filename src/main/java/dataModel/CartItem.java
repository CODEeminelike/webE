package dataModel;

import dataDAO.CartItemDAO;
import java.sql.SQLException;

public class CartItem {

    private int iD;
    private String cartID;
    private int product;
    private int mount;

    // Constructor
    public CartItem() {}

    // Getters and Setters

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    // Hàm xử lý việc thêm hoặc cập nhật sản phẩm trong giỏ hàng
   public boolean addOrUpdateCartItem(String email, int productID) throws SQLException {
    CartItemDAO cartItemDAO = new CartItemDAO();
    String cartID = email;  // CartID = email của khách hàng

    // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
    if (cartItemDAO.isProductInCart(cartID, productID)) {
        // Nếu đã có sản phẩm trong giỏ hàng, cần cập nhật số lượng
        return cartItemDAO.updateCartItem(cartID, productID);
    } else {
        // Nếu chưa có, thêm sản phẩm mới vào giỏ
        CartItem cartItem = new CartItem();
        cartItem.setCartID(cartID);  // Thiết lập CartID là email của khách hàng
        cartItem.setProduct(productID);  // Truyền trực tiếp productID thay vì tạo đối tượng Product
        cartItem.setMount(1);  // Số lượng ban đầu là 1

        return cartItemDAO.addCartItem(cartItem);  // Gọi phương thức thêm mới
    }
}

}
