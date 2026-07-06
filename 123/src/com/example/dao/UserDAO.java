package com.example.dao;

import com.example.model.User;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY create_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setCreateTime(rs.getTimestamp("create_time"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    public User getUserById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setCreateTime(rs.getTimestamp("create_time"));
                user.setStatus(rs.getInt("status"));
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return user;
    }

    public void updateStatus(int id, int status) throws SQLException {
        String sql = "UPDATE user SET status = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }
}