package com.server.service;

import com.dao.entity.CoinCoinBuyBill;
import com.dao.entity.CoinCoinSellBill;
import com.dao.entity.StockUser;

import java.math.BigDecimal;

/**
 * @Description:
 * @Version:
 */
public interface ICoinCoinUpdateService {
    void updateUsableFundSellNumNoOverDate(StockUser buyUser,String basicCode, StockUser sellUser, BigDecimal dealMoney, BigDecimal dealFeeSell, CoinCoinSellBill coinSell,
                                           Integer numPoint, CoinCoinBuyBill coinBuy, String dealCode, BigDecimal dealNum, String stockCode, BigDecimal dealPrice,Integer tradeType,boolean isSuccessDeal,
                                           BigDecimal coinCoinDealBuyFeeRate,BigDecimal dealFeeBuy);
}
