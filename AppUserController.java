package com.rest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.base.AppLoginVo;
import com.dao.dto.CoinCoinSelectDto;
import com.dao.dto.StockUserLORDto;
import com.dao.entity.*;
import com.dao.mapper.*;
import com.dao.vo.StockUserCapitalVO;
import com.dao.vo.StockUserVo;
import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.server.annotation.SystemLog;
import com.server.constant.CommonConstant;
import com.server.constant.RedisKeyPrefix;
import com.server.enums.CodeTypeEnum;
import com.server.enums.LogType;
import com.server.service.*;
import com.util.Respons.ResponseMsg;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.StringUtils;
import com.util.auth.AuthSign;
import com.util.exception.CommonException;
import com.util.pageinfoutil.PageVo;
import com.util.redis.IJedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("app/user")
@Slf4j
public class AppUserController {

    @Autowired
    private IStockUserService stockUserService;
    @Autowired
    private IStockUserSignService stockUserSignService;
    @Autowired
    private IJedisService jedisService;
    @Autowired
    private IContentNoticeService contentNoticeService;
    @Autowired
    private ContentNoticeMapper contentNoticeMapper;
    @Autowired
    private StockUserPayWayMapper stockUserPayWayMapper;
    @Autowired
    private IStockUserPayWayService stockUserPayWayService;
    @Autowired
    private IComConfigViewpaperService comConfigViewpaperService;
    @Autowired
    private ComScreenNoticeMapper comScreenNoticeMapper;
    @Autowired
    private AppVersionMapper appVersionMapper;
    @Autowired
    private ComDealRuleMapper comDealRuleMapper;
    @Autowired
    private IComDealRuleService comDealRuleService;
    @Resource
    private ComConfigAgreementMapper agreementMapper;
    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;
    @Resource
    private StockUserMapper stockUserMapper;
    @Autowired
    private IXyBlocksMsgService xyBlocksMsgService;
    @Autowired
    private XyBlocksMsgMapper xyBlocksMsgMapper;
    @Autowired
    private ComConfigAgreementMapper comConfigAgreementMapper;
    @Autowired
    private HttpServletRequest req;

   
    @PostMapping("login")
    
    public ResultVo login(@ModelAttribute @Validated({StockUserLORDto.Login.class}) StockUserLORDto dto, BindingResult bindingResult) throws UnsupportedEncodingException {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        AppLoginVo result = stockUserService.login(dto);
        return new ResultUtil().setData(result);
    }

   
    @GetMapping("loginMsg")

    public ResultVo loginMsg(@RequestParam("account")String account,@RequestParam("id")Long id,@RequestParam("areacode")String areacode) throws UnsupportedEncodingException {
  

     
//        stockUserService.loginMsg(id,account,areacode);

        return new ResultUtil().setSuccessMsg();
    }
    /**
     * 验证码登录
     * @param dto
     * @param bindingResult
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("loginByCode")
  
    public ResultVo loginByCode(@ModelAttribute @Validated({StockUserLORDto.YZMDL.class}) StockUserLORDto dto, BindingResult bindingResult) throws UnsupportedEncodingException {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        AppLoginVo result = stockUserService.loginByCode(dto);
        return new ResultUtil().setData(result);
    }
    /**

     * @param dto
     * @param bindingResult
     * @return
     */
    @PostMapping("register")

