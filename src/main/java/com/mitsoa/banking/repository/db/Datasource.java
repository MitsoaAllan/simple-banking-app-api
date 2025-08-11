package com.mitsoa.banking.repository.db;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class Datasource {
    private static final int port = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 5432;
    private static final String host = System.getenv("HOST") != null ? System.getenv("HOST") : "localhost";
    private static final String database = System.getenv("DATABASE_NAME");
    private static final String username = System.getenv("DATABASE_USERNAME");
    private static final String password = System.getenv("DATABASE_PASSWORD");
    private final String url;

    public Datasource() {
        url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
