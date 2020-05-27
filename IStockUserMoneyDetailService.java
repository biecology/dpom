package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.StockUserMoneyDetail;
import com.server.enums.FinancialTypeEnum;

import java.math.BigDecimal;

public interface IStockUserMoneyDetailService extends IService<StockUserMoneyDetail> {
    /**
     * @param stockUserId
     * @param money
     * @param moneyBefore
     * @param type
     * @param massage
     * @param typeId
     * @param stockCode
     * @return
     */
    int addUserMoneyDetail(Long stockUserId, BigDecimal money, BigDecimal moneyBefore, FinancialTypeEnum type, String massage, Long typeId, String stockCode,Integer walletType);

    /**
     */

    public void stockUserFundChangeDealMoney(BigDecimal dealMoney, String dealMoneyMsg, FinancialTypeEnum financialTypeEnum, Long stockUserId, long id, String stockCode);
}
