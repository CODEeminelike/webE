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
            String email = (String) session.getAttribute("email");

            // Nếu email tồn tại trong session, lấy thông tin khách hàng từ database
            if (email != null) {
                try {
                    // Lấy thông tin khách hàng từ cơ sở dữ liệu theo email
                    CustomerDAO dao = new CustomerDAO();
                    Customer customer = dao.getCustomerByEmail(email);

                    // Nếu khách hàng tồn tại, truyền đối tượng customer vào request
                    if (customer != null) {
                        request.setAttribute("customer", customer);
                        // Chuyển đến trang chỉnh sửa thông tin
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/page/editProfile.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Nếu không tìm thấy khách hàng, chuyển về trang đăng nhập
                        response.sendRedirect(request.getContextPath() + "/customerServlet?action=login");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                }
            } else {
                // Nếu chưa có email trong session, chuyển về trang đăng nhập
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
            String address = request.getParameter("address");

            // Lấy thông tin khách hàng từ session
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            if (email != null) {
                try {
                    // Lấy thông tin khách hàng từ cơ sở dữ liệu theo email
                    CustomerDAO dao = new CustomerDAO();
                    Customer customer = dao.getCustomerByEmail(email);

                    if (customer != null) {
                        // Cập nhật thông tin khách hàng
                        customer.setFname(fname);
                        customer.setLname(lname);
                        customer.setPassword(password);
                        customer.setAddress(address);

                        // Cập nhật thông tin vào cơ sở dữ liệu
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
                    }

                    // Truyền đối tượng customer vào request và chuyển đến trang editProfile.jsp
                    request.setAttribute("customer", customer);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/page/editProfile.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("updateError", "Có lỗi xảy ra khi cập nhật thông tin!");
                    request.getRequestDispatcher("/page/editProfile.jsp").forward(request, response);
                }
            }
        } else if ("logout".equals(action)) {
            // Xử lý đăng xuất
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/page/productList.jsp");
        }
    }
}
