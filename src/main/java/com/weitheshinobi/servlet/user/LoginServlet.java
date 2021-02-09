package com.weitheshinobi.servlet.user;

import com.weitheshinobi.pojo.User;
import com.weitheshinobi.service.user.UserService;
import com.weitheshinobi.service.user.UserServiceImpl;
import com.weitheshinobi.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode,userPassword);
        if(user!=null){
            req.getSession().setAttribute(Constants.USER_SESSION,userCode);
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            req.setAttribute("error","ID或密碼錯誤");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
