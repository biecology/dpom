package com.server.service;

import com.dao.dto.OrghisInfo;
import com.dao.entity.GoodsOrghisInfo;

/**
 * @Description:
 * @Version:
 */
public interface IRealTimeDataService {
    /**
     * @param stockOrghisInfo
     */
    void toCompoundData(GoodsOrghisInfo stockOrghisInfo);

    /**
     * @param stockCode
     * @param orghisInfo
     */
    void startOrghisInfo(String stockCode, OrghisInfo orghisInfo);
}
