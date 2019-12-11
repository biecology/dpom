package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.base.AppLoginVo;
import com.dao.dto.ApplyPaymentCodeDto;
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


public interface IStockUserService extends IService<StockUser> {

    AppLoginVo login(StockUserLORDto dto) throws UnsupportedEncodingException;

   
    boolean register(StockUserLORDto dto);

   
    boolean getCode(StockUserLORDto dto);

    List<StockUserCapitalVO> getCaptialFund(StockUserLORDto dto);

   
    StockUserVo userInfo(Long id);

   
    boolean propertyChange(StockUserLORDto dto);

   
    PageInfo<CoinCoinChangeDeal> changeRecord(StockUserLORDto dto, PageVo pageVo);

    boolean editPswd(StockUserLORDto dto);

    boolean bindTelOrEmail(StockUserLORDto dto);

    
    boolean updatePswd(StockUserLORDto dto);

  
    boolean updateTradePswd(StockUserLORDto dto);

    boolean baseAuth(StockUserLORDto dto);

  
    boolean inspectAuth(StockUserLORDto dto);

  
    boolean relieveShop(StockUserLORDto dto);

  
    boolean addPayWay(StockUserLORDto dto);

   
    String touchMe();

   
    boolean contentMessage(StockUserLORDto dto);

    
    List<ComConfigAgreement> dealRule(Integer type);

   
    Map concessionInfo(Long id);

    PageInfo<MyCustomVo> myCustom(StockUserLORDto dto, PageVo pageVo);

   
    PageInfo commissionInfo(StockUserLORDto dto, PageVo pageVo);

    
    Object getExchange(String code);

    PageInfo<StockUserMoneyDetail> moneyDetail(Long id, String code,PageVo vo);

    Map captialFundToltal(Long id);

   
    Object myInvitation(CoinCoinSelectDto dto);

   
    PageInfo question(PageVo pageVo);

    Map getSysConfig();

    List getFundList(Long id);

   
    AppLoginVo loginByCode(StockUserLORDto dto) throws UnsupportedEncodingException;

    void loginMsg(Long id,String account,String areacode);

    

    public Boolean applyPaymentCode(ApplyPaymentCodeDto applyPaymentCodeDto, String language);
turn
    
    Map getPaymentCodeInfo(Long id);
}
