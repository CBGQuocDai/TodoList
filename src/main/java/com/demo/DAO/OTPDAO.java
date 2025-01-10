package com.demo.DAO;

import com.demo.model.OTP;
import com.demo.util.JdbcConnetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OTPDAO {
    private static final String ADD_OTP = "INSERT INTO OTP(email,password,create_at,expire_at,is_verify) VALUES (?,?,?,?,?)";
    private static final String GET_OTP = "SELECT * FROM OTP WHERE email=? ORDER BY id DESC LIMIT 1";
    public void addOTP(OTP otp){
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_OTP);
            ps.setString(1, otp.getEmail());
            ps.setString(2, otp.getPassword());
            ps.setTimestamp(3, otp.getCreateAt());
            ps.setTimestamp(4, otp.getExpireAt());
            ps.setInt(5,otp.getIsVerified());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public OTP getOTP(String email){
        OTP otp = null;
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps =connection.prepareStatement(GET_OTP);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                otp = new OTP();
                otp.setEmail(rs.getString("email"));
                otp.setPassword(rs.getString("password"));
                otp.setCreateAt(rs.getTimestamp("create_at"));
                otp.setExpireAt(rs.getTimestamp("expire_at"));
                otp.setId(rs.getInt("id"));
                otp.setVerified(rs.getInt("is_verify"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return otp;
    }
}
