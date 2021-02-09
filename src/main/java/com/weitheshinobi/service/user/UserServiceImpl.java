package com.weitheshinobi.service.user;

import com.weitheshinobi.dao.BaseDao;
import com.weitheshinobi.dao.user.UserDao;
import com.weitheshinobi.dao.user.UserDaoImpl;
import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public User login(String userCode, String passWord) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResoucre(connection,null,null);
        }
        return user;
    }
}
