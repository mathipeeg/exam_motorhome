package com.example.demo.Repository;

import com.example.demo.DBManager.*;
import com.example.demo.Model.*;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class OrderRepository {

    public Size getSize(int sizeId){
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM size WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, sizeId);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("size_name");
                int price = rs.getInt("price");
                Size size = new Size(id, name, price);
                return size;
            }
        } catch(SQLException e){
            throw new IllegalArgumentException("ID does not exist in database.");
        }
        return null;
    }

    public void addExtra(OrderExtras orderExtras) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO order_extras VALUES(default, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, orderExtras.getExtraId());
            prepStatement.setInt(2, orderExtras.getOrderId());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Connection to the database failed. Try again later.");
        }
    }

    public void newCustomer(CustomerOrder co){ //UNIK E-MAIL
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO customer VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, co.getFirstName());
            prepStatement.setString(2, co.getLastName());
            prepStatement.setInt(3, co.getTelephone());
            prepStatement.setString(4, co.getEmail());
            prepStatement.setString(5, co.getAddress());
            prepStatement.setString(6, co.getCardInfo());
            prepStatement.setDate(7, new java.sql.Date(co.getExpDate().getTime()));
            prepStatement.setInt(8, co.getCvs());
            prepStatement.executeUpdate();
        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new IllegalArgumentException("Your input was illegal and couldn't be added to the database.");
            }
        }
    }

    public int getLastCustomerId() {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT id FROM customer ORDER BY id DESC LIMIT 1";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new DatabaseException("Connection to the database failed. Try again later.");
            }
        }
        return -1;
    }


    public int getLastOrderId() {
        try{

            Connection connection = DBManager.getConnection();
            String sql = "SELECT id FROM `order` ORDER BY id DESC LIMIT 1";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                throw new DatabaseException("Connection to the database failed. Try again later.");
            }
        }
        return -1;
    }

    public Customer getCustomer(String existingEmail) {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM customer WHERE email=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, existingEmail);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int telephone = rs.getInt("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cardInfo = rs.getString("card_info");
                Date expDate = rs.getDate("card_date");
                int cardCvs = rs.getInt("card_cvs");
                Customer customer = new Customer(id, firstName, lastName, telephone, email, address, cardInfo, expDate, cardCvs);
                return customer;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                throw new IllegalArgumentException("E-mail doesn't exist in database.");
            }
        }
        return null;
    }

    public void newOrder(Order order) {

        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO `order` VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, order.getMotorhomeId());
            prepStatement.setInt(2, order.getCustomerId());
            prepStatement.setString(3, order.getPickup());
            prepStatement.setString(4, order.getDropoff());
            prepStatement.setDate(5, new java.sql.Date(order.getStartDate().getTime()));
            prepStatement.setDate(6, new java.sql.Date(order.getEndDate().getTime()));
            prepStatement.setInt(7, order.getNights());
            prepStatement.setDouble(8, order.getDeposit());
            prepStatement.executeUpdate();

        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new NullPointerException("Adding order failed, as a value was null.");
            }
        }
    }

    public ArrayList<Extras> getAllExtras() {
        ArrayList<Extras> extraArray = new ArrayList<>();

        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM extras";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                Extras extra = new Extras(id, description, price);
                extraArray.add(extra);
            }
            return extraArray;
        } catch (SQLException e) {
            throw new DatabaseException("Connection to the database failed. Try again later.");
        }
    }


    public Order getOrder(int id) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM `order` WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("id");
                int motorhomeId = rs.getInt("motorhome_id");
                int customerId = rs.getInt("customer_id");
                String pickUp = rs.getString("pickup");
                String dropOff = rs.getString("dropoff");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                int nights = rs.getInt("nights");
                int deposit = rs.getInt("deposit");


                Order order = new Order(orderId, motorhomeId, customerId, pickUp, dropOff, startDate, endDate, nights, deposit);
                return order;
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) { //Undersøg lige den her exception
                throw new IllegalArgumentException("ID doesn't exist in database.");
            }
        }
        return null;
    }

    public Customer getCustomer(int id){
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM customer WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int telephone = rs.getInt("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cardInfo = rs.getString("card_info");
                Date expDate = rs.getDate("card_date");
                int cardCvs = rs.getInt("card_cvs");
                Customer customer = new Customer(customerId, firstName, lastName, telephone, email, address, cardInfo, expDate, cardCvs);
                return customer;
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) { //Undersøg lige den her exception
                throw new IllegalArgumentException("ID doesn't exist in database.");
            }
        }
        return null;
    }

    public ArrayList<OrderExtras> getOrderExtra(int orderId) {
        ArrayList<OrderExtras> getOrderExtrasArray = new ArrayList<>();

        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM order_extras INNER JOIN extras ON order_extras.extra_id = extras.id WHERE order_extras.order_id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, orderId);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                int extraId = rs.getInt("id");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                OrderExtras orderExtras = new OrderExtras(extraId, description, price);
                getOrderExtrasArray.add(orderExtras);
            }
            return getOrderExtrasArray;

        } catch (SQLException e) {
            throw new IllegalArgumentException("ID doesn't exist in database.");
        }
    }
}
