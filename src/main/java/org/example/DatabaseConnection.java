package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    /* declare the connection */
    static  Connection connection = null;

    /* establish connection with the database by using  getConnection() method of DriverManager class. */
    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }
        /*
            sql12580037 id database
            sql12580037 is username
            tJiGrBZhQI is password
        */
        String db = "sql12580037";
        String user = "sql12580037";
        String pwd = "tJiGrBZhQI";

        return getConnection(db,user,pwd);
    }
    private static Connection getConnection(String db,String user,String pwd){
        try {
            /*  Here, Java program is loading mySql driver to establish database connection. */
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com/"+db+"?user="+user+"&password="+pwd);

        }
        /* Handle  exceptions */
        catch(Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }

}
