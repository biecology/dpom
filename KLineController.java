package com.rest.controller;

import com.alibaba.fastjson.JSON;
import com.dao.dto.KLineQueryDto;
import com.dao.dto.SellBuyFiveDto;
import com.dao.vo.StockProductVO;
import com.server.constant.RedisKeyPrefix;
import com.server.service.IkLineService;
import com.server.util.CoinCoinDealUtil;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.redis.IJedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("app/kline")
@Slf4j
public class KLineController {

    @Autowired
    private IkLineService ikLineService;
    @Autowired
    private IJedisService jedisService;

    /**

     * @param dto
     * @param request
     * @param bindingResult
     * @return
     */
    @PostMapping("history")
    public ResultVo getKLine(@ModelAttribute @Valid KLineQueryDto dto, HttpServletRequest request, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        List<Object> list = ikLineService.listHistoryByVo(dto, request);
        return new ResultUtil().setData(list);
    }

    /
     *
     * @param request
     * @return
     */
    @GetMapping("goodInfo")
    public ResultVo goodInfo(HttpServletRequest request) {
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        List<StockProductVO> list ;
        if(StringUtils.isEmpty(type)){
            list = ikLineService.getStockInfoByCode(code,true);
        }else{
            list = ikLineService.getStockInfoByCode(code,false);
        }
        List bib =  list.stream().filter(x -> x.getCode().startsWith("BIB")).collect(Collectors.toList());
        List btc =  list.stream().filter(x -> x.getCode().startsWith("BTC")).collect(Collectors.toList());
        List eth =  list.stream().filter(x -> x.getCode().startsWith("ETH")).collect(Collectors.toList());
        List eos =  list.stream().filter(x -> x.getCode().startsWith("EOS")).collect(Collectors.toList());
        bib.addAll(btc);
        bib.addAll(eth);
        bib.addAll(eos);
        list.removeAll(bib);
        list.removeAll(btc);
        list.removeAll(eth);
        list.removeAll(eos);
        bib.addAll(list);
//            }else if(o1.getCode().startsWith("BTC")){
//                return 2;
//            }else if(o1.getCode().startsWith("ETH")){
//                return 3;
//            }else if(o1.getCode().startsWith("EOS")){
//                return 4;
//            }

        return new ResultUtil().setData(bib);
    }

    /**
     *
     * @param request
     * @return
     */
    @GetMapping("buySellFive")
    public ResultVo buySellFive(HttpServletRequest request) {
        String code = request.getParameter("code");
        String str = jedisService.get(RedisKeyPrefix.SELF_GOODS_BUY_AND_SELL_INFO_BACKGROUND + code);


        if (StringUtils.isNotBlank(str)) {
            SellBuyFiveDto dto = JSON.parseObject(str, SellBuyFiveDto.class);
            return new ResultUtil().setData(dto);
        } else {
            return new ResultUtil().setSuccessMsg();
        }
    }

    /**
  
     *
     * @param request
     * @return
     */
    @GetMapping("depth")
    public ResultVo depth(HttpServletRequest request) {
        String code = request.getParameter("code");
        String str = jedisService.get(RedisKeyPrefix.STOCK_DEPTH_DEAL_NEW_KEY + code);
        if (StringUtils.isNotBlank(str)) {
            SellBuyFiveDto dto = JSON.parseObject(str, SellBuyFiveDto.class);
            return new ResultUtil().setData(dto);
        } else {
            return new ResultUtil().setSuccessMsg();
        }
    }

    /
     * @return
     */
    @GetMapping("rankList")
    public ResultVo rankList() {
        List<StockProductVO> list = ikLineService.rankList();
        return new ResultUtil().setData(list);
    }
    /**
   
     */
    @GetMapping("originsInfo")
    public ResultVo originsInfo(@RequestParam("stockCode")String stockCode){
        Object o=ikLineService.originsInfo(stockCode);
        return new ResultUtil().setData(o);
    }


    @GetMapping("test")
    public void test(){
        for (int i = 0; i < 10; i++) {
            System.out.println(CoinCoinDealUtil.getSize("BTC/USDT",true));
        }

    }
}