    public ResultVo register(@ModelAttribute @Validated({StockUserLORDto.Register.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = stockUserService.register(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg();
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg("Successful operation");
        }
        return null;
    }

    /**

     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("checkCode")

    public ResultVo checkCode(@ModelAttribute @Validated({StockUserLORDto.GetCode.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String code = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_1.getCode() + dto.getAccount() + dto.getAreaCode());
        String value = req.getHeader("language");
      
   
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg();
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg("Successful operation");
        }
        return null;
    }


    @GetMapping("getCode")

    public ResultVo getCode(@ModelAttribute @Validated({StockUserLORDto.GetCode.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = stockUserService.getCode(dto);
        String value = req.getHeader("language");

        System.out.println("language------------"+value);

        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg();
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg("Successful operation");
        }
        return null;
    }

    /**
     *
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value = "userSign", method = RequestMethod.POST)

    public ResultVo applyShop(Long id, HttpServletRequest req,
                              HttpServletResponse res) {
        if (StringUtils.isBlank(id)) {
            throw new CommonException(ResponseMsg.ERROR_PARAM);
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        Map map = stockUserSignService.addOnce(userId);
        return new ResultUtil().setData(map);
    }

    /**
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value = "userInfo", method = RequestMethod.POST)
    public ResultVo userInfo(Long id, HttpServletRequest req,
                             HttpServletResponse res) {
        if (StringUtils.isBlank(id)) {
            throw new CommonException(ResponseMsg.ERROR_PARAM);
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        StockUserVo stockUserVo = stockUserService.userInfo(userId);
        return new ResultUtil().setData(stockUserVo);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("captialFundToltal")
    public ResultVo captialFundToltal(Long id) {
////        String token = req.getHeader("token");
//        Long userId = AuthSign.getUserId(token);
        Long userId = 28803L;
        Map o = stockUserService.captialFundToltal(userId);
        StockUserLORDto dto = new StockUserLORDto();
        dto.setId(userId);
        List<StockUserCapitalVO> list = stockUserService.getCaptialFund(dto);
        BigDecimal cny = BigDecimal.ZERO;
       
        BigDecimal sum_usdt = BigDecimal.ZERO;
        BigDecimal sum_btc = BigDecimal.ZERO;
        for (StockUserCapitalVO stockUserCapitalVO : list) {
          
            if(stockUserCapitalVO.getStockCode().equals("BIB")){
              
                BigDecimal change = new BigDecimal(jedisService.get("vb:ticker:newprice:BIB/USDT"));
             
              
                BigDecimal USDT = stockUserCapitalVO.getFrostFund().multiply(change);
              
              
                sum_usdt=sum_usdt.add(USDT);
               
                BigDecimal usdtToBtcChange = new BigDecimal(jedisService.get("vb:ticker:newprice:BTC/USDT"));
               
                BigDecimal BTC = USDT.divide(usdtToBtcChange,8,RoundingMode.HALF_DOWN);
              
                sum_btc = sum_btc.add(BTC);
            }
          

            if(stockUserCapitalVO.getStockCode().equals("ETH")){
               
                BigDecimal change = new BigDecimal(jedisService.get("vb:ticker:newprice:ETH/BTC"));
             
                BigDecimal BTC = stockUserCapitalVO.getFrostFund().multiply(change);
              
                BigDecimal ethToUsdtChange = new BigDecimal(jedisService.get("vb:ticker:newprice:ETH/USDT"));
             
               
                BigDecimal USDT = stockUserCapitalVO.getFrostFund().multiply(ethToUsdtChange);
            
                sum_usdt = sum_usdt.add(USDT);
                sum_btc = sum_btc.add(BTC);
            }
         
            if(stockUserCapitalVO.getStockCode().equals("BTC")){
                sum_btc = sum_btc.add(stockUserCapitalVO.getFrostFund());
              
                BigDecimal btcToUsdtChange = new BigDecimal(jedisService.get("vb:ticker:newprice:BTC/USDT"));
              
                BigDecimal USDT = stockUserCapitalVO.getFrostFund().multiply(btcToUsdtChange);
                sum_usdt = sum_usdt.add(USDT);
            }
            
            if(stockUserCapitalVO.getStockCode().equals("USDT")){
             
                BigDecimal btcToUsdtChange = new BigDecimal(jedisService.get("vb:ticker:newprice:BTC/USDT"));
                
                /
                BigDecimal BTC = stockUserCapitalVO.getFrostFund().divide(btcToUsdtChange,8, RoundingMode.HALF_DOWN);
               
                sum_btc = sum_btc.add(BTC);
//                BigDecimal RMB = stockUserCapitalVO.getFrostFund().divide((BigDecimal) map.get("USDT"));
                sum_usdt = sum_usdt.add(stockUserCapitalVO.getFrostFund());
            }


        }
        BigDecimal usdt = (BigDecimal)o.get("usdtTotal");
        BigDecimal btc = (BigDecimal)o.get("btcTotal");
        o.put("usdtTotal",usdt.add(sum_usdt));
        o.put("btcTotal",btc.add(sum_btc));


        return new ResultUtil().setData(o);
    }

    /**
    
     * @param dto
     * @param bindingResult
     * @return
     */
    static final String[] statics = new String[]{"BIB","ETH","BTC","USDT"};
    @GetMapping("captialFund")
    public ResultVo getCaptialFund(@ModelAttribute @Validated({StockUserLORDto.ID.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);

//        dto.setId(2L);
        dto.setId(userId);
        List<StockUserCapitalVO> list = stockUserService.getCaptialFund(dto);
        //BIB ETH BTC UDST
        List<StockUserCapitalVO> fileterList = list.stream().filter(sc -> {
            for (String aStatic : statics) {
                if (sc.getStockCode().equalsIgnoreCase(aStatic))
                    return true;
            }
            return false;
        }).collect(Collectors.toList());
        //   List<CapitalFundVo> list = stockUserCapitalFundService.getOneCoinFundInfo(request,dto);
        return new ResultUtil().setData(fileterList);
    }

    /**
    
     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("captialFundPc")
    public ResultVo captialFundPc(@ModelAttribute @Validated({StockUserLORDto.ID.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        List<StockUserCapitalVO> list = stockUserService.getCaptialFund(dto);
        List lists = new ArrayList();
        for(StockUserCapitalVO stockUserCapitalVO : list){
            if("USDT".equalsIgnoreCase(stockUserCapitalVO.getStockCode())||"BTC".equalsIgnoreCase(stockUserCapitalVO.getStockCode())
                    ||"BIB".equalsIgnoreCase(stockUserCapitalVO.getStockCode())||"ETH".equalsIgnoreCase(stockUserCapitalVO.getStockCode())){
                lists.add(stockUserCapitalVO);
            }
        }
        return new ResultUtil().setData(lists);
    }

    @PostMapping("propertyChange")
   
    public ResultVo propertyChange(@ModelAttribute @Validated({StockUserLORDto.ZCDH.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = stockUserService.propertyChange(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    /**

     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @GetMapping("changeRecord")
    public ResultVo changeRecord(@ModelAttribute @Validated({StockUserLORDto.ID.class}) StockUserLORDto dto, BindingResult bindingResult, PageVo pageVo) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        PageInfo<CoinCoinChangeDeal> coinCoinChangeDealPageInfo = stockUserService.changeRecord(dto, pageVo);
        return new ResultUtil().setData(coinCoinChangeDealPageInfo);
    }

    @PostMapping("editPswd")

    public ResultVo editPswd(@ModelAttribute @Validated({StockUserLORDto.XGMM.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = stockUserService.editPswd(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("bindTelOrEmail")

    public ResultVo bindTelOrEmail(@ModelAttribute @Validated({StockUserLORDto.BD.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = stockUserService.bindTelOrEmail(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("updatePswd")
  
    public ResultVo updatePswd(@ModelAttribute @Validated({StockUserLORDto.UpdatePswd.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean result = stockUserService.updatePswd(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("updateTradePswd")

    public ResultVo updateTradePswd(@ModelAttribute @Validated({StockUserLORDto.XGZJMM.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean result = stockUserService.updateTradePswd(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("baseAuth")

    public ResultVo baseAuth(@ModelAttribute @Validated({StockUserLORDto.SMRZ.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean result = stockUserService.baseAuth(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("inspectAuth")

    public ResultVo inspectAuth(@ModelAttribute @Validated({StockUserLORDto.GJRZ.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean result = stockUserService.inspectAuth(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @GetMapping("relieveShop")

    public ResultVo relieveShop(@ModelAttribute @Validated({StockUserLORDto.ID.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean result = stockUserService.relieveShop(dto);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("addPayWay")
    public ResultVo addPayWay(@ModelAttribute @Validated({StockUserLORDto.TJZFFS.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean result = stockUserService.addPayWay(dto);
        String value = req.getHeader("language");
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
    @GetMapping("touchMe")
    public ResultVo touchMe() {
        String result = stockUserService.touchMe();
        return new ResultUtil().setData(result);
    }

    @PostMapping("contentMessage")

    public ResultVo contentMessage(@ModelAttribute @Validated({StockUserLORDto.LY.class}) StockUserLORDto dto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = stockUserService.contentMessage(dto);
        String value = req.getHeader("language");
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
    @GetMapping("dealRule")
    public ResultVo dealRule() {
        List<ComConfigAgreement> result = stockUserService.dealRule(1);
        return new ResultUtil().setData(result);
    }

    @GetMapping("concessionInfo")

    public ResultVo concessionInfo(Long id) {
        Map result = stockUserService.concessionInfo(id);
        return new ResultUtil().setData(result);
    }

    /**

     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @GetMapping("myCustom")
    public ResultVo myCustom(@ModelAttribute @Validated({StockUserLORDto.ID.class}) StockUserLORDto dto, BindingResult bindingResult,PageVo pageVo) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        PageInfo result = stockUserService.myCustom(dto,pageVo);
        return new ResultUtil().setData(result);
    }

    /**

     */
    @GetMapping(value = "getQrcode")
    public void getAgentQrcode(HttpServletRequest req, HttpServletResponse res)
            throws IOException, WriterException {
        ServletOutputStream stream = res.getOutputStream();
        String number = req.getParameter("number");
        if (number == null) {
            throw new CommonException(ResponseMsg.ERROR_PARAM);
        }

        String value = req.getParameter("language");
        if(!"cn".equals(value)){
            value = "eu";
        }
        /*if(!value.equals("cn")){
            value = "eu";
        }*/
        String url = CommonConstant.invitation_HttpPath + "/register_"+value+".html?invitationCode="
                + number;
     
        int width = 200;
      
        int height = 200;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
 
        MatrixToImageWriter.writeToStream(m, "png", stream);
    }

    /**
 
     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @GetMapping("commissionInfo")
    public ResultVo commissionInfo(@ModelAttribute @Validated({StockUserLORDto.ID.class}) StockUserLORDto dto, BindingResult bindingResult,PageVo pageVo) {
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        PageInfo result = stockUserService.commissionInfo(dto,pageVo);
        return new ResultUtil().setData(result);
    }


    /**
   
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    /*@RequestMapping(value = "inAndOutRecord", method = RequestMethod.POST)
    @ResponseBody
    @Deprecated
    @ApiIgnore
    public ResultVo inAndOutRecord(Long id,Integer tradeType, HttpServletRequest req,
                                    HttpServletResponse res, PageVo pageVo) {
        if (StringUtils.isBlank(id,tradeType)) {
            throw new CommonException(ResponseMsg.ERROR_PARAM);
        }

        return new ResultUtil().setData();
    }*/

    @PostMapping("contentNotice")
    public ResultVo contentNotice(PageVo pageVo) {
        PageInfo<ContentNotice> all = contentNoticeService.findAll(pageVo);
        return new ResultUtil().setData(all);
    }

    @PostMapping("contentNoticeDetail")
    public ResultVo contentNoticeDetail(Long id) {
        ContentNotice contentNotice = contentNoticeMapper.selectById(id);
        return new ResultUtil().setData(contentNotice);
    }

    @PostMapping("updatePayType")
    public ResultVo updatePayType(StockUserPayWay stockUserPayWay,Integer payWay) {
        if (StringUtils.isBlank(stockUserPayWay.getId(),payWay)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        stockUserPayWay.setStockUserId(userId);
        stockUserPayWayService.updatePayWayById(stockUserPayWay,payWay);
        String value = req.getHeader("language");
        if (value.equals("cn")) {
            return new ResultUtil().setSuccessMsg(false);
        } else if (value.equals("eu")) {
            return new ResultUtil().setSuccessMsg(true);
        }
        return null;
    }

    @PostMapping("toUpdatePayType")
    public ResultVo updatePayType(Long id) {
        StockUserPayWay stockUserPayWay = stockUserPayWayMapper.selectById(id);
        StockUser stockUser = stockUserMapper.selectById(stockUserPayWay.getStockUserId());
        stockUserPayWay.setUsername(stockUser.getUsername());
        return new ResultUtil().setData(stockUserPayWay);
    }

    @PostMapping("viewpager")
    public ResultVo viewpager(Integer type) {
        List<ComConfigViewpager> allBySort = comConfigViewpaperService.getAllBySort(type);
        return new ResultUtil().setData(allBySort);
    }

    @PostMapping("screenNotice")
    public ResultVo screenNotice() {
        List<ComScreenNotice> comScreenNotices = comScreenNoticeMapper.selectList(new QueryWrapper<ComScreenNotice>());
        return new ResultUtil().setData(comScreenNotices.get(0));
    }

    @PostMapping("appVersion")
    public ResultVo appVersion(Integer type) {
        if (StringUtils.isBlank(type)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        AppVersion appVersion = appVersionMapper.selectOne(new QueryWrapper<AppVersion>().eq("is_deleted", 0).eq("app_type",type));
        return new ResultUtil().setData(appVersion);
    }

    @PostMapping("transactionRules")
    public ResultVo dealRule(PageVo pageVo) {
        PageInfo allList = comDealRuleService.getAllList(2, pageVo);
        return new ResultUtil().setData(allList);
    }

    @PostMapping("transactionRulesDetail")
    public ResultVo dealRuleDetail(Long id) {
        if (StringUtils.isBlank(id)) {
            throw new CommonException(ResponseMsg.ERROR_PARAM);
        }
        ComDealRule comDealRule = comDealRuleMapper.selectById(id);
        return new ResultUtil().setData(comDealRule);
    }

    @PostMapping("comMessage")
    public ResultVo comMessage(PageVo pageVo) {
        boolean en = false;
        if (StringUtils.isBlank(pageVo.getPageNumber(),pageVo.getPageSize())) {
//            if(req.getHeader("language").equalsIgnoreCase("cn")){
//                throw new CommonException(ResponseMsg.ERROR_PARAM);
//            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
//                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
//            }


        }
        if(req.getHeader("language").equalsIgnoreCase("eu")){
            System.out.println(req.getHeader("language"));
            en = true;
        }
        PageInfo allList = xyBlocksMsgService.getAllList(pageVo,en);
        return new ResultUtil().setData(allList);
    }

    @PostMapping("comMessageDetail")
    public ResultVo comMessageDetail(Long id) {
        if (StringUtils.isBlank(id)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        XyBlocksMsg xyBlocksMsg = xyBlocksMsgMapper.selectById(id);
        return new ResultUtil().setData(xyBlocksMsg);
    }

    @GetMapping("getExchange")
    public ResultVo getExchange(@RequestParam("code")String code){
        Object o=stockUserService.getExchange(code);
        return new ResultUtil().setData(o);
    }

    /**
     * @return
     */
    @GetMapping("feeInfo")
    public ResultVo feeInfo(){
        ComConfigAgreement agreement = agreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>()
                .eq("type", 8));
        return new ResultUtil().setData(agreement);
    }

    /**
     * @return
     */
    @GetMapping("ours")
    public ResultVo ours(){
        ComConfigAgreement agreement = agreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>()
                .eq("type", 6));
        return new ResultUtil().setData(agreement);
    }

    /**
     * @return
     */
    @GetMapping("money")
    public ResultVo money(){
        ComConfigAgreement agreement = agreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>()
                .eq("type", 7));
        return new ResultUtil().setData(agreement);
    }

    /**
     * @return
     */
    @GetMapping("privacy")
    public ResultVo privacy(){
        ComConfigAgreement agreement = agreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>()
                .eq("type", 4));
        return new ResultUtil().setData(agreement);
    }

    /**
     * @return
     */
    @GetMapping("service")
    public ResultVo service(){
        ComConfigAgreement agreement = agreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>()
                .eq("type", 9));
        return new ResultUtil().setData(agreement);
    }


    @GetMapping("falv")
    public ResultVo falv(){
        ComConfigAgreement agreement = agreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>()
                .eq("type", 5));
        return new ResultUtil().setData(agreement);
    }
   
