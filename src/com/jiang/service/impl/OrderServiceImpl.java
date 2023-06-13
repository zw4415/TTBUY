package com.jiang.service.impl;

import com.jiang.dao.OrderDao;
import com.jiang.dao.ProductDao;
import com.jiang.dao.impl.OrderDaoImpl;
import com.jiang.dao.impl.ProductDaoImpl;
import com.jiang.pojo.Order;
import com.jiang.service.OrderService;
import com.jiang.utils.BaseDao;
import com.jiang.utils.Helper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao =new OrderDaoImpl();
    private ProductDao productDao =new ProductDaoImpl();
    @Override
    public boolean addOrder(Order order) throws SQLException {
        Connection connection = BaseDao.getConnection();
        order.setId(Helper.getUUID());
        order.setStatus(0);
        order.setCreated(new Date(new java.util.Date().getTime()));
        int i1 = productDao.reducedById(connection, order.getProductid(),order.getNumber());
        int i = orderDao.addOrder(connection, order);
        connection.close();
        return i!=0;
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        List<Order> orders = orderDao.getOrdersByUserId(connection, userId);
        connection.close();
        return orders;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        Connection connection = BaseDao.getConnection();
        List<Order> orders = orderDao.getAllOrders(connection);
        connection.close();
        return orders;
    }

    @Override
    public Order getOrderById(String id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Order order = orderDao.getOrderById(connection,id);
        connection.close();
        return order;
    }

    @Override
    public boolean sendProduct(String id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        int i = orderDao.updateStatus(connection, id, 1);
        connection.close();
        return i!=0;
    }

    @Override
    public boolean getProduct(String id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        int i = orderDao.updateStatus(connection, id, 2);
        connection.close();
        return i!=0;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        int i = orderDao.deleteOrder(connection, id);
        connection.close();
        return i!=0;
    }
}
