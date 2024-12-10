package dataDAO;

import dataModel.Orders;
import appUtil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    public boolean addOrder(Orders order) { 
        String query = "INSERT INTO orders (date, time, email, address, totalPrice) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);

            statement.setDate(1, order.getDate());
            statement.setTime(2, order.getTime());
            statement.setString(3, order.getEmail());
            statement.setString(4, order.getAddress());
            statement.setDouble(5, order.getTotalPrice());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeConnection();
            closeResources(statement, null);
        }
    }

    public List<Orders> getOrderByEmail(String email) throws SQLException {
        String query = "SELECT * FROM orders WHERE email = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Orders> orders = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("date"));
                order.setTime(resultSet.getTime("time"));
                order.setEmail(resultSet.getString("email"));
                order.setAddress(resultSet.getString("address"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
                orders.add(order);
            }
        } finally {
            DBUtil.closeConnection();
            closeResources(statement, resultSet);
        }

        return orders;
    }

    public Orders getOrderById(int id) throws SQLException {
        String query = "SELECT * FROM orders WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Orders order = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("date"));
                order.setTime(resultSet.getTime("time"));
                order.setEmail(resultSet.getString("email"));
                order.setAddress(resultSet.getString("address"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
            }
        } finally {
            DBUtil.closeConnection();
            closeResources(statement, resultSet);
        }

        return order;
    }

    public List<Orders> getAllOrders() throws SQLException {
        String query = "SELECT * FROM orders";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Orders> orders = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("date"));
                order.setTime(resultSet.getTime("time"));
                order.setEmail(resultSet.getString("email"));
                order.setAddress(resultSet.getString("address"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
                orders.add(order);
            }
        } finally {
            DBUtil.closeConnection();
            closeResources(statement, resultSet);
        }

        return orders;
    }

    private void closeResources(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
