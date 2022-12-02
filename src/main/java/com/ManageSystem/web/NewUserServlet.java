package com.ManageSystem.web;

import com.ManageSystem.pojo.User;
import com.ManageSystem.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static com.ManageSystem.util.UserLogin.UserValidation;

@WebServlet("/newUserServlet")
public class NewUserServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(NewUserServlet.class);
    private final UserService user = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader br = request.getReader();
            User user = JSON.parseObject(br.readLine(), User.class);
            this.user.addUser(user);
            LOGGER.info(user.getUsername() + "注册了");
            if (UserValidation(user)) {
                response.getWriter().write("YES");
            } else {
                response.getWriter().write("NO");
            }
        } catch (Exception e) {
            LOGGER.debug("账号注册出现问题~~" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
