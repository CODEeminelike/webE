package controller;

import dataModel.Customer;
import dataDAO.CustomerDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/profileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Phương thức GET xử lý các yêu cầu hiển thị thông tin cá nhân
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewProfile".equals(action)) {
            // Lấy thông tin người dùng từ session
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");

            // Nếu người dùng đã đăng nhập, hiển thị thông tin cá nhân
            if (customer != null) {
                request.setAttribute("customer", customer);
                // Chỉ set thông báo nếu có lỗi hoặc thành công từ POST
                RequestDispatcher dispatcher = request.getRequestDispatcher("/page/editProfile.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu chưa đăng nhập, chuyển về trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/customerServlet?action=login");
            }
        }
    }

    // Phương thức POST xử lý cập nhật thông tin cá nhân và đăng xuất
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            // Lấy thông tin người dùng từ form
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String password = request.getParameter("password");

            // Lấy thông tin khách hàng từ session
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");

            if (customer != null) {
                customer.setFname(fname);
                customer.setLname(lname);
                customer.setPassword(password);

                try {
                    // Cập nhật thông tin khách hàng
                    boolean updated = customer.updateProfile();

                    if (updated) {
                        // Cập nhật thành công
                        request.setAttribute("updateSuccess", "Cập nhật thông tin thành công!");
                        // Cập nhật lại customer trong session
                        session.setAttribute("customer", customer);
                    } else {
                        // Cập nhật thất bại
                        request.setAttribute("updateError", "Có lỗi xảy ra khi cập nhật thông tin!");
                    }
                } catch (SQLException e) {
                    request.setAttribute("updateError", "Có lỗi xảy ra khi cập nhật thông tin!");
                    e.printStackTrace();
                }

                // Quay lại trang editProfile.jsp để hiển thị kết quả
                request.setAttribute("customer", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/page/editProfile.jsp");
                dispatcher.forward(request, response);
            }
        } else if ("logout".equals(action)) {
            // Xử lý đăng xuất
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/page/productList.jsp");
        }
    }
}
