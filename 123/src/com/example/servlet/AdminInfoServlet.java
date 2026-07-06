package com.example.servlet;

import com.example.dao.InfoDAO;
import com.example.model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/info")
public class AdminInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        InfoDAO infoDAO = new InfoDAO();
        try {
            if ("approve".equals(action) && idStr != null) {
                infoDAO.updateStatus(Integer.parseInt(idStr), 1);
            } else if ("reject".equals(action) && idStr != null) {
                infoDAO.updateStatus(Integer.parseInt(idStr), -1);
            } else if ("delete".equals(action) && idStr != null) {
                infoDAO.deleteInfo(Integer.parseInt(idStr));
            } else if ("detail".equals(action) && idStr != null) {
                Info info = infoDAO.getInfoById(Integer.parseInt(idStr));
                request.setAttribute("info", info);
                request.getRequestDispatcher("/admin/info_detail.jsp").forward(request, response);
                return;
            } else if ("paid".equals(action) && idStr != null) {
                infoDAO.updatePaid(Integer.parseInt(idStr), 1);
            } else if ("free".equals(action) && idStr != null) {
                infoDAO.updatePaid(Integer.parseInt(idStr), 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/admin/index");
    }
}