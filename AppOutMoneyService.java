package com.server.service;

import com.dao.entity.StockUser;
import com.dao.entity.StockUserCharge;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 */
public interface AppOutMoneyService {
    /**
     *  @param userCharge
     *
     */
    public void outMoneyApply(StockUserCharge userCharge);

    /**
     *  @param userCharge
     * @param stockUser
     * @param req
     */
    void outCoinApply(StockUserCharge userCharge, StockUser stockUser, HttpServletRequest req);

    /**
     *
     * @param userCharge
     * @return
     */
    Map<String, Object> selectGetTbFee(StockUserCharge userCharge);
}
