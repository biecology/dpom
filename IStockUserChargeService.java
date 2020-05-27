package com.server.service;

import com.dao.entity.StockUserCharge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.vo.GoldEntryAndExitVo;
import com.github.pagehelper.PageInfo;
import com.util.pageinfoutil.PageVo;

import java.util.List;

/**
 */
public interface IStockUserChargeService extends IService<StockUserCharge> {

    PageInfo<StockUserCharge> listUserCharge(GoldEntryAndExitVo vo, PageVo pageUtil);

}
