package com.weitheshinobi.servlet.user;

import com.mysql.cj.util.StringUtils;
import com.weitheshinobi.pojo.User;
import com.weitheshinobi.service.user.UserService;
import com.weitheshinobi.service.user.UserServiceImpl;
import com.weitheshinobi.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newPassword = req.getParameter("newpassword");

        boolean flag = false;
        if(o!=null && !StringUtils.isNullOrEmpty(newPassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)o).getId(),newPassword);
            if(flag){
                req.setAttribute("message","修改密碼成功");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","修改密碼失敗");
            }
        }else {
            req.setAttribute("message","新密碼有誤");
        }

        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
