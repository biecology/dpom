package com.rest.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Date: 2019-05-20 16:47
 * @Version:
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    private String filePath = "file:";
    @Value("${biEcology.file.upload}")
    private String uploadPath;
    @Value("${biEcology.apk.upload}")
    private String apkPath;
    @Value("${biEcology.web.url}")
    private String webUrl;
    @Value("${biEcology.web.static}")
    private String webStatic;
    @Value("${biEcology.web.register}")
    private String webRegister;
    @Value("${biEcology.web.help}")
    private String webHelp;
    @Value("${biEcology.web.agent}")
    private String webAgent;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private LimitRaterInterceptor limitRaterInterceptor;
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private RobotInterceptor robotInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limitRaterInterceptor).addPathPatterns("/**").excludePathPatterns(ignoredUrlsProperties.getRateUrls());
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns(ignoredUrlsProperties.getJwtUrls());
        registry.addInterceptor(robotInterceptor).addPathPatterns("/robot/**").excludePathPatterns(new String[]{"/robot/login","/robot/dologin"});

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations(filePath + uploadPath);
        registry.addResourceHandler("/apk/**").addResourceLocations(filePath + apkPath);
        registry.addResourceHandler("/web/**").addResourceLocations(filePath + webUrl);
        registry.addResourceHandler("/static/**").addResourceLocations(filePath + webStatic);
        registry.addResourceHandler("/register/**").addResourceLocations(filePath + webRegister);
        registry.addResourceHandler("/help/**").addResourceLocations(filePath + webHelp);
        registry.addResourceHandler("/agent/**").addResourceLocations(filePath + webAgent);

        super.addResourceHandlers(registry);

    }
}
