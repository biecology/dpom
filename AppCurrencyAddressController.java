package com.rest.controller;

import com.dao.entity.StockUserWithdrawAddr;
import com.google.common.collect.Maps;
import com.server.enums.CoinTypeAddrEnum;
import com.server.service.AppCurrencyAddressService;
import com.server.service.IStockUserAddrService;
import com.util.Respons.ResponseMsg;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.StringUtils;
import com.util.auth.AuthSign;
import com.util.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author
 * @date
 */
@Controller
@RequestMapping(value = {"app/currencyAddress"})
public class AppCurrencyAddressController {
    @Autowired
    private AppCurrencyAddressService appCurrencyAddressService;
    @Autowired
    private IStockUserAddrService stockUserAddrService;
    @Autowired
    private HttpServletRequest request;

    /**
     * @param stockUserAddr
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo add(StockUserWithdrawAddr stockUserAddr) {
    
        String value = request.getHeader("language");
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        stockUserAddr.setStockUserId(userId.intValue());
        String code = "";
        String addr = stockUserAddr.getToAddr();
        if(stockUserAddr.getType()==1){
            code  = "ETH";
        }else if(stockUserAddr.getType()==2){
            code = "BTC";
        }if(stockUserAddr.getType()==7){
            code  = "USDT";
        }else if(stockUserAddr.getType()==8){
            code = "BIB";
        }
        Byte type = stockUserAddrService.checkCoinAddrs(code, addr);

        StockUserWithdrawAddr userAddr = appCurrencyAddressService.selectByUserIdAndAddr(stockUserAddr);
        
        stockUserAddr.setCode(code);
        stockUserAddr.setType(type.intValue());
        int data = appCurrencyAddressService.addCurrencyAddress(stockUserAddr);

        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**
     *
     * @param stockUserAddr
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
   
    public ResultVo del(StockUserWithdrawAddr stockUserAddr) {
        int data = appCurrencyAddressService.delCurrencyAddress(stockUserAddr);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }


}
