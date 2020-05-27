package com.server.service;

import com.dao.dto.KLineQueryDto;
import com.dao.entity.GoodsKlineDayInfo;
import com.dao.entity.GoodsKlineMinuteInfo;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Version:
 */
public interface IStockKLineInfoService {
    /**
     * @param vo
     * @return
     */
    List<Object> minuteListByCode(KLineQueryDto vo);

    /**
     * @param code
     * @return
     */
    GoodsKlineMinuteInfo selectByCode(String code);

    /**
     * @param code
     * @param time
     * @return
     */
    GoodsKlineMinuteInfo getCountVolume(String code, Date time);

    /**
     * @param code
     * @param
     * @return
     */
    GoodsKlineDayInfo getCountVolumeByDay(String code, String startTime,String endTime);
}
