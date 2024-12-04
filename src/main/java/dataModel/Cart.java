/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataModel;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private List<CartItem> cartItem;
    private int customterID;

    // Constructor
    public Cart() {
    }

     

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public int getCustomterID() {
        return customterID;
    }

    public void setCustomterID(int customterID) {
        this.customterID = customterID;
    }

    // Methods: gettotalPrice, thêm/bớt cartItem, showListItem, Payment (chưa được triển khai)
}