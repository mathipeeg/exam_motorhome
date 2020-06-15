package com.example.demo.Repository;

import com.example.demo.DBManager.*;
import com.example.demo.Model.*;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class FleetRepository {

    public ArrayList<Motorhome> getAllMotorhomes() {
        ArrayList<Motorhome> allMotorhomesArray = new ArrayList<>();

        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM motorhome inner join brand on motorhome.brand_id = brand.id inner join size on motorhome.size_id = size.id";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int brand_id = rs.getInt("brand_id");
                int size_id = rs.getInt("size_id");
                String img = rs.getString("img");
                int amount = rs.getInt("amount");
                String brandName = rs.getString("name");
                String sizeName = rs.getString("size_name");
                int sizePrice = rs.getInt("price");
                Motorhome motorhome = new Motorhome(id, brand_id, size_id, img, amount, brandName, sizeName, sizePrice);
                allMotorhomesArray.add(motorhome);
            }
            return allMotorhomesArray;
        } catch (SQLException e) {
           throw new DatabaseException("Database couldn't be reached or your input was not correct.");
        }
    }

    public Motorhome getMotorhomeInfo(int id)
    {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM motorhome INNER JOIN brand ON motorhome.brand_id = brand.id INNER JOIN size on motorhome.size_id = size.id WHERE motorhome.id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            ResultSet rs = prepStatement.executeQuery();

            if (rs.next()) {
                int motorhomeId = rs.getInt("id");
                int brand_id = rs.getInt("brand_id");
                int size_id = rs.getInt("size_id");
                String imgPath = rs.getString("img");
                int amount = rs.getInt("amount");
                String brandName = rs.getString("name");
                String sizeName = rs.getString("size_name");
                int price = rs.getInt("price");
                Motorhome motorhome = new Motorhome(motorhomeId, brand_id, size_id, imgPath, amount, brandName, sizeName, price);
                return motorhome;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("ID doesn't exist.");
        }
        return null;
    }

    public Motorhome getMotorhome(int motorhomeId)
    {
        try {

            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM motorhome WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, motorhomeId);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int brandId = rs.getInt("brand_id");
                int sizeId = rs.getInt("size_id");
                String imgPath = rs.getString("img");
                int amount = rs.getInt("amount");
                Motorhome motorhome = new Motorhome(id, brandId, sizeId, imgPath, amount);
                return motorhome;
            }

        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                throw new IllegalArgumentException("ID doesn't exist.");
            }
        }
        return null;
    }

    public void bookMotorhome(BookedMotorhome booking) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO booked_motorhomes VALUES(default, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, booking.getMotorhomeId());
            prepStatement.setDate(2, new java.sql.Date(booking.getStartDate().getTime()));
            prepStatement.setDate(3, new java.sql.Date(booking.getEndDate().getTime()));
            prepStatement.executeUpdate();
        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new IllegalArgumentException("Your input was illegal and couldn't be added to the database.");
            }
        }
    }

    public ArrayList<BookedMotorhome> getAllBookedHomes() {
        ArrayList<BookedMotorhome> allMotorhomesArray = new ArrayList<>();

        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM booked_motorhomes";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int motorhomeId = rs.getInt("motorhome_id");
                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");
                BookedMotorhome booked = new BookedMotorhome(id, motorhomeId, start, end);
                allMotorhomesArray.add(booked);
            }
            return allMotorhomesArray;
        } catch (SQLException e) {
            throw new DatabaseException("Connection to database failed");
        }
    }

    public void removeBookedHome(BookedMotorhome booked) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM booked_motorhomes WHERE id = ?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, booked.getId());
            prepStatement.executeUpdate();
        }catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new DatabaseException("Connection to the database failed.");
            }
        }
    }
}
