package com.rest.config.interceptor;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @Version:
 */
@Data
@Configuration
public class IgnoredUrlsProperties {


    String[] jwtUrls = new String[]{
            "/app/user/contentNoticeDetail",
            "/app/user/screenNotice",
            "/app/user/indexContent","/app/user/appVersion","/app/user/contentNotice","/app/user/transactionRules",
            "/app/user/transactionRules","/app/user/viewpager",
            "/app/user/login","/app/user/register","/app/user/editPswd",
            "/app/upload/file","/file/**",
            "/app/mining/activiteRule","/app/mining/paoList",
            "/app/kline/**","/**/websocket/**","/app/coincoin/stockPair","/app/coincoin/codeInfo","/app/user/dealRule","/app/user/ours",
            "/app/user/touchMe","/app/user/feeInfo","/app/user/privacy","/app/user/getCode",
            "/app/lawCoin/showPrice","/app/lawCoin/lawCoinTrading","/app/addr/getAddrQrcode","/app/user/getSysConfig",
            "/app/user/checkCode","/app/user/getQrcode","/app/user/comMessage","/app/user/transactionRules","/app/user/viewpager",
            "/app/kline/depth","/app/kline/buySellFive","/app/googleAuth/loginGoogleAuth","/app/user/service","/app/user/loginByCode"
            ,"/app/user/question","/app/user/money","/app/user/falv","/app/upload/fileWen",
            "/robot/**","/error","/druid/**","/api/v1/allticker","/swagger-resources","/swagger-resources/**"
    };

    String[] rateUrls = new String[]{
           "/**"
    };
}

