/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Asus
 */
public class ConnectDatabase {
    ResultSet resultSet;
    
    public ResultSet connectDB(String query){
try {
    
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PharmacyManagementSystem;selectMethod=cursor", "sa", "123456");
java.sql.Statement statement = connection.createStatement();

resultSet = statement.executeQuery(query);
return resultSet;
} catch (Exception e) {
//e.printStackTrace();
return null;
}
}
     public void  connectDBUpdate(String query) throws SQLException,ClassNotFoundException{
         
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PharmacyManagementSystem;selectMethod=cursor", "sa", "123456");
java.sql.Statement statement = connection.createStatement();
         statement.executeUpdate(query);
} 
     
}
