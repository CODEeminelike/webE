/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataModel;



import java.sql.Date;
import java.sql.Time;

public class Orders {
    private int id;
    private Date date;
    private Time time;
    private String email;
    private String address;
    private double totalPrice;

    public Orders(int id, Date date, Time time, String email, String address, double totalPrice) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public Orders() {
    }

    
    
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
