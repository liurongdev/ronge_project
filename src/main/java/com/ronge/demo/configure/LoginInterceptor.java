package com.ronge.demo.configure;

import com.ronge.demo.service.UserService;
import com.ronge.demo.utils.SecretUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private static final String LONGIN_COOKIE_KEY = "login_uid";

    private static final String LOGIN_TOKEN_KEY = "token";

    private static final long KEEP_LOGIN_TIME= 5 * 60 * 60 *1000;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("request uri:{}"+request.getRequestURL()+ ";method:"+ request.getMethod());
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            logger.info("cors OPTIONS 请求方法放行...");
            return true;
        }
        Cookie[] cookies = request.getCookies();
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        System.out.println("token:" + request.getHeader("token"));
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (Objects.equals(LONGIN_COOKIE_KEY, cookie.getName())) {
//                    logger.info("已经登录,login_uid:{}", cookie.getValue());
//                    return true;
//                }
//            }
//        }
        //response.sendRedirect("http://localhost:8081/#/login");
        if (StringUtils.isBlank(token)) {
            //response.sendRedirect("http://localhost:8081/#/login");
            // return false;
//            System.out.println("登录token为空");
//            response.getOutputStream().write("please login".getBytes());
//            response.setHeader("token","please login");
//            //response.sendRedirect("/login");
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());

//            response.setHeader("Access-Control-Expose-Headers", "token");
//            response.setHeader("Access-Control-Allow-Headers", "token");
//            response.setHeader("token", "ronge");
            //return false;
        }

        if (StringUtils.isNotBlank(token)) {
            String data = SecretUtils.AesDecrypt(token);
            long loginTime = Long.parseLong(data.split("_")[1]);
            if (System.currentTimeMillis() - loginTime > KEEP_LOGIN_TIME) {
                System.out.println("token已经过期，重新请重新登录");
                response.setHeader("token", "please login");
                //response.sendRedirect("/login");
                response.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
                return false;
            }
        }
//        response.setHeader("Access-Control-Expose-Headers", "token");
//        response.setHeader("Access-Control-Allow-Headers", "token");
//        response.setHeader("token", token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
