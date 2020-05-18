package com.example.demo.Repository;

import com.example.demo.DBManager.DBManager;
import com.example.demo.DBManager.TestException;
import com.example.demo.Model.Test;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository //Undersøg hvad det her helt præcist gør
public class testRepo {





    //TEST / EXAMPLE
//    public void createTest(Test test) throws TestException {
//        try{
//            Connection connection = DBManager.getConnection();
//            String sql = "insert into test_table values(?)";
//            PreparedStatement prepStatement = connection.prepareStatement(sql);
//            prepStatement.setString(1, test.getTest_column());
//            prepStatement.executeUpdate();
//
//        } catch(SQLException e){
//            if(e instanceof SQLIntegrityConstraintViolationException){ //Undersøg lige den her exception
//                throw new TestException("test exception");
//            }
//        }
//    }
}


