package com.example.demo.Persistencia;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    /*
   Default env:
   DATABASE_HOST=localhost;DATABASE_PORT=5432;DATABASE_NAME=crud_pessoa;DATABASE_USERNAME=postgres;DATABASE_PASSWORD=1234
   */
    public static Connection getConnection() throws SQLException, URISyntaxException {
        //String dbUri = System.getenv("DATABASE_HOST");
        //String dbPort = System.getenv("DATABASE_PORT");
        //String dbName = System.getenv("DATABASE_NAME");

        String username = System.getenv("DATABASE_USERNAME");
        String password = System.getenv("DATABASE_PASSWORD");
        String dbUrl = System.getenv("DATABASE_URL");

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
