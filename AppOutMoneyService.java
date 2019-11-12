package com.server.service;

import com.dao.entity.StockUser;
import com.dao.entity.StockUserCharge;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface AppOutMoneyService {
 
    public void outMoneyApply(StockUserCharge userCharge);

  
    void outCoinApply(StockUserCharge userCharge, StockUser stockUser, HttpServletRequest req);

    Map<String, Object> selectGetTbFee(StockUserCharge userCharge);
}
