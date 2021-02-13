package com.weitheshinobi.service.user;

import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserService {
    public User login(String userCode,String passWord);

    public boolean updatePwd(int id , String password);

    public int getUserCount(String userName,int userRole);
}
