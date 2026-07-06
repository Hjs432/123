package com.example.servlet;

import com.example.dao.AdminDAO;
import com.example.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AdminDAO adminDAO = new AdminDAO();
        try {
            Admin admin = adminDAO.getAdminByUsername(username);
            if (admin != null && admin.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                response.sendRedirect(request.getContextPath() + "/admin/index");
            } else {
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "登录失败，请重试");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        }
    }
}