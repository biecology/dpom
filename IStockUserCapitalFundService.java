package com.server.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 */
public interface IStockUserCapitalFundService extends IService<StockUserCapitalFund> {

    /**
     * @param id
     * @param code
     * @return
     */
    StockUserCapitalFund selectStockUserCapitalFund(Long id,String code);


    /**
     *
     * @param id
     * @param money
     * @return
     */
    int updateStockUserCapitalFund(Long id, String code,BigDecimal money);

    /**
     *
     * @param id
     * @param money
     * @return
     */
    int updateStockUserMiningFund(Long id, String code,BigDecimal money);

    /**
     *
     * @param req
     * @param capitalVO
     * @return
     */
    StockUserCapitalVO getOneCoinFundInfo(HttpServletRequest req, StockUserCapitalVO capitalVO);

    FrozenDto findWallet(Long id);

    BigDecimal findPossessed(Long uid,int type,Date startTime,Date endTime);

    BigDecimal findNowQuantity(Date startTime,Date endTime);

    BigDecimal findSumQuantity();

    BigDecimal findSumSharedTotal(Long uid,Date date, Date date1);

    BigDecimal findSumSharedTotalAll(Date date, Date date1);

    StockUserCapitalFund findBibWallet(Long userId,String stockCode);

    int transferBib(Long uid,TransferDto transferDto, StockUserCapitalFund wallet, StockUserCapitalFund walletSl);

    PageInfo getOperationTransferLog(Long userId, PageVo pageVo);
}
