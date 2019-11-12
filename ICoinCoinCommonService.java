package com.server.service;

import com.dao.entity.CoinCoinBuyBill;
import com.dao.entity.CoinCoinSellBill;
import com.dao.dto.CoinCoinDto;
import com.dao.dto.StockCoinCoinDto;
import com.server.enums.BuySellBillEndEnum;
import com.server.util.priorityUtils.PriorityTaskBuyBill;
import com.server.util.priorityUtils.PriorityTaskSellBill;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;


public interface ICoinCoinCommonService {

 
    CoinCoinBuyBill insertCoinCoinBuyBill(BigDecimal dealMoney, BigDecimal dealFee, CoinCoinDto dto, StockCoinCoinDto stockCoinCoinDto);

  
    CoinCoinSellBill insertCoinCoinSellBill(BigDecimal dealMoney, BigDecimal dealFee, CoinCoinDto dto, StockCoinCoinDto stockCoinCoinDto);

 
    void startCoinCoinSellBuyMarriedDeal(CopyOnWriteArrayList<PriorityTaskBuyBill> listsBuy, CopyOnWriteArrayList<PriorityTaskSellBill> listsSell, Integer tradeType, String stockCode);

  
    public BuySellBillEndEnum buyNumBigSellNumNewDeal(CopyOnWriteArrayList<PriorityTaskBuyBill> listsBuy, PriorityTaskBuyBill coinBuy, CopyOnWriteArrayList<PriorityTaskSellBill> listsSell, PriorityTaskSellBill coinSell, Integer tradeType);
}
