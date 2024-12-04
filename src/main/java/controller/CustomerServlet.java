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
            // Đăng ký
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
                if (customer.register()) {
                    // Đăng ký thành công, chuyển hướng đến trang đăng nhập
                    response.sendRedirect(request.getContextPath() + "/customerServlet?action=login");
                } else {
                    request.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/page/register.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi khi đăng ký. Vui lòng thử lại.");
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
                    // Đăng nhập thành công
                    response.sendRedirect(request.getContextPath() + "/page/productList.jsp");
                } else {
                    // Đăng nhập thất bại
                    request.setAttribute("error", "Email hoặc mật khẩu không đúng.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi khi đăng nhập. Vui lòng thử lại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
