package com.mastery.java.task.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler extends AppConfiguration{
    Connection dbCon;

    public Connection getDbCon() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("org.postgresql.Driver");

        dbCon = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbCon;
    }
}
