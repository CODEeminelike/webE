/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import Email.EmailSender;
import dataModel.Customer;
import dataDAO.CustomerDAO;
import jakarta.mail.MessagingException;

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
            String address = request.getParameter("address");  // Get address from form

            Customer customer = new Customer();
            customer.setFname(fname);
            customer.setLname(lname);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setCartID(cartID);
            customer.setAddress(address);  // Set address here

            try {
                sendMail(email, request);
                if (customer.register()) {
                    response.sendRedirect(request.getContextPath() + "/customerServlet?action=login");
                } else {
                    request.setAttribute("errorMessage", "Đăng ký thất bại! Email đã tồn tại.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/page/register.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình đăng ký. Vui lòng thử lại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/page/register.jsp");
                dispatcher.forward(request, response);
            }
        } else if ("login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Customer customer = new Customer();
            customer.setEmail(email);
            customer.setPassword(password);

            try {
                if (customer.login()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("customer", customer);
                    session.setAttribute("email", email);
                    response.sendRedirect(request.getContextPath() + "/page/productList.jsp");
                } else {
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

    public void sendMail(String email, HttpServletRequest request){
                    String from = "chungkhoa456@gmail.com";
                    String subject = "Đăng ký tài khoản thành công";

                // Tạo nội dung email bằng HTML
                StringBuilder body = new StringBuilder();
                body.append("<!DOCTYPE html>")
                        .append("<html>")
                        .append("<head>")
                        .append("<style>")
                        .append("body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; background-color: #f4f4f4; margin: 0; padding: 20px; }")
                        .append(".email-container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }")
                        .append(".header { background-color: #4CAF50; color: white; text-align: center; padding: 15px; border-radius: 5px 5px 0 0; }")
                        .append(".content { padding: 20px; }")
                        .append(".button { display: inline-block; background-color: #4CAF50; color: white !important; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 10px; }")
                        .append(".footer { text-align: center; margin-top: 20px; font-size: 0.9em; color: #777; }")
                        .append("</style>")
                        .append("</head>")
                        .append("<body>")
                        .append("<div class='email-container'>")
                        .append("<div class='header'>")
                        .append("<h1>Chào mừng đến với dịch vụ của chúng tôi!</h1>")
                        .append("</div>")
                        .append("<div class='content'>")
                        .append("<p>Xin chào,</p>")
                        .append("<p>Cảm ơn bạn đã đăng ký tài khoản với chúng tôi! Chúng tôi rất vui mừng được chào đón bạn.</p>")
                        .append("<p>Bây giờ bạn có thể khám phá các dịch vụ của chúng tôi và trải nghiệm những tiện ích tuyệt vời.</p>")
                        .append("</div>")
                        .append("<div class='footer'>")
                        .append("</div>")
                        .append("</div>")
                        .append("</body>")
                        .append("</html>");
                boolean isBodyHTML = true;
                try {
                    // Gửi email
                    EmailSender.sendMail(email, from, subject, body.toString(), isBodyHTML);

                    // Gửi thông báo thành công
                    request.setAttribute("successMessage", "Liên kết đặt lại mật khẩu đã được gửi đến email của bạn.");
                } catch (MessagingException e) {
                    // Gửi thông báo lỗi
                    request.setAttribute("errorMessage", "Gửi email thất bại. Vui lòng thử lại sau.");
                    this.log("Email gửi thất bại: " + e.getMessage());
                    }
}
}
