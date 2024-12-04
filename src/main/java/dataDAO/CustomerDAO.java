/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataDAO;

import dataModel.*;
import appUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    // Phương thức thêm mới khách hàng
    public boolean addCustomer(Customer customer) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Customer (fname, lname, email, cartID, password) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, customer.getFname());
            statement.setString(2, customer.getLname());
            statement.setString(3, customer.getEmail());
            statement.setInt(4, customer.getCartID());
            statement.setString(5, customer.getPassword());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy thông tin khách hàng theo ID
    public Customer getCustomerById(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer WHERE iD = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setID(resultSet.getInt("iD"));
                    customer.setFname(resultSet.getString("fname"));
                    customer.setLname(resultSet.getString("lname"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setCartID(resultSet.getInt("cartID"));
                    customer.setPassword(resultSet.getString("password"));
                    return customer;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật thông tin khách hàng
    public boolean updateCustomer(Customer customer) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Customer SET fname = ?, lname = ?, email = ?, cartID = ?, password = ? WHERE iD = ?")) {

            statement.setString(1, customer.getFname());
            statement.setString(2, customer.getLname());
            statement.setString(3, customer.getEmail());
            statement.setInt(4, customer.getCartID());
            statement.setString(5, customer.getPassword());
            statement.setInt(6, customer.getID());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa khách hàng
    public boolean deleteCustomer(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Customer WHERE iD = ?")) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   

    // Phương thức lấy thông tin khách hàng theo email
    public Customer getCustomerByEmail(String email) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer WHERE email = ?")) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setID(resultSet.getInt("iD"));
                    customer.setFname(resultSet.getString("fname"));
                    customer.setLname(resultSet.getString("lname"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setCartID(resultSet.getInt("cartID"));
                    customer.setPassword(resultSet.getString("password"));
                    return customer;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}