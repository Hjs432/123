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
import java.util.List;

@WebServlet("/admin/index")
public class AdminIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        InfoDAO infoDAO = new InfoDAO();
        try {
            List<Info> pendingInfos = infoDAO.getInfoList(0);
            List<Info> approvedInfos = infoDAO.getInfoList(1);
            request.setAttribute("pendingInfos", pendingInfos);
            request.setAttribute("approvedInfos", approvedInfos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }
}