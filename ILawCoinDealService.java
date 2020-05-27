package com.server.service;

import javax.servlet.http.HttpServletRequest;

public interface ILawCoinDealService {
    /**
     *
     * @param lawCoinReq
     * @param req
     * @return
     */
    boolean addBill(LawCoinReq lawCoinReq, HttpServletRequest req);

    /**
     *
     * @param lawCoinReq
     * @param req
     * @param pageVo
     * @return
     */
    PageInfo<?> selectLawCoinList(LawCoinReq lawCoinReq, HttpServletRequest req, PageVo pageVo);

    /**
     *
     * @param lawCoinReq
     * @param req
     * @return
     */
    LawCoinRecord lawCoinDeal(LawCoinReq lawCoinReq, HttpServletRequest req);

    PageInfo<?> selectLawCoinRecord(LawCoinReq lawCoinReq, HttpServletRequest req, PageVo pageVo);

    /**
     *
     * @param lawCoinReq
     * @param req
     */
    void sellBuyConfirme(LawCoinReq lawCoinReq, HttpServletRequest req);

    /**
     *
     * @param lawCoinReq
     * @param req
     */
    void cancelLawCoinRecord(LawCoinReq lawCoinReq, HttpServletRequest req);

    /**
     *
     * @param lawCoinReq
     * @param req
     * @param lawCoinAppeal
     */
    void appealLawCoinRecord(LawCoinReq lawCoinReq, HttpServletRequest req, LawCoinAppeal lawCoinAppeal);

    /**
     *
     * @param lawCoinReq
     * @param req
     */
    void cancelLawCoinDealBill(LawCoinReq lawCoinReq, HttpServletRequest req);

    /**
     *
     * @param lawCoinReq
     * @param req
     * @param
     * @return
     */
    PageInfo<?> selectUserLawCoinBill(LawCoinReq lawCoinReq, HttpServletRequest req, PageVo pageVo);


    /**
     *
     * @param lawCoinReq
     * @param req
     * @param
     * @return
     */
    PageInfo<?> selectLawCoinRecordAll(LawCoinReq lawCoinReq, HttpServletRequest req, PageVo pageVo);


    /**
     * @param lawCoinRecord
     * @param msg
     * @param financialTypeEnum
     */
    void cancelLawCoinRecord(LawCoinRecord lawCoinRecord, String msg, FinancialTypeEnum financialTypeEnum);

    void sellConfirmeBill(LawCoinRecord lawCoinRecord);

    void sellConfirm(Long id ,Long orderId);

    void applyShop(Long id);

    LawCoinRecord orderDetail(String dealEntrustNo ,Long userId);
}
