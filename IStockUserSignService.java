package com.server.service;

import com.dao.entity.StockUserSign;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface IStockUserSignService extends IService<StockUserSign> {

    Map addOnce(Long id);
}
