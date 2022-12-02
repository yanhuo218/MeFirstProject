package com.ManageSystem.web.Filter;

import com.ManageSystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ManageSystem.util.UserLogin.UserExist;

/**
 * 拦截没有登录的信息
 *
 * @author 付秋垚
 */
@WebFilter("/index.html")
public class indexFilter implements Filter {
    public static final Logger LOGGER = LoggerFactory.getLogger(indexFilter.class);
    private final UserService user = new UserService();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("username".equals(name)) {
                        String username = cookie.getValue();
                        if (UserExist(username)) {
                            HttpSession session = req.getSession();
                            session.setAttribute("username", username);
                            LOGGER.info(username + "进入了系统");
                            chain.doFilter(req, response);
                            return;
                        }
                    }
                }
            }
            req.getRequestDispatcher("/login.html").forward(req, response);
        } catch (Exception e) {
            System.out.println("拦截器出现问题~~");
            throw new RuntimeException(e);
        }
    }
}
