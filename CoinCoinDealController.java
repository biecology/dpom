package com.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.dao.dto.CoinCoinSelectDto;
import com.dao.entity.Stock;
import com.server.annotation.SystemLog;
import com.server.constant.RabbitMqKeysPrefix;
import com.dao.dto.CoinCoinDto;
import com.dao.dto.QueueCoinCoinBillDto;
import com.server.constant.RedisKeyPrefix;
import com.server.service.ICoinCoinDealService;
import com.server.service.IStockDealService;
import com.server.util.CoinCoinDealUtil;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.auth.AuthSign;
import com.util.pageinfoutil.PageVo;
import com.util.redis.IJedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("app/coincoin")
@Slf4j
public class CoinCoinDealController {

    @Autowired
    private ICoinCoinDealService coinCoinDealService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IStockDealService stockDealService;
    @Autowired
    private IJedisService jedisService;
    private static String SPECIAL_USER_IDS[]={"6184","40680","881301070"};


 
    @PostMapping("addBill")
    public ResultVo addBill(@ModelAttribute @Validated({CoinCoinDto.Add.class}) CoinCoinDto dto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token=request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        addBillAndRabbit(dto);
/*
        Object bill=coinCoinDealService.addBill(dto);
        QueueCoinCoinBillDto queueCoinCoinBillVO = new QueueCoinCoinBillDto();
        queueCoinCoinBillVO.setCoinCoinDto(dto);
        queueCoinCoinBillVO.setObject(bill);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ExchangeTypes.TOPIC, RabbitMqKeysPrefix.PUBLISH_QUEUE_COIN_COIN_BILL_VO_CHANEL, JSONObject.toJSONString(queueCoinCoinBillVO),correlationData);
*/
        if(Arrays.asList(SPECIAL_USER_IDS).contains(String.valueOf(userId)) &&"BIB/ETH".equals(dto.getStockCode())){
            try{
                syncBtcUsdt(dto);
            }catch (Exception e){
              
            }
        }
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg();
        } else  {
            return new ResultUtil().setSuccessMsg("Successful operation");
        }
    }
   
    private void syncBtcUsdt(CoinCoinDto dto){
        BigDecimal btcUsdtPrice = new BigDecimal(jedisService.get(RedisKeyPrefix.STOCK_GOODS_NEWPRICE_PREKEY + "BTC/USDT"));
        BigDecimal ethUsdtPrice = new BigDecimal(jedisService.get(RedisKeyPrefix.STOCK_GOODS_NEWPRICE_PREKEY + "ETH/USDT"));
        CoinCoinDto usdtDto = new CoinCoinDto();
        BeanUtils.copyProperties(dto,usdtDto);
        BigDecimal usdtTargetPrice = dto.getEntrustPrice().multiply(ethUsdtPrice).setScale(6,BigDecimal.ROUND_HALF_UP);
       
        usdtDto.setStockCode("BIB/USDT");
        usdtDto.setEntrustPrice(usdtTargetPrice);
        addBillAndRabbit(usdtDto);
        BigDecimal btcTargetPrice = usdtTargetPrice.divide(btcUsdtPrice,8,BigDecimal.ROUND_HALF_UP);
        CoinCoinDto btcDto = new CoinCoinDto();
        BeanUtils.copyProperties(dto,btcDto);
        btcDto.setStockCode("BIB/BTC");
        btcDto.setEntrustPrice(btcTargetPrice);
        addBillAndRabbit(btcDto);
    }
    void addBillAndRabbit(CoinCoinDto dto){
        Object bill=coinCoinDealService.addBill(dto);
        QueueCoinCoinBillDto queueCoinCoinBillVO = new QueueCoinCoinBillDto();
        queueCoinCoinBillVO.setCoinCoinDto(dto);
        queueCoinCoinBillVO.setObject(bill);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ExchangeTypes.TOPIC, RabbitMqKeysPrefix.PUBLISH_QUEUE_COIN_COIN_BILL_VO_CHANEL, JSONObject.toJSONString(queueCoinCoinBillVO),correlationData);

    }

    @PostMapping(value = "cancelBill")

    public ResultVo cancelBill(@ModelAttribute @Validated({CoinCoinDto.Cancel.class})CoinCoinDto dto,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token=request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        coinCoinDealService.cancelBill(dto);
        String value = request.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg();
        } else  {
            return new ResultUtil().setSuccessMsg("Successful operation");
        }
    }

    /**

     */
    @PostMapping("selCoinCoin")
    public  ResultVo selectCoinCoin(@ModelAttribute @Validated({CoinCoinDto.EntrustBill.class})CoinCoinDto dto, BindingResult bindingResult, PageVo pageVo) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token=request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        Map<String,Object> map = coinCoinDealService.selectCoinCoin(dto, pageVo);
        return new ResultUtil().setData(map);
    }

    /**
  
     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @PostMapping("selCoinCoinByHistory")
    public  ResultVo selCoinCoinByHistory(@ModelAttribute @Validated({CoinCoinDto.EntrustBillHistory.class})CoinCoinDto dto, BindingResult bindingResult, PageVo pageVo) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token=request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        Map<String,Object> map = coinCoinDealService.selCoinCoinByHistory(dto, pageVo);
        return new ResultUtil().setData(map);
    }


    /**
    
     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @GetMapping("successDeal")
    public ResultVo successDeal(@ModelAttribute @Validated({CoinCoinSelectDto.SuccessDeal.class})CoinCoinSelectDto dto,BindingResult bindingResult, PageVo pageVo){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token=request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        Map voPageInfo =coinCoinDealService.successDeal(dto,pageVo);
        return new ResultUtil().setData(voPageInfo);
    }

    /*
     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("codeInfo")
    public ResultVo codeInfo(@ModelAttribute @Validated({CoinCoinSelectDto.BZXQ.class})CoinCoinSelectDto dto,BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        Stock voPageInfo =coinCoinDealService.codeInfo(dto);
        return new ResultUtil().setData(voPageInfo);
    }

    /**
     
     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @PostMapping("successDealInfo")
    public ResultVo successDealInfo(@ModelAttribute @Validated({CoinCoinSelectDto.SuccessDealInfo.class})CoinCoinSelectDto dto,BindingResult bindingResult, PageVo pageVo){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        Object t =coinCoinDealService.successDealInfo(dto,pageVo);
        return new ResultUtil().setData(t);
    }

    /**
     * 
     * @return
     */
    @GetMapping("stockPair")
    public ResultVo stockPair(){
       // Object o = coinCoinDealService.stockPair();
        Map map = stockDealService.getCodeAll();
        return new ResultUtil().setData(map);
    }

    /**
     * 
     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("getFount")
    public ResultVo getFount(@ModelAttribute @Validated({CoinCoinSelectDto.HQZC.class})CoinCoinSelectDto dto,BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token=request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        Object t =coinCoinDealService.getFount(dto);
        return new ResultUtil().setData(t);
    }


}