    @GetMapping("moneyDetail")
    public ResultVo moneyDetail(@RequestParam("id")Long id,String code,PageVo vo){
       
        PageInfo<StockUserMoneyDetail>pageInfo=stockUserService.moneyDetail(id,code,vo);
        return new ResultUtil().setData(pageInfo);
    }


    @PostMapping("indexContent")
    public ResultVo indexContent(Integer type) {
        if (StringUtils.isBlank(type)) {
            if(req.getHeader("language").equalsIgnoreCase("cn")){
                throw new CommonException(ResponseMsg.ERROR_PARAM);
            }else if(req.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException(ResponseMsg.ERROR_PARAM_EU);
            }
        }
        ComConfigAgreement configAgreement = comConfigAgreementMapper.selectOne(new QueryWrapper<ComConfigAgreement>().eq("type", type));
        return new ResultUtil().setData(configAgreement);
    }

    /**
 
     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("myInvitation")
    public ResultVo myInvitation(@ModelAttribute @Validated({CoinCoinSelectDto.WDYQ.class})CoinCoinSelectDto dto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = req.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        Object t =stockUserService.myInvitation(dto);
        return new ResultUtil().setData(t);
    }

    /**
     
     * @return
     */
    @GetMapping("question")
    public ResultVo question(PageVo pageVo){
        PageInfo t =stockUserService.question(pageVo);
        return new ResultUtil().setData(t);
    }

    /**
    
     * @return
     */
    @GetMapping("getSysConfig")
    public ResultVo getSysConfig(){
        Map t =stockUserService.getSysConfig();
        return new ResultUtil().setData(t);
    }

    /**
    
     * @return
     */
    @GetMapping("getFundList")
    public ResultVo getFundList(@RequestParam("id")Long id){
        List t =stockUserService.getFundList(id);
        return new ResultUtil().setData(t);
    }

}

