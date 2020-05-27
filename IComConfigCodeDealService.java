package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.ComConfigCodeDeal;

import java.math.BigDecimal;

/**
 * @Description:
 * @Version:
 */
public interface IComConfigCodeDealService extends IService<ComConfigCodeDeal> {
    /**
     * @param dealCode
     * @param type
     * @return
     */
    BigDecimal getCoinCoinDealFeeRate(String dealCode, Integer type);
}
