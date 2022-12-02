package com.ManageSystem.web;

import com.ManageSystem.pojo.DetailData;
import com.ManageSystem.pojo.User;
import com.ManageSystem.service.UserService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/gainUserDataServlet")
public class GainUserDataServlet extends HttpServlet {
    private final UserService user = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("username".equals(name)) {
                HttpSession session = request.getSession();
                Object user1 = session.getAttribute("username");
                String username = cookie.getValue();
                if (username.equals(user1)) {
                    user.setUsername(username);
                } else {
                    user.setUsername(null);
                }
                break;
            }
        }
        DetailData Data = this.user.inquireUserData(user);
        if (Data != null) {
            String s = JSON.toJSONString(Data);
            response.setHeader("Content-type", "text/json; charset=utf-8");
            response.getWriter().write(s);
        } else {
            response.getWriter().write("NULL");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
