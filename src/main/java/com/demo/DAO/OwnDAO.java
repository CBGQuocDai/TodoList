package com.demo.DAO;

import com.demo.util.JdbcConnetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OwnDAO {
    private static final String ADD_OWN = "INSERT INTO own(email,tid) VALUES(?,?)";
    public void add(String email, int tid) {
        try {
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_OWN);
            ps.setString(1, email);
            ps.setInt(2, tid);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}