/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.customerM.dal;

import java.sql.*;

/**
 *
 * @author f0fp1241
 */
public class ConnectionModule {
    // method responsible for establishing the connection with the base
    public static Connection conector(){
       Connection connection = null;
       
       // the under row "call" the driver
       String driver = "com.mysql.jdbc.Driver";
       
       // storing information regarding the bank
       String url = "jdbc:mysql://localhost:3306/dbmanagement";
       String user = "root";
       String password = "";
       
       // Establishing the connection with the base
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
       
    }
    
}
