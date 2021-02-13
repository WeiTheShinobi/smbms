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

    public User login(String userCode, String userPassword) {
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

        if(user != null){
            if(!user.getUserPassword().equals(userPassword)){
                user = null;
            }
        }

        return user;
    }

    public boolean updatePwd(int id, String password) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            if(userDao.updatePwd(connection,id,password)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResoucre(connection,null,null);
        }

        return flag;
    }

    public int getUserCount(String userName, int userRole) {

        Connection connection = null;
        int userCount = 0;
        try {
            connection = BaseDao.getConnection();
            userCount = userDao.getUserCount(connection, userName, userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResoucre(connection,null,null);
        }
        return userCount;
    }
}
