package com.server.service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Version:
 */
public interface IStockDealService {

    /**
     */
    List<String> getCodeList(Integer type);
    /**
     */
    Map getCodeAll();
    /**
     */
    void checkCapitalFund(Long userId);

    /**
     */
    List<String> getSelfCodeList();
}
