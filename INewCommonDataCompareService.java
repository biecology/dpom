package com.server.service;

import com.dao.entity.*;

import java.util.List;

/**
 * @Description:
 * @Version:
 */
public interface INewCommonDataCompareService {
    /**
     */
    void insertRedis();

    /**
     * @param code
     * @param infoList
     */
    void selectFiveData(String code, List<GoodsKlineMinute5Info> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectFifteenData(String code, List<GoodsKlineMinute15Info> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectHalfHourData(String code, List<GoodsKlineMinute30Info> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectOneHourData(String code, List<GoodsKlineMinute60Info> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectFourHourData(String code, List<GoodsKlineHour4Info> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectDayData(String code, List<GoodsKlineDayInfo> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectWeekData(String code, List<GoodsKlineWeekInfo> infoList);

    /**
     * @param code
     * @param infoList
     */
    void selectMonthData(String code, List<GoodsKlineMonthInfo> infoList);
}
