package com.jiang.servlet.client;

import com.jiang.pojo.Comment;
import com.jiang.pojo.User;
import com.jiang.service.CommentService;
import com.jiang.service.impl.CommentServiceImpl;
import com.jiang.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/client/add")
public class CommentServlet extends HttpServlet {
    private CommentService commentService = new CommentServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid = request.getParameter("productid");
        String text = request.getParameter("text");
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();

        Comment comment = new Comment();

        comment.setProductid(productid);
        comment.setText(text);
        comment.setUsername(username);
        HashMap<Object, Object> map = new HashMap<>();
        boolean b = false;
        try {
            b = commentService.addComment(comment);
            if (b) {
                map.put("status", 200);
            } else {
                map.put("status", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Helper.responseJSON(response, map);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
