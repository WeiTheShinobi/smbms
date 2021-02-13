package com.weitheshinobi.dao.user;

import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
//    登入
    public User getLoginUser(Connection connection,String userCode) throws SQLException;

//    修改密碼
    public int updatePwd(Connection connection,int id ,String password) throws SQLException;

    public int getUserCount(Connection connection,String userName,int userRole) throws SQLException;

    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;
}
