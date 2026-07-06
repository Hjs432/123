package com.example.servlet;

import com.example.dao.InfoDAO;
import com.example.model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/info/detail")
public class InfoDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            InfoDAO infoDAO = new InfoDAO();
            try {
                Info info = infoDAO.getInfoById(id);
                if (info != null) {
                    infoDAO.addView(id);
                    request.setAttribute("info", info);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/info_detail.jsp").forward(request, response);
    }
}