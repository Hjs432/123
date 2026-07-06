package com.example.dao;

import com.example.model.Info;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDAO {
    public List<Info> getInfoList(int status) throws SQLException {
        List<Info> list = new ArrayList<>();
        String sql = "SELECT i.*, c.name as categoryName FROM info i JOIN category c ON i.category_id = c.id WHERE i.status = ? ORDER BY i.publish_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, status);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getInt("id"));
                info.setCategoryId(rs.getInt("category_id"));
                info.setTitle(rs.getString("title"));
                info.setContent(rs.getString("content"));
                info.setContactPhone(rs.getString("contact_phone"));
                info.setContactName(rs.getString("contact_name"));
                info.setContactEmail(rs.getString("contact_email"));
                info.setPublishTime(rs.getTimestamp("publish_time"));
                info.setStatus(rs.getInt("status"));
                info.setIsPaid(rs.getInt("is_paid"));
                info.setViews(rs.getInt("views"));
                info.setCategoryName(rs.getString("categoryName"));
                list.add(info);
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<Info> getInfoByCategory(int categoryId, int status) throws SQLException {
        List<Info> list = new ArrayList<>();
        String sql = "SELECT i.*, c.name as categoryName FROM info i JOIN category c ON i.category_id = c.id WHERE i.category_id = ? AND i.status = ? ORDER BY i.publish_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, status);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getInt("id"));
                info.setCategoryId(rs.getInt("category_id"));
                info.setTitle(rs.getString("title"));
                info.setContent(rs.getString("content"));
                info.setContactPhone(rs.getString("contact_phone"));
                info.setContactName(rs.getString("contact_name"));
                info.setContactEmail(rs.getString("contact_email"));
                info.setPublishTime(rs.getTimestamp("publish_time"));
                info.setStatus(rs.getInt("status"));
                info.setIsPaid(rs.getInt("is_paid"));
                info.setViews(rs.getInt("views"));
                info.setCategoryName(rs.getString("categoryName"));
                list.add(info);
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<Info> searchInfo(String keyword, String condition) throws SQLException {
        List<Info> list = new ArrayList<>();
        String sql = "SELECT i.*, c.name as categoryName FROM info i JOIN category c ON i.category_id = c.id WHERE i.status = 1 ";
        if ("full".equals(condition)) {
            sql += "AND (i.title = ? OR i.content = ?)";
        } else {
            sql += "AND (i.title LIKE ? OR i.content LIKE ?)";
        }
        sql += " ORDER BY i.publish_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            if ("full".equals(condition)) {
                pstmt.setString(1, keyword);
                pstmt.setString(2, keyword);
            } else {
                pstmt.setString(1, "%" + keyword + "%");
                pstmt.setString(2, "%" + keyword + "%");
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getInt("id"));
                info.setCategoryId(rs.getInt("category_id"));
                info.setTitle(rs.getString("title"));
                info.setContent(rs.getString("content"));
                info.setContactPhone(rs.getString("contact_phone"));
                info.setContactName(rs.getString("contact_name"));
                info.setContactEmail(rs.getString("contact_email"));
                info.setPublishTime(rs.getTimestamp("publish_time"));
                info.setStatus(rs.getInt("status"));
                info.setIsPaid(rs.getInt("is_paid"));
                info.setViews(rs.getInt("views"));
                info.setCategoryName(rs.getString("categoryName"));
                list.add(info);
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    public Info getInfoById(int id) throws SQLException {
        Info info = null;
        String sql = "SELECT i.*, c.name as categoryName FROM info i JOIN category c ON i.category_id = c.id WHERE i.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                info = new Info();
                info.setId(rs.getInt("id"));
                info.setCategoryId(rs.getInt("category_id"));
                info.setTitle(rs.getString("title"));
                info.setContent(rs.getString("content"));
                info.setContactPhone(rs.getString("contact_phone"));
                info.setContactName(rs.getString("contact_name"));
                info.setContactEmail(rs.getString("contact_email"));
                info.setPublishTime(rs.getTimestamp("publish_time"));
                info.setStatus(rs.getInt("status"));
                info.setIsPaid(rs.getInt("is_paid"));
                info.setViews(rs.getInt("views"));
                info.setCategoryName(rs.getString("categoryName"));
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return info;
    }

    public void insertInfo(Info info) throws SQLException {
        String sql = "INSERT INTO info (category_id, title, content, contact_phone, contact_name, contact_email) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, info.getCategoryId());
            pstmt.setString(2, info.getTitle());
            pstmt.setString(3, info.getContent());
            pstmt.setString(4, info.getContactPhone());
            pstmt.setString(5, info.getContactName());
            pstmt.setString(6, info.getContactEmail());
            pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public void updateStatus(int id, int status) throws SQLException {
        String sql = "UPDATE info SET status = ? WHERE id = ?";
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

    public void deleteInfo(int id) throws SQLException {
        String sql = "DELETE FROM info WHERE id = ?";
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

    public void updatePaid(int id, int isPaid) throws SQLException {
        String sql = "UPDATE info SET is_paid = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, isPaid);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public void addView(int id) throws SQLException {
        String sql = "UPDATE info SET views = views + 1 WHERE id = ?";
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

    public List<Info> getInfoByPaidStatus(int isPaid, int status) throws SQLException {
        List<Info> list = new ArrayList<>();
        String sql = "SELECT i.*, c.name as categoryName FROM info i JOIN category c ON i.category_id = c.id WHERE i.is_paid = ? AND i.status = ? ORDER BY i.publish_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, isPaid);
            pstmt.setInt(2, status);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getInt("id"));
                info.setCategoryId(rs.getInt("category_id"));
                info.setTitle(rs.getString("title"));
                info.setContent(rs.getString("content"));
                info.setContactPhone(rs.getString("contact_phone"));
                info.setContactName(rs.getString("contact_name"));
                info.setContactEmail(rs.getString("contact_email"));
                info.setPublishTime(rs.getTimestamp("publish_time"));
                info.setStatus(rs.getInt("status"));
                info.setIsPaid(rs.getInt("is_paid"));
                info.setViews(rs.getInt("views"));
                info.setCategoryName(rs.getString("categoryName"));
                list.add(info);
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }
}