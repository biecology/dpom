package com.server.service;

import com.dao.entity.StockUserWithdrawAddr;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AppCurrencyAddressService {

    int addCurrencyAddress(StockUserWithdrawAddr userWithdrawAddr);

   
    int delCurrencyAddress(StockUserWithdrawAddr userWithdrawAddr);

   
    List<StockUserWithdrawAddr> queryCurrencyAddress(Long stockUserId, Integer code);

   
    @Transactional
    void verificationAddress(String address);

    StockUserWithdrawAddr selectByUserIdAndAddr(StockUserWithdrawAddr userWithdrawAddr);

    @Transactional
    void verificationBtcAddress(String addr);

    void verificationBchAddress(String addr);
}
