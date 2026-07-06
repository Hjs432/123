package com.example.test;

import com.example.dao.AdminDAO;
import com.example.dao.CategoryDAO;
import com.example.dao.InfoDAO;
import com.example.model.Admin;
import com.example.model.Category;
import com.example.model.Info;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("========== 都市生活网站 - 测试类 ==========");
        System.out.println();

        testDBConnection();
        System.out.println();

        testCategoryDAO();
        System.out.println();

        testInfoDAO();
        System.out.println();

        testAdminDAO();
        System.out.println();

        System.out.println("========== 测试完成 ==========");
    }

    private static void testDBConnection() {
        System.out.println("【测试1】数据库连接");
        try {
            Connection conn = DBUtil.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("  ✓ 数据库连接成功！");
                DBUtil.close(conn);
            } else {
                System.out.println("  ✗ 数据库连接失败！");
            }
        } catch (SQLException e) {
            System.out.println("  ✗ 数据库连接异常: " + e.getMessage());
        }
    }

    private static void testCategoryDAO() {
        System.out.println("【测试2】分类数据访问 (CategoryDAO)");
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            System.out.println("  ✓ 获取分类列表成功，共 " + categories.size() + " 个分类");

            for (Category category : categories) {
                System.out.println("    - ID: " + category.getId() + ", 名称: " + category.getName());
            }

            if (!categories.isEmpty()) {
                Category category = categoryDAO.getCategoryById(categories.get(0).getId());
                System.out.println("  ✓ 根据ID获取分类成功: " + category.getName());
            }
        } catch (SQLException e) {
            System.out.println("  ✗ 分类数据访问异常: " + e.getMessage());
        }
    }

    private static void testInfoDAO() {
        System.out.println("【测试3】信息数据访问 (InfoDAO)");
        InfoDAO infoDAO = new InfoDAO();
        try {
            List<Info> infos = infoDAO.getInfoList(1);
            System.out.println("  ✓ 获取已审核信息列表成功，共 " + infos.size() + " 条");

            List<Info> paidInfos = infoDAO.getInfoByPaidStatus(1, 1);
            System.out.println("  ✓ 获取付费信息列表成功，共 " + paidInfos.size() + " 条");

            List<Info> freeInfos = infoDAO.getInfoByPaidStatus(0, 1);
            System.out.println("  ✓ 获取免费信息列表成功，共 " + freeInfos.size() + " 条");

            Info testInfo = new Info();
            testInfo.setCategoryId(1);
            testInfo.setTitle("测试招聘信息");
            testInfo.setContent("这是一条测试招聘信息内容");
            testInfo.setContactPhone("13800138000");
            testInfo.setContactName("测试用户");
            testInfo.setContactEmail("test@example.com");

            infoDAO.insertInfo(testInfo);
            System.out.println("  ✓ 插入测试信息成功");

            List<Info> searchResults = infoDAO.searchInfo("测试", "fuzzy");
            System.out.println("  ✓ 模糊搜索成功，找到 " + searchResults.size() + " 条结果");

        } catch (SQLException e) {
            System.out.println("  ✗ 信息数据访问异常: " + e.getMessage());
        }
    }

    private static void testAdminDAO() {
        System.out.println("【测试4】管理员数据访问 (AdminDAO)");
        AdminDAO adminDAO = new AdminDAO();
        try {
            Admin admin = adminDAO.getAdminByUsername("admin");
            if (admin != null) {
                System.out.println("  ✓ 获取管理员信息成功");
                System.out.println("    - 用户名: " + admin.getUsername());
                System.out.println("    - 密码: " + admin.getPassword());
                System.out.println("    - 角色: " + admin.getRole());

                if ("123456".equals(admin.getPassword())) {
                    System.out.println("  ✓ 默认管理员密码验证通过");
                } else {
                    System.out.println("  ✗ 默认管理员密码不正确");
                }
            } else {
                System.out.println("  ✗ 未找到管理员账号");
            }
        } catch (SQLException e) {
            System.out.println("  ✗ 管理员数据访问异常: " + e.getMessage());
        }
    }
}