/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author garmin
 */
public class database {
    
    private static String databaseName = "posplex";
    private static String username = "root";
    private static String password = "Forgetit";
    
    static Connection connection=null;
       
    public static Connection getConnection()
    {
        if (connection != null) return connection;
        return getConnection(databaseName, username, password);
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name+"?user="+user_name+"&password="+password);
            System.out.println("connected");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return connection;        
    }
    
}
