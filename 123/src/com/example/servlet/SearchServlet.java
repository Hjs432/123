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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        String condition = request.getParameter("condition");
        String searchType = request.getParameter("searchType");
        
        InfoDAO infoDAO = new InfoDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);
            
            if (keyword != null && !keyword.isEmpty()) {
                List<Info> infos = infoDAO.searchInfo(keyword, "full".equals(searchType) ? "full" : "fuzzy");
                request.setAttribute("infos", infos);
                request.setAttribute("keyword", keyword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/search_result.jsp").forward(request, response);
    }
}