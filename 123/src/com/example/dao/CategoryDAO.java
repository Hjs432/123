package com.example.dao;

import com.example.model.Category;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> getAllCategories() throws SQLException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE status = 1 ORDER BY sort_order";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setParentId(rs.getInt("parent_id"));
                category.setSortOrder(rs.getInt("sort_order"));
                category.setStatus(rs.getInt("status"));
                list.add(category);
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    public Category getCategoryById(int id) throws SQLException {
        Category category = null;
        String sql = "SELECT * FROM category WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setParentId(rs.getInt("parent_id"));
                category.setSortOrder(rs.getInt("sort_order"));
                category.setStatus(rs.getInt("status"));
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return category;
    }
}