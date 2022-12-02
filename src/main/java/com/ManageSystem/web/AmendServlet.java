package com.ManageSystem.web;

import com.ManageSystem.pojo.DetailData;
import com.ManageSystem.pojo.User;
import com.ManageSystem.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/amendServlet")
public class AmendServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginTwoServlet.class);
    private final UserService users = new UserService();
    private String username;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BufferedReader br = request.getReader();
            String Data = br.readLine();
            DetailData data = JSON.parseObject(Data, DetailData.class);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    username = cookie.getName();
                    if ("username".equals(username)) {
                        User user = new User();
                        user.setUsername(cookie.getValue());
                        user = users.loginUser(user);
                        if (user.getDetaildata().equals(data.getId())) {
                            users.upUserData(data);
                            LOGGER.info(cookie.getValue() + "信息修改成功");
                            response.getWriter().write("yes");
                        } else {
                            response.getWriter().write("");
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info(username + "信息修改失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
