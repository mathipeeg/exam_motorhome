package com.example.demo.Repository;

import com.example.demo.DBManager.*;
import com.example.demo.Model.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserRepository {
    public boolean checkLogin(String loginEmail, String loginPassword){
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM staff WHERE email=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, loginEmail);
            ResultSet rs = prepStatement.executeQuery();
            String password = null;

            if(rs.next()) {
                password = rs.getString("password");

            }
            if (password != null) {
                if (BCrypt.checkpw(loginPassword, password)) {
                    return true;
                }
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                try {
                    throw new LoginException("Error occured. Couldn't login.");
                } catch (LoginException loginException) {
                    loginException.printStackTrace();
                }
            }
        }
        return false;
    }

    public Staff getStaff(String loginEmail, String loginPassword) {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM staff WHERE email=? AND password=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, loginEmail);
            prepStatement.setString(2, loginPassword);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int telephone = rs.getInt("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String position = rs.getString("position");
                String password = rs.getString("password");
                Staff staff = new Staff(id, firstName, lastName, telephone, email, address, position, password);
                return staff;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                try {
                    throw new LoginException("Error occured. Couldn't find user with those credentials.");
                } catch (LoginException loginException) {
                    loginException.printStackTrace();
                }
            }
        }
        return null;
    }

    public ArrayList<Staff> getAllStaff(){
        ArrayList<Staff> staffArray = new ArrayList<>();

        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM staff";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int telephone = rs.getInt("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String position = rs.getString("position");
                String password = rs.getString("password");
                Staff staff = new Staff(id, firstName, lastName, telephone, email, address, position, password);
                staffArray.add(staff);
            }
            return staffArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePassword(Staff staff) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "UPDATE staff SET password=? WHERE id=?";

            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, staff.getPassword());
            prepStatement.setInt(2, staff.getId());
            prepStatement.executeUpdate();
        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                try {
                    throw new CustomException("Student idk can't be updated lmao");
                } catch (CustomException customException) {
                    customException.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customerArray = new ArrayList<>();

        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM customer";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int telephone = rs.getInt("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cardInfo = rs.getString("card_info");
                Date cardDate = rs.getDate("card_date");
                int cvs = rs.getInt("card_cvs");
                Customer customer = new Customer(id, firstName, lastName, telephone, email, address, cardInfo, cardDate, cvs);
                customerArray.add(customer);
            }
            return customerArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
