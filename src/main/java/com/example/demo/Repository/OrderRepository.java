package com.example.demo.Repository;

import com.example.demo.DBManager.DBManager;
import com.example.demo.DBManager.OrderException;
import com.example.demo.Model.*;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class OrderRepository {

    public Motorhome getMotorhome(int motorhomeId) throws OrderException {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM motorhome WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, motorhomeId);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                int brandId = rs.getInt("brand_id");
                int sizeId = rs.getInt("size_id");
                Motorhome motorhome = new Motorhome(id, brandId, sizeId);
                return motorhome;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                // TODO: 18/05/2020 Lav ny exception
                throw new OrderException("test exception");
            }
        }
        return null;
    }

    public Size getSize(int sizeId) throws OrderException {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM size WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, sizeId);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("size_name");
                int price = rs.getInt("price");
                Size size = new Size(id, name, price);
                return size;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                // TODO: 18/05/2020 Lav ny exception
                throw new OrderException("test exception");
            }
        }
        return null;
    }

    public void addExtra(OrderExtras orderExtras) throws OrderException {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO order_extras VALUES(default, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, orderExtras.getExtraId());
            prepStatement.setInt(2, orderExtras.getOrderId());
            prepStatement.executeUpdate();

        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new OrderException("Student already exists");
            }
        }
        // TODO: 19/05/2020 ÆNDR -1 TIL ORDER ID NÅR ORDREN BLIVER OPRETTET! so imppppooorrtatnt :c  
    }

    public void newCustomer(CustomerOrder co) throws OrderException {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO customer VALUES(default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, co.getFirstName());
            prepStatement.setString(2, co.getLastName());
            prepStatement.setInt(3, co.getTelephone());
            prepStatement.setString(4, co.getEmail());
            prepStatement.setString(5, co.getAddress());
            prepStatement.setInt(6, co.getCardInfo()); // TODO: 19/05/2020 Ændr måske? til string? måske??
            prepStatement.executeUpdate();
        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new OrderException("Woah what");
            }
        }
    }

    public int getLastCustomerId() throws OrderException {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT id FROM customer ORDER BY id DESC LIMIT 1";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                // TODO: 18/05/2020 Lav ny exception
                throw new OrderException("test exception");
            }
        }
        return -1;
    }

    public int getLastOrderId() throws OrderException {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT id FROM `order` ORDER BY id DESC LIMIT 1";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                // TODO: 18/05/2020 Lav ny exception
                throw new OrderException("test exception");
            }
        }
        return -1;
    }

    public Customer getCustomer(String existingEmail) throws OrderException {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM customer WHERE email=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, existingEmail);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int telephone = rs.getInt("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int cardInfo = rs.getInt("card_info");
                Customer customer = new Customer(id, firstName, lastName, telephone, email, address, cardInfo);
                return customer;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                // TODO: 18/05/2020 Lav ny exception
                throw new OrderException("test exception");
            }
        }
        return null;
    }

    public void newOrder(Order order) throws OrderException {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO `order` VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, order.getMotorhomeId());
            prepStatement.setInt(2, order.getCustomerId());
            prepStatement.setString(3, order.getPickup());
            prepStatement.setString(4, order.getDropoff());
            prepStatement.setString(5, order.getStartDate());
            prepStatement.setString(6, order.getEndDate());
            prepStatement.setInt(7, order.getNights());
            prepStatement.setDouble(8, order.getDeposit());
            prepStatement.executeUpdate();
        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new OrderException("Woah what");
            }
        }
    }

    // TODO: 19/05/2020 Datoer lavet til Dates og ikke String
    public ArrayList<Extras> getAllExtras() {
        ArrayList<Extras> extraArray = new ArrayList<>();

        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM extras";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                Extras extra = new Extras(id, description, price);
                extraArray.add(extra);
            }
            return extraArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Motorhome> getAllMotorhomes(){
        ArrayList<Motorhome> allMotorhomesArray = new ArrayList<>();

        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM motorhome inner join brand on motorhome.brand_id = brand.id inner join  size on motorhome.size_id =size.id";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int brand_id = rs.getInt("brand_id");
                int size_id = rs.getInt("size_id");
                String brandName = rs.getString("name");
                String sizeName = rs.getString("size_name");
                Motorhome motorhome = new Motorhome(id, brand_id, size_id, brandName, sizeName);
                allMotorhomesArray.add(motorhome);
            }
                return allMotorhomesArray;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Brand> getBrand(){
        ArrayList<Brand> getBrandArray = new ArrayList<>();

        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM brand";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Brand brand = new Brand(id, name);
                getBrandArray.add(brand);
            }
            return getBrandArray;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
