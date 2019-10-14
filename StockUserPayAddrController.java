package com.rest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.entity.StockUser;
import com.dao.entity.StockUserAddr;
import com.dao.entity.StockUserCharge;
import com.dao.entity.StockUserWithdrawAddr;
import com.dao.mapper.StockUserAddrMapper;
import com.dao.mapper.StockUserMapper;
import com.dao.mapper.StockUserWithdrawAddrMapper;
import com.dao.vo.GoldEntryAndExitVo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.server.constant.CommonConstant;
import com.server.constant.RedisKeyPrefix;
import com.server.enums.CodeTypeEnum;
import com.server.enums.CoinTypeAddrEnum;
import com.server.service.*;
import com.util.RequestPathUtil;
import com.util.Respons.ResponseMsg;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.StringUtils;
import com.util.auth.AuthSign;
import com.util.exception.CommonException;
import com.util.pageinfoutil.PageVo;
import com.util.redis.IJedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/app/addr")
@Transactional
public class StockUserPayAddrController {


    @Autowired
    private IStockUserAddrService stockUserAddrService;
    @Autowired
    private StockUserMapper stockUserMapper;
    @Autowired
    private StockUserAddrMapper stockUserAddrMapper;
    @Autowired
    private AppOutMoneyService appOutMoneyService;
    @Autowired
    private IStockUserChargeService stockUserChargeService;
    @Autowired
    private IStockUserWithdrawAddrService stockUserWithdrawAddrService;
    @Autowired
    private AppCurrencyAddressService appCurrencyAddressService;
    @Autowired
    private IJedisService jedisService;
    @Autowired
    private HttpServletRequest req;

    /**
     * 提币地址列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addrList", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody

    public ResultVo currencyAddressList(@RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        Map<String, List<StockUserWithdrawAddr>> tmpMap = Maps.newHashMap();
        for (CoinTypeAddrEnum coin : CoinTypeAddrEnum.values()) {
            System.out.println(coin.getCode());
            List<StockUserWithdrawAddr> tmpList = appCurrencyAddressService.queryCurrencyAddress(id, coin.getCode());
            tmpMap.put(coin.getMessage(), tmpList);
        }
        return new ResultUtil().setData(tmpMap);
    }

    /**
     
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addrListPc", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
   
    public ResultVo addrListPc(@RequestParam(value = "id", required = false) Long id,String codeType) {
        if (id == null) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        List<StockUserWithdrawAddr> stockUserAddrs = stockUserWithdrawAddrService.list(new QueryWrapper<StockUserWithdrawAddr>().eq("stock_user_id", id).eq("type", codeType).eq("is_deleted",0));
        return new ResultUtil().setData(stockUserAddrs);
    }

   

    @RequestMapping(value = "createCoinAddr", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo createCoinAddr(StockUserAddr addr) {
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        StockUser stockUser = stockUserMapper.selectById(userId);
        if (null != stockUser) {
           
        } else {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        addr.setAccount(stockUser.getUserUid());
        StockUserAddr result = stockUserAddrService.createCoinAddr(addr);
        String codeUrlImg = RequestPathUtil.getReqHttpPath() + "/app/addr/getAddrQrcode?addr=" + result.getAddr();
        result.setSalt(null);
        result.setCodeUrlImg(codeUrlImg);
        return new ResultUtil().setData(result);
    }

    @GetMapping(value = "getAddrQrcode")
    public void getAgentQrcode(String addr,HttpServletRequest req, HttpServletResponse res)
            throws IOException, WriterException {
        ServletOutputStream stream = res.getOutputStream();
        String url = addr;
      
        int width = 200;
        
        int height = 200;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
       
        MatrixToImageWriter.writeToStream(m, "png", stream);
    }

 
    @RequestMapping(value = "getGoldEntryAndExitList", method = RequestMethod.GET)
    @ResponseBody
  
    public ResultVo getGoldEntryAndExitList(GoldEntryAndExitVo vo, PageVo pageUtil) {
        if (null == vo.getType() || null == vo.getId() || null == vo.getStockCode()) {
            return new ResultUtil().setErrorMsg();
        }
        PageInfo<StockUserCharge> list = stockUserChargeService.listUserCharge(vo, pageUtil);
        return new ResultUtil().setData(list);
    }

    @RequestMapping(value = "applyCharge", method = RequestMethod.POST)
    
    @ResponseBody
    public ResultVo applyCharge(StockUserCharge userCharge, HttpServletRequest req,
                                           HttpServletResponse res) {
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        String tradePwd = req.getParameter("tradePwd");
        String stockCode = req.getParameter("stockCode");
        if (StringUtils.isBlank(userCharge.getUsdFee(), userCharge.getId(), tradePwd, userCharge.getWalletAddr(), stockCode)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        String addr = userCharge.getWalletAddr();
        Byte type = stockUserAddrService.checkCoinAddrs(stockCode, addr);
        StockUser stockUser = stockUserMapper.selectById(userId);
        if (stockUser.getIsDeleted() || stockUser.getIsDisable()) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.DISABLE);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.DISABLE_EU);
            }
        }
        if (!tradePwd.equalsIgnoreCase(stockUser.getTradePwd())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.DEAL_PWD_ERROR);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.DEAL_PWD_ERROR_EU);
            }
        }
        userCharge.setStockCode(userCharge.getCode());
        userCharge.setCode(stockCode);
        appOutMoneyService.outCoinApply(userCharge, stockUser, req);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

   
    @RequestMapping(value = "getTbFee", method = RequestMethod.POST)

    @ResponseBody
    public ResultVo getTbFee(StockUserCharge userCharge, HttpServletRequest req,
                                        HttpServletResponse res) {

        if (StringUtils.isBlank(userCharge.getCode())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        Map<String, Object> code = appOutMoneyService.selectGetTbFee(userCharge);
        return new ResultUtil().setData(code);
    }

  
    @GetMapping("stockCode")
    public ResultVo stockCode(PageVo pageVo){
        Map map = new HashMap();
        map.put("stockCode","USDT");
        map.put("stockType",2);
        Map map1 = new HashMap();
        map1.put("stockCode","BTC");
        map1.put("stockType",2);
        Map map2 = new HashMap();
        map2.put("stockCode","ETH");
        map2.put("stockType",1);
        Map map3 = new HashMap();
        map3.put("stockCode","BIB");
        map3.put("stockType",1);
        List list = new ArrayList();
        list.add(map2);
        list.add(map1);
        list.add(map);
        list.add(map3);
        return new ResultUtil().setData(list);
    }

    @RequestMapping(value = "testFind", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo testFind() {
        String addr = CommonConstant.WALLET_QUERY_ORDER_RECORD + "&address=0x9cc10118A53803917693908Db6aC233d230C19e7";
        log.error(addr);
        return new ResultUtil().setData(addr);
    }

}
