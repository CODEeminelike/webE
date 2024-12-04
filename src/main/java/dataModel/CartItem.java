/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataModel;


import java.io.Serializable;

public class CartItem implements Serializable {
    private int iD;
    private int cartID;
    private Product product;
    private int mount;

    // Constructor
    public CartItem() {
    }
    
    
    
    
    // Getters and setters
    
    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    // Methods: getTotalPrice, thêm/bớt product (chưa được triển khai)
}