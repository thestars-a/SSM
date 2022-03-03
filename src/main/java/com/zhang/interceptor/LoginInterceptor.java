package com.zhang.interceptor;

import com.alibaba.fastjson.JSON;
import com.zhang.pojo.Admin;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin loginUser = (Admin) session.getAttribute("loginUser");
        String username = "";
        if (loginUser != null) {
            return true;
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        username = cookie.getValue();
                        System.out.println(username);
                        break;
                    }
                }
                for (Cookie cookie : cookies) {
                    if ("loginState".equals(cookie.getName())) {
                        if (cookie.getValue().equals(loginService.getUUID(username))) {
                            Admin admin = loginService.getAdmin(username);
                            request.getSession().setAttribute("loginUser",admin);
                            return true;
                        }
                    };
                }
            }
        }
        response.getWriter().write(JSON.toJSONString("no"));
        return false;
    }
}
