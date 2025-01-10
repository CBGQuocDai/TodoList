package com.demo.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnetion extends HttpServlet {

    public JdbcConnetion() {
    }

    public Connection getConnection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/todo";
        String username = "root";
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(jdbcUrl,username, System.getenv("ROOT_MYSQL"));
        }
        catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
