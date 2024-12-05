package controller;

import dataModel.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy productID từ tham số request
        String productIdParam = request.getParameter("productId");
        int productID = 0;

        // Kiểm tra giá trị productId có hợp lệ không
        try {
            productID = Integer.parseInt(productIdParam);
        } catch (NumberFormatException e) {
            // Nếu productId không hợp lệ, chuyển hướng về trang sản phẩm
            response.sendRedirect(request.getContextPath() + "/productList");
            return;
        }

        // Lấy email từ session (cartID = email khách hàng)
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email == null) {
            // Nếu không có email trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/customerServlet?action=login");
            return;
        }

        // Tạo đối tượng CartItem và gọi phương thức addOrUpdateCartItem
        CartItem cartItem = new CartItem();
        try {
            boolean success = cartItem.addOrUpdateCartItem(email, productID);

            if (success) {
                // Nếu thêm hoặc cập nhật thành công, chuyển hướng về danh sách sản phẩm
                response.sendRedirect(request.getContextPath() + "/productList");
            } else {
                // Nếu không thành công, quay lại trang sản phẩm và hiển thị thông báo lỗi
                request.setAttribute("message", "Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.");
                request.getRequestDispatcher("/page/productList.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Có lỗi xảy ra trong quá trình xử lý giỏ hàng.");
            request.getRequestDispatcher("/page/productList.jsp").forward(request, response);
        }
    }
}
