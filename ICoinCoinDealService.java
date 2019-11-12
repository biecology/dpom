package com.server.service;

import com.dao.dto.CoinCoinSearchDto;
import com.dao.dto.CoinCoinSelectDto;
import com.dao.dto.CoinCoinDto;
import com.dao.entity.Stock;
import com.util.pageinfoutil.PageVo;

import java.util.Map;


public interface ICoinCoinDealService {
   
    Object addBill(CoinCoinDto dto);

    
    boolean cancelBill(CoinCoinDto dto);

   
    Map<String,Object> selectCoinCoin(CoinCoinDto dto, PageVo pageVo);

   
    Map<String,Object> successDeal(CoinCoinSelectDto dto, PageVo pageVo);

   
    Object successDealInfo(CoinCoinSelectDto dto, PageVo pageVo);

   
    Object stockPair();

   
    Object getFount(CoinCoinSelectDto dto);

    
    Map<String, Object> selCoinCoinByHistory(CoinCoinDto dto, PageVo pageVo);


    Stock codeInfo(CoinCoinSelectDto dto);
   
    Map<String, Object> entrustBuy(CoinCoinSearchDto dto, PageVo pageVo);

   
    Map<String, Object> entrustSell(CoinCoinSearchDto dto, PageVo pageVo);
}
