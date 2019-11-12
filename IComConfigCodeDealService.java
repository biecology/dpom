package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.ComConfigCodeDeal;

import java.math.BigDecimal;


public interface IComConfigCodeDealService extends IService<ComConfigCodeDeal> {
 
    BigDecimal getCoinCoinDealFeeRate(String dealCode, Integer type);
}
