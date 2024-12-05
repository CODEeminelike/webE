package controller;

import dataDAO.CartItemDAO;
import dataModel.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số "action" từ yêu cầu
        String action = request.getParameter("action");

        // Lấy email (cartID) từ session
        String cartID = (String) request.getSession().getAttribute("email");
        if (cartID == null || cartID.isEmpty()) {
            response.sendRedirect("login.jsp"); // Chuyển hướng tới trang đăng nhập nếu chưa đăng nhập
            return;
        }

        // Xử lý hành động "del" (giảm số lượng hoặc xóa sản phẩm)
        if ("del".equals(action)) {
            try {
                int productID = Integer.parseInt(request.getParameter("product_id"));

                // Gọi phương thức xóa hoặc giảm số lượng sản phẩm từ CartItemDAO
                CartItemDAO cartItemDAO = new CartItemDAO();
                boolean isUpdated = cartItemDAO.deleteCartItem(cartID, productID);

                if (isUpdated) {
                    response.sendRedirect("page/Cart2.jsp"); // Chuyển hướng lại trang giỏ hàng
                } else {
                    response.getWriter().println("Failed to update the item in the cart.");
                }
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().println("Error occurred while processing the request.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số "action" từ yêu cầu
        String action = request.getParameter("action");

        // Lấy email (cartID) từ session
        String cartID = (String) request.getSession().getAttribute("email");
        if (cartID == null || cartID.isEmpty()) {
            response.sendRedirect("login.jsp"); // Chuyển hướng tới trang đăng nhập nếu chưa đăng nhập
            return;
        }

        // Xử lý hành động "buy" (thanh toán)
         if ("buy".equals(action)) {
            try {
                // Lấy tổng giá đơn hàng từ request (có thể dùng để hiển thị thông báo thành công)
                double totalOrderPrice = Double.parseDouble(request.getParameter("totalOrderPrice"));

                // Gọi phương thức xử lý thanh toán và xóa giỏ hàng từ Cart
                Cart cart = new Cart();
                boolean isPaymentProcessed = cart.processPayment(cartID);

                if (isPaymentProcessed) {
                    // Hiển thị thông báo thanh toán thành công
                    response.getWriter().println("<h1>Thanh toan thanh cong!</h1>");
                    response.getWriter().println("<p>Tong gia tri don hang: " + totalOrderPrice + " VND</p>");
                    response.getWriter().println("<a href='productList.jsp'>Tro ve</a>");
                } else {
                    response.getWriter().println("Error occurred while processing the payment.");
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Error occurred while processing the payment.");
            }
        }
    }
}
