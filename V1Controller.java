package com.rest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.dto.SellBuyFive;
import com.dao.dto.SellBuyFiveDto;
import com.dao.dto.TickerDto;
import com.dao.entity.Stock;
import com.dao.vo.StockProductVO;
import com.google.common.collect.Lists;
import com.server.constant.RedisKeyPrefix;
import com.util.redis.IJedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class V1Controller {
    @Autowired
    private IJedisService jedisService;

   
    @GetMapping("/api/v1/allticker")
    public TickerDto allticker(){
        TickerDto tickerDto = new TickerDto();
        tickerDto.setDate(String.valueOf(System.currentTimeMillis()));
        String accessStocks = jedisService.get("access_stocks");
        List<TickerDto.Ticker> innerList = new ArrayList<>();
        if(StringUtils.isNotEmpty(accessStocks)){
            List<Stock> stocks = JSON.parseArray(accessStocks, Stock.class);
            List<StockProductVO>  stockProductVOList = buildHangQing(stocks);
            for (StockProductVO stockProductVO : stockProductVOList) {
                TickerDto.Ticker ticker = new TickerDto.Ticker();
                String buy = jedisService.get("vb:depth:self:newitem:" + stockProductVO.getCode());
                SellBuyFiveDto sellBuyFiveDto = JSONObject.parseObject(buy, SellBuyFiveDto.class);
                SellBuyFive BuyFive = sellBuyFiveDto.getBids().get(0);
                ticker.setBuy(BuyFive.getPrice());
                ticker.setLow(stockProductVO.getLow());
                ticker.setHigh(stockProductVO.getHigh());
                ticker.setChange(stockProductVO.getChangeRate());
                ticker.setLast(stockProductVO.getPrice());
                SellBuyFive sellBuyFive = sellBuyFiveDto.getAsks().get(0);
                ticker.setSell(sellBuyFive.getPrice());
                ticker.setSymbol(stockProductVO.getCode().replace("/","_"));
                ticker.setVol(stockProductVO.getVolume());
                innerList.add(ticker);
            }
            tickerDto.setTicker(innerList);
        }

        return tickerDto;

    }

    public List<StockProductVO> buildHangQing(List<Stock> stocks){
        List<StockProductVO> voList = Lists.newArrayList();
        stocks.forEach(e -> {
            StockProductVO vo ;
            String str = jedisService.get(RedisKeyPrefix.STOCK_GOODS_INFO_PREKEY + e.getCode());
            if (com.util.StringUtils.isNotBlank(str)) {
                vo = JSONObject.parseObject(str, StockProductVO.class);
                vo.setCoinImg(e.getCoinImg());
            } else {
                vo = new StockProductVO();
                vo.setCode(e.getCode());
                vo.setCoinOutType(e.getCoinOutType());
            }
            voList.add(vo);
        });
        return voList;
    }


}
