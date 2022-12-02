package com.ManageSystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/exitSysServlet")
public class ExitSysServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(NewUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        response.getWriter().write("0");
                        LOGGER.info(cookie.getValue() + "退出了系统");
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
