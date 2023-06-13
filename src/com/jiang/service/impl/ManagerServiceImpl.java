package com.jiang.service.impl;

import com.jiang.dao.ManagerDao;
import com.jiang.dao.impl.ManagerDaoImpl;
import com.jiang.pojo.Manager;
import com.jiang.service.ManagerService;
import com.jiang.utils.BaseDao;
import com.jiang.utils.Helper;

import java.sql.Connection;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao=new ManagerDaoImpl();
    @Override
    public Manager ManagerLogin(String username, String password) throws Exception {
        Connection connection = BaseDao.getConnection();
        Manager manager = managerDao.getManagerByUserName(connection, username);
        connection.close();
        //password= Helper.toMd5str(password);加密
        if(manager==null||!password.equals(manager.getPassword())){
            return null;
        }
        return manager;
    }
}
