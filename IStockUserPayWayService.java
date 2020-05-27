package com.server.service;

import com.dao.entity.StockUserPayWay;

public interface IStockUserPayWayService {

    void updateUserPayWay(Long userId,Long payId,Integer flag);

    void updatePayWayById(StockUserPayWay stockUserPayWay,Integer payWay);
}
