package com.demo.DAO;

import com.demo.model.User;
import com.demo.util.JdbcConnetion;
import java.sql.*;

public class UserDAO {

    private static final String GET_USER_BY_EMAIL = "SELECT * FROM User WHERE email = ?";
    private static final String ADD_USER = "INSERT INTO User (password, email) VALUES (?, ?)";
    private static final String UPDATE_USER = "UPDATE User SET password = ? WHERE email = ?";
    private static final String ACTIVE_ACCOUNT = "UPDATE User SET verify = 1 WHERE email = ?";
    public User getUserByEmail(String email){
        User user = null;
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps =connection.prepareStatement(GET_USER_BY_EMAIL);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("password"),rs.getString("email"),rs.getInt("verify"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public void updateUser(User user){
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1,user.getPassword());
            ps.setString(2,user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addUser(User user){
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_USER);
            ps.setString(1,user.getPassword());
            ps.setString(2,user.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void activeAccount(String email){
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps= connection.prepareStatement(ACTIVE_ACCOUNT);
            ps.setString(1,email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
