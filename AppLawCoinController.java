package com.rest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.entity.*;
import com.dao.mapper.*;
import com.github.pagehelper.PageInfo;
import com.dao.vo.LawCoinReq;
import com.server.annotation.SystemLog;
import com.server.constant.CommonConstant;
import com.server.enums.PayTypeEnum;
import com.server.service.ILawCoinDealService;
import com.server.service.IStockApplyService;
import com.server.service.IStockUserPayWayService;
import com.server.service.IStockUserService;
import com.util.DateUtil;
import com.util.Respons.ResponseMsg;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.StringUtils;
import com.util.auth.AuthSign;
import com.util.exception.CommonException;
import com.util.pageinfoutil.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/app/lawCoin")
@Transactional
public class AppLawCoinController {

    @Autowired
    private ILawCoinDealService appLawCoinDealService;
    @Autowired
    private StockUserPayWayMapper stockUserPayWayMapper;
    @Autowired
    private LawCoinRecordMapper lawCoinRecordMapper;
    @Autowired
    private IStockUserPayWayService stockUserPayWayService;
    @Autowired
    private LawCoinAppealMapper lawCoinAppealMapper;
    @Autowired
    private IStockApplyService stockApplyService;
    @Autowired
    private ComConfigSysMapper comConfigSysMapper;
    @Autowired
    private StockUserMapper stockUserMapper;
    @Autowired
    private HttpServletRequest request;

