package com.server.service;

import com.dao.entity.StockUserGoogleAuthentication;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IStockUserGoogleAuthenticationService extends IService<StockUserGoogleAuthentication> {

    int insertGoogleSercet(StockUserGoogleAuthentication stockUserGoogleAuthentication);

    StockUserGoogleAuthentication selectByStockUserId(Long stockUserId);

}
