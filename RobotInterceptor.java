package com.rest.config.interceptor;

import com.server.annotation.RateLimiter;
import com.server.constant.CommonConstant;
import com.util.StringUtils;
import com.util.exception.CommonException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**

 */
@Configuration
public class RobotInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Object user = request.getSession().getAttribute("username");
        if (user == null) {
        
            request.getRequestDispatcher("/robot/login").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
