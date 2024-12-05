/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dataModel.Customer;
import dataDAO.CustomerDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login.jsp");
            dispatcher.forward(request, response);
        } else if ("register".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/page/register.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

       if ("register".equals(action)) {
        // Lấy các tham số từ form đăng ký
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cartID = email;  // CartID có thể tạo sau khi đăng ký thành công

        Customer customer = new Customer();
        customer.setFname(fname);
        customer.setLname(lname);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setCartID(cartID);
        
        try {
            // Kiểm tra nếu đăng ký thành công
            if (customer.register()) {
                // Đăng ký thành công, chuyển hướng đến trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/customerServlet?action=login");
            } else {
                // Nếu đăng ký thất bại (ví dụ: email đã tồn tại)
                request.setAttribute("errorMessage", "Đăng ký thất bại! Email đã tồn tại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/page/register.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQLException và gửi thông báo lỗi
            request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình đăng ký. Vui lòng thử lại.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/page/register.jsp");
            dispatcher.forward(request, response);
        }
        } else if ("login".equals(action)) {
    // Đăng nhập
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    
    Customer customer = new Customer();
    customer.setEmail(email);
    customer.setPassword(password);

    try {
        if (customer.login()) {
            // Lưu thông tin khách hàng vào session
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            session.setAttribute("email", email);  // Lưu email vào session

            // Đăng nhập thành công, chuyển hướng đến trang chính
            response.sendRedirect(request.getContextPath() + "/page/productList.jsp");
        } else {
            // Đăng nhập thất bại, quay lại trang đăng nhập
            request.setAttribute("message", "Đăng nhập thất bại, vui lòng kiểm tra lại email và mật khẩu.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login.jsp");
            dispatcher.forward(request, response);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("message", "Có lỗi xảy ra trong quá trình xử lý. Vui lòng thử lại sau.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login.jsp");
        dispatcher.forward(request, response);
    }
}

}

    }


