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

@WebServlet("/publish")
public class PublishServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/publish.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String contactPhone = request.getParameter("contactPhone");
        String contactName = request.getParameter("contactName");
        String contactEmail = request.getParameter("contactEmail");

        Info info = new Info();
        info.setCategoryId(categoryId);
        info.setTitle(title);
        info.setContent(content);
        info.setContactPhone(contactPhone);
        info.setContactName(contactName);
        info.setContactEmail(contactEmail);

        InfoDAO infoDAO = new InfoDAO();
        try {
            infoDAO.insertInfo(info);
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "发布失败，请重试");
            doGet(request, response);
        }
    }
}