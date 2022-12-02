package com.ManageSystem.web;

import com.ManageSystem.pojo.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static com.ManageSystem.util.UserLogin.UserValidation;

@WebServlet("/loginTwoServlet")
public class LoginTwoServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginTwoServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        User user1 = JSON.parseObject(params, User.class);
        if (UserValidation(user1)) {
            LOGGER.info(user1.getUsername() + "登录了");
            response.getWriter().write("YES");
            Cookie cookie = new Cookie("username", user1.getUsername());
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
        } else {
            response.getWriter().write("NO");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}
