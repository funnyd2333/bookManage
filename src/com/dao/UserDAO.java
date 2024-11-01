package com.dao;

import com.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public User login(Connection con, User user) throws Exception {
        //登录验证
        User r_user = null;
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs= ps.executeQuery();
        if(rs.next()) {
            r_user = new User();
            r_user.setId(rs.getInt("id"));
            r_user.setUsername(rs.getString("username"));
            r_user.setPassword(rs.getString("password"));
        }
        return r_user;
    }
}
