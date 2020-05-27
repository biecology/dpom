package com.server.service;

import com.dao.entity.StockUserWithdrawAddr;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AppCurrencyAddressService {

    /**
     *
     * @param userWithdrawAddr
     * @return
     */
    int addCurrencyAddress(StockUserWithdrawAddr userWithdrawAddr);

    /**
     *
     * @param userWithdrawAddr
     * @return
     */
    int delCurrencyAddress(StockUserWithdrawAddr userWithdrawAddr);

    /**
     *
     * @return
     */
    List<StockUserWithdrawAddr> queryCurrencyAddress(Long stockUserId, Integer code);

    /**
     *
     * @param address
     * @return
     */
    @Transactional
    void verificationAddress(String address);

    StockUserWithdrawAddr selectByUserIdAndAddr(StockUserWithdrawAddr userWithdrawAddr);

    /**
     *
     * @param addr
     */
    @Transactional
    void verificationBtcAddress(String addr);

}
