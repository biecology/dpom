package com.rest.config.interceptor;

import com.server.annotation.RateLimiter;
import com.server.constant.CommonConstant;
import com.util.IpInfoUtil;
import com.util.StringUtils;
import com.util.exception.CommonException;
import com.util.limit.RedisRaterLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**

 * @Date: 2019-05-20 16:28
 * @Version:
 */
@Slf4j
@Component
public class LimitRaterInterceptor extends HandlerInterceptorAdapter {

    @Value("${biEcology.rateLimit.enable}")
    private boolean rateLimitEnable;

    @Value("${biEcology.rateLimit.limit}")
    private Integer limit;

    @Value("${biEcology.rateLimit.timeout}")
    private Integer timeout;

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    @Autowired
    private IpInfoUtil ipInfoUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
      
        String token1 = redisRaterLimiter.acquireTokenFromBucket(ipInfoUtil.getIpAddr(request), 50, 1000);
        if (StringUtils.isBlank(token1)) {
           
        }

        
        if (rateLimitEnable) {
            String token2 = redisRaterLimiter.acquireTokenFromBucket(CommonConstant.LIMIT_ALL, limit, timeout);
            if (StringUtils.isBlank(token2)) {
              
            }
        }
     
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
            if (rateLimiter != null) {
                int limit = rateLimiter.limit();
                int timeout = rateLimiter.timeout();
                String token3 = redisRaterLimiter.acquireTokenFromBucket(method.getName(), limit, timeout);
                if (StringUtils.isBlank(token3)) {
                 
                }
            }
        } catch (Exception e) {
           
        return true;
    }
}