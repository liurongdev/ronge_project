package com.ronge.demo.configure;

import com.ronge.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author liurong
 * @date 2020/10/28 21:29
 */
public class LoginInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private static final String LONGIN_COOKIE_KEY = "login_uid";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("request uri:{}",request.getRequestURL());
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(LONGIN_COOKIE_KEY, cookie.getName())) {
                    logger.info("已经登录,login_uid:{}",cookie.getValue());
                    return true;
                }
            }
        }
        //response.sendRedirect("http://localhost:8081/#/login");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
