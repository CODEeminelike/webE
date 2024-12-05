package controller;

import dataDAO.CartItemDAO;
import dataModel.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cartServlet2")
public class CartServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lấy email từ session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Kiểm tra nếu email không tồn tại trong session
        if (email == null || email.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found in session");
            return;
        }

        // Lấy danh sách CartItem từ DAO
        CartItemDAO cartItemDAO = new CartItemDAO();
        try {
            List<CartItem> cartItems = cartItemDAO.getCartItemsByCartID(email);

            // Đặt danh sách CartItem vào request attribute
            request.setAttribute("cartItems", cartItems);

            // Chuyển hướng tới trang JSP để hiển thị
            request.getRequestDispatcher("/page/cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching cart items");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
