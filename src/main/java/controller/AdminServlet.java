package controller;

import dataDAO.CustomerDAO;
import dataDAO.OrdersDAO;
import dataDAO.ProductDAO;
import dataModel.Customer;
import dataModel.Orders;
import dataModel.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Lấy tham số action từ request
        
        if ("loginAdmin".equals(action)) {
            // Xử lý đăng nhập admin
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            CustomerDAO customerDAO = new CustomerDAO();

            try {
                // Kiểm tra đăng nhập của admin
                boolean isAdminValid = customerDAO.loginAdmin(username, password);

                if (isAdminValid) {
                    // Lưu username admin vào session
                    HttpSession session = request.getSession();
                    session.setAttribute("adminUsername", username);

                    // Chuyển hướng đến trang dashboard quản trị
                    response.sendRedirect("./page/adminDashboard.jsp");
                } else {
                    // Hiển thị thông báo lỗi nếu đăng nhập thất bại
                    request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
                    request.getRequestDispatcher("./page/adminLogin.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Đã xảy ra lỗi khi đăng nhập");
                request.getRequestDispatcher("./page/adminLogin.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("logout".equals(action)) {
            // Xử lý đăng xuất
            HttpSession session = request.getSession();
            session.invalidate(); // Hủy session hiện tại

            // Chuyển hướng về trang đăng nhập
            response.sendRedirect("./page/adminLogin.jsp");
        }else if ("viewUser".equals(action)) {
            // Xử lý hiển thị danh sách người dùng
            CustomerDAO customerDAO = new CustomerDAO();
            try {
                List<Customer> customers = customerDAO.getAllCustomers(); // Lấy danh sách người dùng
                request.setAttribute("customers", customers);  // Truyền danh sách vào request
                request.getRequestDispatcher("./page/adminUserList.jsp").forward(request, response);  // Chuyển đến trang hiển thị
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("./page/adminError.jsp");
            }
        }else if ("viewOrder".equals(action)) {
            // Xử lý hiển thị danh sách người dùng
            OrdersDAO dao = new OrdersDAO();
            try {
                List<Orders> orders = dao.getAllOrders(); // Lấy danh sách người dùng
                request.setAttribute("order", orders);  // Truyền danh sách vào request
                request.getRequestDispatcher("./page/adminOrderList.jsp").forward(request, response);  // Chuyển đến trang hiển thị
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("./page/adminError.jsp");
            }
        }else if ("viewProduct".equals(action)) {
    // Xử lý hiển thị danh sách sản phẩm
    Product product = new Product(); // Tạo đối tượng Product
    List<Product> products = product.getAllProducts();  // Gọi phương thức DAO để lấy tất cả sản phẩm
    request.setAttribute("products", products);  // Truyền danh sách sản phẩm vào request
    request.getRequestDispatcher("./page/adminProductList.jsp").forward(request, response);  // Chuyển đến trang hiển thị
        }
        
    
    
    }
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Thêm sản phẩm
        if ("addProduct".equals(action)) {
            String name = request.getParameter("name");
            String descrip = request.getParameter("descrip");
            String category = request.getParameter("category");
            double price = Double.parseDouble(request.getParameter("price"));

            Product product = new Product();
            product.setName(name);
            product.setDescrip(descrip);
            product.setCategory(category);
            product.setPrice(price);

            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.addProduct(product);

            if (success) {
                // Sau khi thêm sản phẩm thành công, chuyển về danh sách sản phẩm
                response.sendRedirect("admin?action=viewProduct");
            } else {
                request.setAttribute("error", "Thêm sản phẩm thất bại");
                request.getRequestDispatcher("./page/adminProductList.jsp").forward(request, response);
            }
        }

        // Xóa sản phẩm
        else if ("deleteProduct".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));

            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.deleteProduct(productId);

            if (success) {
                // Sau khi xóa sản phẩm thành công, chuyển về danh sách sản phẩm
                response.sendRedirect("admin?action=viewProduct");
            } else {
                request.setAttribute("error", "Xóa sản phẩm thất bại");
                request.getRequestDispatcher("./page/adminProductList.jsp").forward(request, response);
            }
        }

        // Sửa sản phẩm
        else if ("updateProduct".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);

            request.setAttribute("product", product);
            request.getRequestDispatcher("./page/adminProductUpdateForm.jsp").forward(request, response);  // Chuyển tới form sửa
        }

        // Cập nhật sản phẩm sau khi chỉnh sửa
        else if ("updateProductSubmit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String descrip = request.getParameter("descrip");
            String category = request.getParameter("category");
            double price = Double.parseDouble(request.getParameter("price"));

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setDescrip(descrip);
            product.setCategory(category);
            product.setPrice(price);

            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.updateProduct(product);

            if (success) {
                // Sau khi cập nhật sản phẩm thành công, chuyển về danh sách sản phẩm
                response.sendRedirect("admin?action=viewProduct");
            } else {
                request.setAttribute("error", "Cập nhật sản phẩm thất bại");
                request.getRequestDispatcher("./page/adminProductUpdateForm.jsp").forward(request, response);
            }
        }

        // Hiển thị danh sách sản phẩm
        else if ("viewProduct".equals(action)) {
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("./page/adminProductList.jsp").forward(request, response);
        }

        // Chuyển đến trang thêm sản phẩm
        else if ("addProductForm".equals(action)) {
            request.getRequestDispatcher("/page/adminProductAddForm.jsp").forward(request, response);
        }
    }
}
    
    
    
    


