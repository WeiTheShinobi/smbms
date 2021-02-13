package com.weitheshinobi.dao.user;

import com.mysql.cj.util.StringUtils;
import com.weitheshinobi.dao.BaseDao;
import com.weitheshinobi.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int userCount = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            resultSet = BaseDao.execute(connection, preparedStatement, resultSet, sql.toString(), params);
            if(resultSet.next()){
                userCount = resultSet.getInt("count");
            }
            BaseDao.closeResoucre(null, preparedStatement, resultSet);
        }
        return userCount;
    }

    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)
            throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResoucre(null, pstm, rs);
        }
        return userList;
    }
}
