package com.example.dao;

import com.example.model.Admin;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public Admin getAdminByUsername(String username) throws SQLException {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE username = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setRole(rs.getString("role"));
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return admin;
    }
}