package com.weitheshinobi.dao.user;

import com.weitheshinobi.dao.BaseDao;
import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        if(connection != null){
            String url = "SELECT * FROM smbms_user WHERE userCode=?";
            Object[] parms = {userCode};
            resultSet = BaseDao.execute(connection,preparedStatement,resultSet,url,parms);
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResoucre(null,preparedStatement,resultSet);
        }
        return user;
    }
}
