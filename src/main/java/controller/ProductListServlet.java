package controller;

import dataModel.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số category từ URL (nếu có)
        String category = request.getParameter("category");

        // Nếu category không có giá trị thì mặc định là "Tất cả"
        if (category == null) {
            category = "Tất cả";
        }
        Product pro = new Product();
        // Lọc sản phẩm theo category
        List<Product> productList;
        if (category.equals("Tất cả")) {
            productList = pro.getAllProducts(); // Lấy tất cả sản phẩm
        } else {
            productList = pro.getProductsByCategory(category); // Lấy sản phẩm theo category
            
        }
        
        // Đưa danh sách sản phẩm và category vào request để truyền tới JSP
        request.setAttribute("productList", productList);
        request.setAttribute("category", category); // Lưu category vào để duy trì trong URL

        // Chuyển hướng đến JSP để hiển thị kết quả
        RequestDispatcher dispatcher = request.getRequestDispatcher("/page/productList.jsp");
        dispatcher.forward(request, response);
    }
}
