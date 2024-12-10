package controller;

import dataDAO.*;
import dataModel.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        
        //Xử lý hành động "viewHistory" (xem lịch sử mua hàng)
    
    // Xử lý hành động "viewHistory" (xem lịch sử mua hàng)
    else if ("viewHistory".equals(action)) {
    try {
        // Lấy email từ session
        String email = (String) request.getSession().getAttribute("email");
        if (email == null || email.isEmpty()) {
            response.sendRedirect("login.jsp"); // Chuyển hướng tới trang đăng nhập nếu chưa đăng nhập
            return;
        }

        // Tạo đối tượng OrdersDAO và lấy danh sách các đơn hàng của user
        OrdersDAO ordersDAO = new OrdersDAO();
        List<Orders> ordersList = ordersDAO.getOrderByEmail(email);

        // Kiểm tra nếu không có đơn hàng nào
        if (ordersList.isEmpty()) {
            request.setAttribute("message", "Không có đơn hàng nào.");
        }

        // Gửi danh sách đơn hàng tới JSP
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("./page/orderHistory.jsp").forward(request, response);

    } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("message", "Đã xảy ra lỗi khi truy vấn dữ liệu: " + e.getMessage());
        request.getRequestDispatcher("./page/orderHistory.jsp").forward(request, response);
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
                // Lấy tổng giá đơn hàng từ request
                double totalOrderPrice = Double.parseDouble(request.getParameter("totalOrderPrice"));
                String email = (String) request.getSession().getAttribute("email"); // Lấy email từ request
                CustomerDAO dao = new CustomerDAO();
                Customer cus = dao.getCustomerByEmail(email);
                String address = cus.getAddress(); // Lấy địa chỉ từ request
             
                // Gọi phương thức xử lý thanh toán và xóa giỏ hàng
                Cart cart = new Cart();
                boolean isPaymentProcessed = cart.processPayment(email, email, address, totalOrderPrice);

                if (isPaymentProcessed) {
                    // Hiển thị thông báo thanh toán thành công
                    response.getWriter().println("<h1>Thanh toán thành công!</h1>");
                    response.getWriter().println("<p>Tổng giá trị đơn hàng: " + totalOrderPrice + " VND</p>");
                    response.getWriter().println("<a href='./page/productList.jsp'>Trở về</a>");
                } else {
                    response.getWriter().println("Đã xảy ra lỗi trong quá trình xử lý thanh toán.");
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Đã xảy ra lỗi trong quá trình xử lý thanh toán.");
            }
        }

    }
}
