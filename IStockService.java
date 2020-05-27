package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.Stock;
import com.dao.dto.StockCoinCoinDto;

/**
 * @Description:
 * @Version:
 */
public interface IStockService extends IService<Stock> {

    /**
     * @param code
     * @param rob
     * @return
     */
    StockCoinCoinDto getStockCoinVoByCode(String code, Integer rob);
}
