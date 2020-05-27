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

/**
 * @Description:
 * @Version:
 */
public interface ICoinCoinCommonService {

    /**
     * @param dealMoney
     * @param dealFee
     * @param dto
     * @param stockCoinCoinDto
     * @return
     */
    CoinCoinBuyBill insertCoinCoinBuyBill(BigDecimal dealMoney, BigDecimal dealFee, CoinCoinDto dto, StockCoinCoinDto stockCoinCoinDto);

    /**
     * @param dealMoney
     * @param dealFee
     * @param dto
     * @param stockCoinCoinDto
     * @return
     */
    CoinCoinSellBill insertCoinCoinSellBill(BigDecimal dealMoney, BigDecimal dealFee, CoinCoinDto dto, StockCoinCoinDto stockCoinCoinDto);

    /**
     *  @param listsBuy
     * @param listsSell
     * @param tradeType
     * @param stockCode
     */
    void startCoinCoinSellBuyMarriedDeal(CopyOnWriteArrayList<PriorityTaskBuyBill> listsBuy, CopyOnWriteArrayList<PriorityTaskSellBill> listsSell, Integer tradeType, String stockCode);

    /**
     * @param listsBuy
     * @param coinBuy
     * @param listsSell
     * @param coinSell
     * @param tradeType
     * @return
     */
    public BuySellBillEndEnum buyNumBigSellNumNewDeal(CopyOnWriteArrayList<PriorityTaskBuyBill> listsBuy, PriorityTaskBuyBill coinBuy, CopyOnWriteArrayList<PriorityTaskSellBill> listsSell, PriorityTaskSellBill coinSell, Integer tradeType);
}
