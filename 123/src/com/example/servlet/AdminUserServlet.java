package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/user")
public class AdminUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        UserDAO userDAO = new UserDAO();
        try {
            if ("enable".equals(action) && idStr != null) {
                userDAO.updateStatus(Integer.parseInt(idStr), 1);
            } else if ("disable".equals(action) && idStr != null) {
                userDAO.updateStatus(Integer.parseInt(idStr), 0);
            } else if ("delete".equals(action) && idStr != null) {
                userDAO.deleteUser(Integer.parseInt(idStr));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
    }
}