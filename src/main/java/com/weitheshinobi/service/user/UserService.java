package com.weitheshinobi.service.user;

import com.weitheshinobi.pojo.User;

public interface UserService {
    public User login(String userCode,String passWord);
}
