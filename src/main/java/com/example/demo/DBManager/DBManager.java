package com.example.demo.DBManager;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBManager //Klasse til at repræsentere DBManager service
{
    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null; //Instantierer connection

    public static Connection getConnection(){
        if (connection != null) return connection; // Hvis 'connection' allerede er etableret, så bare returnere den
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) { //Læser indstillinger til databasen som du har defineret
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url,user, password); //Prøver at connecte
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(url + "" + user + "" + password); //Hvis den fejler, så printer den url, userinfo og kode
        }
        return connection; //Returnerer connection
    }
}
