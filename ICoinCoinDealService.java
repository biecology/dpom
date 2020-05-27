package com.server.service;

import com.dao.dto.CoinCoinSearchDto;
import com.dao.dto.CoinCoinSelectDto;
import com.dao.dto.CoinCoinDto;
import com.dao.entity.Stock;
import com.util.pageinfoutil.PageVo;

import java.util.Map;

/**
 * @Description:
 * @Version:
 */
public interface ICoinCoinDealService {
    /**
     * @param dto
     * @return
     */
    Object addBill(CoinCoinDto dto);

    /**
     * @param dto
     * @return
     */
    boolean cancelBill(CoinCoinDto dto);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    Map<String,Object> selectCoinCoin(CoinCoinDto dto, PageVo pageVo);

    /**
     * @param dto
     * @return
     */
    Map<String,Object> successDeal(CoinCoinSelectDto dto, PageVo pageVo);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    Object successDealInfo(CoinCoinSelectDto dto, PageVo pageVo);

    /**
     * @return
     */
    Object stockPair();

    /**
     * @param dto
     * @return
     */
    Object getFount(CoinCoinSelectDto dto);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    Map<String, Object> selCoinCoinByHistory(CoinCoinDto dto, PageVo pageVo);


    Stock codeInfo(CoinCoinSelectDto dto);
    /**
     * @param dto
     * @param pageVo
     * @return
     */
    Map<String, Object> entrustBuy(CoinCoinSearchDto dto, PageVo pageVo);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    Map<String, Object> entrustSell(CoinCoinSearchDto dto, PageVo pageVo);
}
