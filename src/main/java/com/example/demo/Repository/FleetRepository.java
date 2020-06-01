package com.example.demo.Repository;

import com.example.demo.DBManager.DBManager;
import com.example.demo.Model.Motorhome;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class FleetRepository {

    public ArrayList<Motorhome> getAllMotorhomes()
    {
        ArrayList<Motorhome> allMotorhomesArray = new ArrayList<>();

        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM motorhome inner join brand on motorhome.brand_id = brand.id inner join  size on motorhome.size_id =size.id";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet rs = prepStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int brand_id = rs.getInt("brand_id");
                int size_id = rs.getInt("size_id");
                String img = rs.getString("img");
                String brandName = rs.getString("name");
                String sizeName = rs.getString("size_name");
                int sizePrice = rs.getInt("price");
                Motorhome motorhome = new Motorhome(id, brand_id, size_id, img, brandName, sizeName, sizePrice);
                allMotorhomesArray.add(motorhome);
            }
            return allMotorhomesArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                String brandName = rs.getString("name");
                String sizeName = rs.getString("size_name");
                int price = rs.getInt("price");
                Motorhome motorhome = new Motorhome(motorhomeId, brand_id, size_id, brandName, sizeName, price);
                return motorhome;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                Motorhome motorhome = new Motorhome(id, brandId, sizeId);
                return motorhome;
            }

        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                e.printStackTrace();
            }
        }
        return null;
    }

}
