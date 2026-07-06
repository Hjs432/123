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

@WebServlet("/admin/search")
public class AdminSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");

        InfoDAO infoDAO = new InfoDAO();
        try {
            List<Info> infos = infoDAO.searchInfo(keyword, "fuzzy");
            request.setAttribute("infos", infos);
            request.setAttribute("keyword", keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/admin/search_result.jsp").forward(request, response);
    }
}