package com.rest.controller;

import com.dao.dto.FrozenDto;
import com.dao.dto.TranseferLogDto;
import com.dao.dto.TransferDto;
import com.dao.entity.StockUserCapitalFund;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.server.service.IStockUserCapitalFundService;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.auth.AuthSign;
import com.util.pageinfoutil.PageVo;
import com.util.redis.IJedisService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app/sl")
public class SlController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IStockUserCapitalFundService iStockUserCapitalFundService;
    @Autowired
    private IJedisService jedisService;

    @PostMapping(value = "/account/transfer")
    public ResultVo transfer(TransferDto transferDto){
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        StockUserCapitalFund wallet  = iStockUserCapitalFundService.findBibWallet(userId,"bib");
        StockUserCapitalFund walletSl  = iStockUserCapitalFundService.findBibWallet(userId,"bibsl");
        int flag = iStockUserCapitalFundService.transferBib(userId,transferDto,wallet,walletSl);
      
    }
    @GetMapping(value = "/find/account")
    public ResultVo findAccount(){
        String token = request.getHeader("token");
         Long userId = AuthSign.getUserId(token);
//        Long userId = 66841047L;
        StockUserCapitalFund walletSl  = iStockUserCapitalFundService.findBibWallet(userId,"bibsl");
        Map map = new HashMap();
        map.put("amount",walletSl.getUsableFund());
        return new ResultUtil().setData(map);
    }
    @GetMapping(value = "/find/transferLog")
    public ResultVo transferLog(PageVo pageVo){
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        PageInfo transeferLogDto = iStockUserCapitalFundService.getOperationTransferLog(userId,pageVo);
        return new ResultUtil().setData(transeferLogDto);
    }



    @PostMapping(value = "/financial/calculate")
    public ResultVo calculate(){
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        FrozenDto wallet = iStockUserCapitalFundService.findWallet(userId);
       
        Map<String, Object> data = new HashMap<String, Object>();
        DateTime startdateTime  =new DateTime();
        startdateTime=startdateTime.withHourOfDay(0);
        startdateTime=startdateTime.withMinuteOfHour(0);
        startdateTime=startdateTime.withSecondOfMinute(0);
        DateTime enddateTime  =new  DateTime();
        enddateTime=enddateTime.withHourOfDay(23);
        enddateTime=enddateTime.withMinuteOfHour(59);
        enddateTime=enddateTime.withSecondOfMinute(59);
        Map<String, Object> yesterday = new HashMap<String, Object>();
        Map<String, Object> sharedMap = new HashMap<String, Object>();

        String usdtbib = jedisService.get("vb:ticker:newprice:BIB/USDT");

        BigDecimal totalCapital = wallet.getTotal().add(wallet.getFrozen()==null?new BigDecimal("0"):wallet.getFrozen());
        BigDecimal possessed = iStockUserCapitalFundService.findPossessed(userId, 5, startdateTime.toDate(), enddateTime.toDate());
        yesterday.put("possessed", possessed);

        BigDecimal shared = iStockUserCapitalFundService.findPossessed(userId, 6, startdateTime.toDate(), enddateTime.toDate());
        yesterday.put("shared", shared);

        BigDecimal qty= iStockUserCapitalFundService.findNowQuantity(startdateTime.toDate(),enddateTime.toDate());
        yesterday.put("qty", qty==null?BigDecimal.ZERO:qty);

        BigDecimal circulate = iStockUserCapitalFundService.findSumQuantity();
     
        BigDecimal sharedAmount = iStockUserCapitalFundService.findSumSharedTotal(userId,startdateTime.toDate(),enddateTime.toDate());
        sharedMap.put("sharedAmount", sharedAmount);
   
        BigDecimal sharedTotal = iStockUserCapitalFundService.findSumSharedTotalAll(startdateTime.toDate(),enddateTime.toDate());
        sharedMap.put("sharedTotal", sharedTotal);
      
        data.put("yesterday", yesterday);
      
        data.put("circulate", circulate);
   
        data.put("shared", sharedMap);

        data.put("totalCapitalTrade", totalCapital.setScale(2, BigDecimal.ROUND_DOWN));
        data.put("usdtTotal",new BigDecimal(usdtbib).multiply(totalCapital.setScale(2, BigDecimal.ROUND_DOWN)).setScale(2,BigDecimal.ROUND_DOWN));
        return new ResultUtil().setData(data);
    }

}
