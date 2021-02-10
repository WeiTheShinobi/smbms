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
            String sql = "SELECT * FROM smbms_user WHERE userCode=?";
            Object[] parms = {userCode};
            resultSet = BaseDao.execute(connection,preparedStatement,resultSet,sql,parms);
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

    public int updatePwd(Connection connection,int id,String password) throws SQLException {

        String sql = "UPDATE smbms_user SET userPassword = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        int execute = 0;
        Object params[] = {password,id};

        if(connection!=null){
            execute = BaseDao.execute(connection, preparedStatement, sql, params);
            BaseDao.closeResoucre(null,preparedStatement,null);
        }

        return execute;
    }
}
