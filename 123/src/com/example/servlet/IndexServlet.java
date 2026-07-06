package com.example.servlet;

import com.example.dao.CategoryDAO;
import com.example.dao.InfoDAO;
import com.example.model.Category;
import com.example.model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InfoDAO infoDAO = new InfoDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            List<Info> paidInfos = infoDAO.getInfoByPaidStatus(1, 1);
            List<Info> freeInfos = infoDAO.getInfoByPaidStatus(0, 1);
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("paidInfos", paidInfos);
            request.setAttribute("freeInfos", freeInfos);
            request.setAttribute("categories", categories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}