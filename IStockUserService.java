package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.base.AppLoginVo;
import com.dao.dto.CoinCoinSelectDto;
import com.dao.entity.*;
import com.dao.dto.StockUserLORDto;
import com.dao.vo.CapitalFundVo;
import com.dao.vo.MyCustomVo;
import com.dao.vo.StockUserCapitalVO;
import com.dao.vo.StockUserVo;
import com.github.pagehelper.PageInfo;
import com.util.pageinfoutil.PageVo;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Version:
 */
public interface IStockUserService extends IService<StockUser> {
    /**
     * @param dto
     * @return
     */
    AppLoginVo login(StockUserLORDto dto) throws UnsupportedEncodingException;

    /**
     * @param dto
     * @return
     */
    AppLoginVo register(StockUserLORDto dto) throws UnsupportedEncodingException;

    /**
     * @param dto
     * @return
     */
    boolean getCode(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    List<StockUserCapitalVO> getCaptialFund(StockUserLORDto dto);

    /**
     * @param id
     * @return
     */
    StockUserVo userInfo(Long id);

    /**
     * @param dto
     * @return
     */
    boolean propertyChange(StockUserLORDto dto);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    PageInfo<CoinCoinChangeDeal> changeRecord(StockUserLORDto dto, PageVo pageVo);

    /**
     * @param dto
     * @return
     */
    boolean editPswd(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean bindTelOrEmail(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean updatePswd(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean updateTradePswd(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean baseAuth(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean inspectAuth(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean relieveShop(StockUserLORDto dto);

    /**
     * @param dto
     * @return
     */
    boolean addPayWay(StockUserLORDto dto);

    /**
     * @return
     */
    String touchMe();

    /**
     * @param dto
     * @return
     */
    boolean contentMessage(StockUserLORDto dto);

    /**
     * @param type
     * @return
     */
    List<ComConfigAgreement> dealRule(Integer type);

    /**
     * @param dto
     * @return
     */
    Map concessionInfo(Long id);

    /**
     * @param dto
     * @return
     */
    PageInfo<MyCustomVo> myCustom(StockUserLORDto dto, PageVo pageVo);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    PageInfo commissionInfo(StockUserLORDto dto, PageVo pageVo);

    /**
     * @return
     */
    Object getExchange(String code);

    /**
     * @param id
     * @param vo
     * @return
     */
    PageInfo<StockUserMoneyDetail> moneyDetail(Long id, String code,PageVo vo);

    Map captialFundToltal(Long id);

    /**
     * @param dto
     * @return
     */
    Object myInvitation(CoinCoinSelectDto dto);

    /**
     * @return
     */
    PageInfo questionGroup(PageVo pageVo);

    /**
     * @return
     */
    PageInfo question(PageVo pageVo);

    /**
     * @return
     */
    Map getSysConfig();

    /**
     * @param id
     * @return
     */
    List getFundList(Long id);

    /**
     * @param dto
     * @return
     */
    AppLoginVo loginByCode(StockUserLORDto dto) throws UnsupportedEncodingException;

    /**
     * @param account
     */
    void loginMsg(Long id,String account,String areacode);


