package com.weitheshinobi.dao.user;

import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getLoginUser(Connection connection,String userCode) throws SQLException;
}
