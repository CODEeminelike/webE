/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataModel;

import java.io.Serializable;

public class Customer implements Serializable {
    private int iD;
    private String fname;
    private String lname;
    private String email; // unique
    private int cartID; // unique
    private String password;

    // Constructor
    public Customer() {
    }

    // Getters and setters

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname  = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password  = password;
    }

    // Methods: login, register, updateProfile (chưa được triển khai)
}