package com.weitheshinobi.dao.user;

import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
//    登入
    public User getLoginUser(Connection connection,String userCode) throws SQLException;

//    修改密碼
    public int updatePwd(Connection connection,int id ,String password) throws SQLException;
}
