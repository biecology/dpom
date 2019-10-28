package com.rest.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dao.entity.StockUser;
import com.dao.mapper.StockUserMapper;
import com.server.constant.CommonConstant;
import com.server.constant.RedisKeyPrefix;
import com.util.Respons.ResponseUtil;
import com.util.StringUtils;
import com.util.auth.AuthSign;
import com.util.redis.IJedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @Version:
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IJedisService jedisService;
    @Resource
    private StockUserMapper stockUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("token");
        String language=request.getHeader("language");
        boolean isF=false;
        if (StringUtils.isBlank(language)){
            isF=false;
        }else {
            if (language.equals("eu")){
                isF=true;
            }
        }
      
        if (AuthSign.verify(token)) {
           
            JSONObject jsonObject = AuthSign.getUserObject(token);
            StockUser stockUser = JSONObject.parseObject(jsonObject.toJSONString(), StockUser.class);
         
            String userId = request.getHeader("loginUserId");
            if (StringUtils.isBlank(userId)) {
                userId = request.getParameter("loginUserId");
            }
            if (StringUtils.isBlank(userId) || !stockUser.getId().equals(Long.parseLong(userId))) {
           
                return false;
            }
           

            if (stockUser.getIsDisable()) {
                
               
            }
            String systemType = request.getParameter("systemType");
            if (StringUtils.isBlank(systemType)) {
                systemType = request.getHeader("systemType");
            }
            String key = RedisKeyPrefix.getLoginTokenKey(stockUser.getId().toString(), systemType);
            String value = jedisService.get(key);
            if (StringUtils.isBlank(value)) {
             
                return false;
            }
            if (!token.equals(value)) {
              
                return false;
            }
       
            jedisService.expire(key, CommonConstant.TOKEN_EXPIRE_TIME);
            if(StringUtils.isBlank(jedisService.get("lastLoginTime:"+stockUser.getId()))){
                jedisService.set("lastLoginTime:"+stockUser.getId(), String.valueOf(Calendar.getInstance().getTimeInMillis()),3600);
                stockUserMapper.updateLastLoginTime(stockUser.getId());
            }


            return true;
        } else {
           
            return false;
        }
//       return true;
    }
}
