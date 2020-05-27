package com.server.service;

import com.dao.dto.CoinCoinDto;

import java.util.Map;

/**
 */
public interface IAppCoinCoinService {

    public void startCoinCoinSellBuyMarriedDealOld(CoinCoinDto coinCoinVO);


    /**
     *
     * @param coinCoinVO
     */
    void startCoinCoinSellBuyMarriedDeal(CoinCoinDto coinCoinVO);


    void startBuyFiveNew(Map<Integer, Object> resultMaps);
}
