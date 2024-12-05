package dataModel;

import java.io.Serializable;
import dataDAO.*;
import java.util.List;
public class Product implements Serializable {
    private int id;
    private String name;
    private String descrip;
    private String linkImage;
    private String category;
    private double price;  // Thêm thuộc tính price

    // Constructor
    public Product() {
    }
     public  List<Product> getAllProducts() {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getAllProducts();  // Gọi tới phương thức DAO để lấy tất cả sản phẩm
    }
     
      // Phương thức lấy sản phẩm theo category
    public  List<Product> getProductsByCategory(String category) {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getProductsByCategory(category);  // Gọi tới phương thức DAO để lấy sản phẩm theo category
    }
     
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Methods: updateProduct, addProduct, delProduct (chưa được triển khai)
}
