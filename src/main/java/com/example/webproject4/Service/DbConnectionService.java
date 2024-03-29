package com.example.webproject4.Service;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class DbConnectionService {

    //Establish the connection with database
    @SneakyThrows
    public Connection connect(){
        String url="jdbc:sqlserver://webproject.database.windows.net:1433;DatabaseName=DB1;"; //Connection string
        String username="admin1";
        String password="Manager1";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url,username,password);
            if (connection != null) { //Check the connection is ok
                System.out.println("Connected to the database!");

            } else {
                System.out.println("Failed to connect to the database!");
            }


        return connection;
    }
}
