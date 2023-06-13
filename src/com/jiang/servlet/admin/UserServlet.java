package com.jiang.servlet.admin;

import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/user/getallusers")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUsers = null;
        try {
            allUsers = userService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("users",allUsers);
        request.getRequestDispatcher("/admin/user-list.jsp").forward(request,response);
    }
}
