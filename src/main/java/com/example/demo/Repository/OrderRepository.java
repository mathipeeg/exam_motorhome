package com.example.demo.Repository;

import com.example.demo.DBManager.DBManager;
import com.example.demo.DBManager.TestException;
import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Size;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class OrderRepository {

    public Motorhome getMotorhome(int motorhomeId) throws TestException {
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
                throw new TestException("test exception");
            }
        }
        return null;
    }

    public Size getSize(int sizeId) throws TestException {
        try{
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM size WHERE id=?";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, sizeId);
            ResultSet rs = prepStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                Size size = new Size(id, name, price);
                return size;
            }
        } catch(SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
                // TODO: 18/05/2020 Lav ny exception
                throw new TestException("test exception");
            }
        }
        return null;
    }
}
