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

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        InfoDAO infoDAO = new InfoDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);
            
            if (idStr != null) {
                int categoryId = Integer.parseInt(idStr);
                List<Info> infos = infoDAO.getInfoByCategory(categoryId, 1);
                Category category = categoryDAO.getCategoryById(categoryId);
                request.setAttribute("category", category);
                request.setAttribute("infos", infos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }
}