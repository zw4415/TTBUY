package com.jiang.service.impl;

import com.jiang.dao.CartDao;
import com.jiang.dao.ProductDao;
import com.jiang.dao.impl.CartDaoImpl;
import com.jiang.dao.impl.ProductDaoImpl;
import com.jiang.pojo.Cart;
import com.jiang.pojo.Product;
import com.jiang.service.CartService;
import com.jiang.utils.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addCat(String productId, String userId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Cart cart = cartDao.getCartByProductId(connection, productId, userId);
        System.out.println("商品是否已经在购物车了？"+(cart!=null));
        if (cart != null) {
            return false;
        }
        int i = cartDao.addCat(connection, productId, userId);
        connection.close();
        return i == 1;
    }

    @Override
    public List<Product> getCartsByUserId(String userId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        List<String> productIdList = cartDao.getCartsByUserId(connection, userId);
        List<Product> products = new ArrayList<>();
        int size = productIdList.size();
        for (int i = 0; i < size; i++) {
            Product product = productDao.searchProductsById(connection, productIdList.get(i));
            products.add(product);
        }
        return products;
    }
    @Override
    public boolean deleteCartById(String productId, String userId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        int i = cartDao.deleteCartById(connection, productId, userId);
        connection.close();
        return i != 0;
    }
}
