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
            searchEngine id database
            admin is username
            admin123 is password
        */

        String db = "searchEngine";
        String user = "admin";
        String pwd = "admin123";

        return getConnection(db,user,pwd);
    }
    private static Connection getConnection(String db,String user,String pwd){
        try {
            /*  Here, Java program is loading mySql driver to establish database connection. */
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://database-search.cpsygy8yhp2n.ap-northeast-1.rds.amazonaws.com/"+db+"?user="+user+"&password="+pwd);
        }

        /* Handle  exceptions */
        catch(Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }

}