    /**
     * @param lawCoinReq
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value = "addBill", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo addBill(LawCoinReq lawCoinReq, HttpServletRequest req,
                            HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        if (StringUtils.isBlank(userId, lawCoinReq.getMaxPrice(), lawCoinReq.getMinPrice()
                , lawCoinReq.getTransNum(), lawCoinReq.getTradeType(), lawCoinReq.getPayType())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        boolean addBillResult = appLawCoinDealService.addBill(lawCoinReq, req);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**
     * @return
     */
    @RequestMapping(value = "fbMinNum", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo fbMinNum() {
        ComConfigSys name = comConfigSysMapper.selectOne(new QueryWrapper<ComConfigSys>().eq("name", CommonConstant.fb_open_min_num));
        return new ResultUtil().setData(name);
    }

    /**

     */
    @RequestMapping(value = "lawCoinDeal", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo lawCoinDeal(LawCoinReq lawCoinReq, HttpServletRequest req,
                                           HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        if (StringUtils.isBlank(userId, lawCoinReq.getTransNum(), lawCoinReq.getTradeType(), lawCoinReq.getEntrustNo())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        LawCoinRecord addBillResult = appLawCoinDealService.lawCoinDeal(lawCoinReq, req);
        return new ResultUtil().setData(addBillResult);
    }

    
    @RequestMapping(value = "lawCoinTrading", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo lawCoinTrading(LawCoinReq lawCoinReq, HttpServletRequest req,
                                              HttpServletResponse res, PageVo pageVo) {

        if (StringUtils.isBlank(lawCoinReq.getTradeType())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        PageInfo<?> list = appLawCoinDealService.selectLawCoinList(lawCoinReq, req, pageVo);

        return new ResultUtil().setData(list);
    }

 

    @RequestMapping(value = "selBuySellInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo selBuySellInfo(LawCoinReq lawCoinReq, HttpServletRequest req,
                                              HttpServletResponse res, PageVo pageVo) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        PageInfo<?> list = null;
        if (StringUtils.isBlank(userId)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        if (lawCoinReq.getTradeType() != null) {
            list = appLawCoinDealService.selectLawCoinRecord(lawCoinReq, req, pageVo);
        } else {
            list = appLawCoinDealService.selectLawCoinRecordAll(lawCoinReq, req, pageVo);
        }
        return new ResultUtil().setData(list);
    }

    @RequestMapping(value = "cancelLawCoinDealBill", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo cancelLawCoinDealBill(LawCoinReq lawCoinReq, HttpServletRequest req,
                                                     HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        if (StringUtils.isBlank(userId, lawCoinReq.getOrderId(), lawCoinReq.getTradeType())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        appLawCoinDealService.cancelLawCoinDealBill(lawCoinReq, req);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

 
    @RequestMapping(value = "sellBuyConfirme", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo sellBuyConfirme(LawCoinReq lawCoinReq, HttpServletRequest req,
                                               HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        if (StringUtils.isBlank(userId, lawCoinReq.getOrderId())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        appLawCoinDealService.sellBuyConfirme(lawCoinReq, req);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @RequestMapping(value = "cancelLawCoinRecord", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo cancelLawCoinRecord(LawCoinReq lawCoinReq, HttpServletRequest req,
                                                   HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        if (StringUtils.isBlank(userId, lawCoinReq.getOrderId())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        appLawCoinDealService.cancelLawCoinRecord(lawCoinReq, req);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

 
    @RequestMapping(value = "appealLawCoinRecord", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo appealLawCoinRecord(LawCoinReq lawCoinReq, LawCoinAppeal lawCoinAppeal, HttpServletRequest req,
                                                   HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        lawCoinReq.setId(userId);
        if (StringUtils.isBlank(userId, lawCoinReq.getOrderId())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        appLawCoinDealService.appealLawCoinRecord(lawCoinReq, req, lawCoinAppeal);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**
     * @param id 用户ID
     * @param orderId 交易订单id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value = "sellConfirm", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo sellConfirm(Long id,Long orderId,HttpServletRequest req,
                                        HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        if (StringUtils.isBlank(userId,orderId)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        ResultVo resultVo = new ResultVo();
        try {
            appLawCoinDealService.sellConfirm(userId,orderId);
        }catch (Exception e){
            resultVo.setCode(402);
            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value = "applyShop", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo applyShop(Long id,HttpServletRequest req,
                                HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        if (StringUtils.isBlank(userId)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        appLawCoinDealService.applyShop(userId);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**
     * @return
     */
    @RequestMapping(value = "applyShopPro", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo applyShopPro() {
        ComConfigSys name = comConfigSysMapper.selectOne(new QueryWrapper<ComConfigSys>().eq("name", CommonConstant.freeze_fee));
        return new ResultUtil().setData(name);
    }

    /**
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value = "payList", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo payList(Long id,HttpServletRequest req,
                              HttpServletResponse res) {
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        if (StringUtils.isBlank(userId)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        List<StockUserPayWay> stockUserPayWays = stockUserPayWayMapper.selectList(new QueryWrapper<StockUserPayWay>().eq("stock_user_id", userId));
        List list = new ArrayList();
        Map map = null;
        if(stockUserPayWays.size()>0){
            for(StockUserPayWay stockUserPayWay : stockUserPayWays){
                map = new HashMap();
                switch (stockUserPayWay.getPayType()){
                    case "1":
                        map.put("id",stockUserPayWay.getId());
                        map.put("payType",stockUserPayWay.getPayType());
                        map.put("account",stockUserPayWay.getWxAccount());
                        map.put("qrCode",stockUserPayWay.getWxImg());
                        map.put("status",stockUserPayWay.getDisable());
                        break;
                    case "2":
                        map.put("id",stockUserPayWay.getId());
                        map.put("payType",stockUserPayWay.getPayType());
                        map.put("account",stockUserPayWay.getAlipayAccount());
                        map.put("qrCode",stockUserPayWay.getAliImg());

                        map.put("status",stockUserPayWay.getDisable());
                        break;
                    case "3":
                        map.put("id",stockUserPayWay.getId());
                        map.put("payType",stockUserPayWay.getPayType());
                        map.put("account",stockUserPayWay.getBankCardNo());
                        map.put("status",stockUserPayWay.getDisable());
                        map.put("bankCardOpenBank",stockUserPayWay.getBankCardOpenBank());
                        map.put("AccountOpeningBranch",stockUserPayWay.getBankCardUnionNo());
                        break;
                        default:
                }
                list.add(map);
            }
        }
        return new ResultUtil().setData(list);
    }

    /**
     * @param userId
     * @param payId
     * @param flag 
     * @param req
     * @param res
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "updatePayWay", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo updatePayWay(Long userId,Long payId,Integer flag, HttpServletRequest req,
                                    HttpServletResponse res, PageVo pageVo) {
        String token = request.getHeader("token");
        userId = AuthSign.getUserId(token);
        if (StringUtils.isBlank(userId,payId,flag)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        stockUserPayWayService.updateUserPayWay(userId,payId,flag);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**

     */
    @RequestMapping(value = "sellBuyRecord", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo userLawCoinBill(LawCoinReq lawCoinReq, HttpServletRequest req,
                                               HttpServletResponse res, PageVo pageVo) {
        if (StringUtils.isBlank(lawCoinReq.getTradeType(), lawCoinReq.getId())) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        PageInfo<?> list = appLawCoinDealService.selectUserLawCoinBill(lawCoinReq, req, pageVo);
        return new ResultUtil().setData(list);
    }

    /**
     */
    @RequestMapping(value = "orderDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo orderDetail(String dealEntrustNo, Long userId, HttpServletRequest req,
                                    HttpServletResponse res, PageVo pageVo) {
        String token = request.getHeader("token");
        userId = AuthSign.getUserId(token);
        if (StringUtils.isBlank(dealEntrustNo, userId)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        LawCoinRecord lawCoinRecord = appLawCoinDealService.orderDetail(dealEntrustNo, userId);
        Long sellId = lawCoinRecord.getSellId();
        List<StockUserPayWay> stockUserPayWays = stockUserPayWayMapper.selectList(new QueryWrapper<StockUserPayWay>().eq("stock_user_id", sellId).in("pay_type"));
        lawCoinRecord.setStockUserPayWays(stockUserPayWays);
        LawCoinAppeal lawCoinAppeal = lawCoinAppealMapper.selectOne(new QueryWrapper<LawCoinAppeal>().eq("entrust_no", lawCoinRecord.getDealEntrustNo()));
        if(lawCoinAppeal!=null){
            lawCoinRecord.setCommand(lawCoinAppeal.getCommand());
        }else{
            lawCoinRecord.setCommand(null);
        }
        return new ResultUtil().setData(lawCoinRecord);
    }

    /**
     */
    @RequestMapping(value = "applyCoin", method = RequestMethod.POST)
    @ResponseBody

    public ResultVo applyCoin(StockApply stockApply,HttpServletRequest req, HttpServletResponse res) {
        log.error("----stockApply123123----------"+stockApply.toString());
        stockApply.setCreateTime(new Date());
        log.error("----stockApply----------"+stockApply.toString());
        stockApplyService.coinApply(stockApply);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**

     */
    @RequestMapping(value = "showPrice", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo showPrice(HttpServletRequest req,
                              HttpServletResponse res) {
        ComConfigSys comConfigSys = comConfigSysMapper.selectOne(new QueryWrapper<ComConfigSys>().eq("name", "ck_price"));
        return new ResultUtil().setData(comConfigSys);
    }



}
