package com.weitheshinobi.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.weitheshinobi.pojo.User;
import com.weitheshinobi.service.user.UserService;
import com.weitheshinobi.service.user.UserServiceImpl;
import com.weitheshinobi.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if(method.equals("savepwd") && method!=null){
            this.updatePwd(req,resp);
        }else if(method.equals("pwdmodify") && method!=null){
            this.pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String, String> resultMap = new HashMap<String, String>();

        if(o == null){
            resultMap.put("result", "sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
            resultMap.put("result", "error");
        }else{
            String sessionPwd = ((User)o).getUserPassword();
            if(oldpassword.equals(sessionPwd)){
                resultMap.put("result", "true");
            }else{
                resultMap.put("result", "false");
            }
        }

        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
