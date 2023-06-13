package com.jiang.servlet.open;

import com.jiang.dao.impl.OrderDaoImpl;
import com.jiang.pojo.Comment;
import com.jiang.pojo.Product;
import com.jiang.service.CommentService;
import com.jiang.service.ProductService;
import com.jiang.service.impl.CommentServiceImpl;
import com.jiang.service.impl.ProductServiceImpl;
import com.jiang.utils.BaseDao;
import com.jiang.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/open/product/*")
public class ProductServlet extends HttpServlet {
    private CommentService commentService=new CommentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String action = req.getRequestURI().replace("/open/product/", "");
        if ("queryall".equals(action)) {
            try {
                getAllProducts(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("getproductbyid".equals(action.substring(0,14))){
            try {
                getProductById(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("找不到");
        }
    }

    protected void getAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        ProductService productService = new ProductServiceImpl();
        List<Product> allProducts = productService.getAllProducts();
        HashMap<Object, Object> map = new HashMap<>(8);
        map.put("status", 200);
        map.put("data", allProducts);
        Helper.responseJSON(resp,map);
    }
    protected void getProductById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String id = req.getParameter("id");
        ProductService productService = new ProductServiceImpl();
        Product product = productService.getProductById(id);

        Connection connection = BaseDao.getConnection();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        int num1 = orderDao.getNum(connection, id);

        List<Comment> comments = commentService.getCommentByProductId(product.getId());
        req.setAttribute("product",product);
        req.setAttribute("comments",comments);
        req.setAttribute("num1",num1);


        CommentServiceImpl commentService = new CommentServiceImpl();
        int num2 = commentService.getNum(product.getId());
        req.setAttribute("num2",num2);
        req.getRequestDispatcher("/product.jsp").forward(req,resp);
    }

}
