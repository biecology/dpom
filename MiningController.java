package com.rest.controller;

import com.alibaba.fastjson.JSON;
import com.dao.dto.MiningDto;
import com.dao.entity.*;
import com.dao.vo.MiningIndexVo;
import com.github.pagehelper.PageInfo;
import com.server.annotation.SystemLog;
import com.server.constant.RedisKeyPrefix;
import com.server.service.IMiningService;
import com.server.util.CoinCoinDealUtil;
import com.util.MathUtil;
import com.util.ResultUtil;
import com.util.ResultVo;
import com.util.auth.AuthSign;
import com.util.pageinfoutil.PageVo;
import com.util.redis.IJedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("app/mining")
@Slf4j
public class MiningController {

    @Autowired
    private IMiningService miningService;
    @Autowired
    private IJedisService jedisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpServletRequest request;

    /**
 
     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("index")
    public ResultVo indexData(@ModelAttribute @Validated(MiningDto.SY.class) MiningDto dto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        MiningIndexVo vo= miningService.indexData(dto);
        return new ResultUtil().setData(vo);
    }

    /*
   
     * @param dto
     * @param bindingResult
     * @return
     */
    @GetMapping("paoList")
    public ResultVo paoList(@ModelAttribute @Validated(MiningDto.PPLB.class) MiningDto dto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        Object vo=miningService.paoList(dto);
        return new ResultUtil().setData(vo);
    }

    @PostMapping("receivePo")
   
    public ResultVo receivePo(@ModelAttribute @Validated(MiningDto.LQPP.class) MiningDto dto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = request.getHeader("token");
        Long userId = AuthSign.getUserId(token);
        dto.setId(userId);
        boolean vo= miningService.ReceivePo(dto);
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
    @GetMapping("activiteRule")
    public ResultVo activiteRule(){
        ComConfigAgreement vo= miningService.activiteRule();
        return new ResultUtil().setData(vo);
    }

    /**
     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @GetMapping("miningInfo")
    public ResultVo miningInfo(@ModelAttribute @Validated(MiningDto.SYMX.class) MiningDto dto, BindingResult bindingResult, PageVo pageVo){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        PageInfo<StockUserMiningInfo> vo= miningService.miningInfo(dto,pageVo);
        return new ResultUtil().setData(vo);
    }

    /**
   
     * @param dto
     * @param bindingResult
     * @param pageVo
     * @return
     */
    @GetMapping("forceInfo")
    public ResultVo forceInfo(@ModelAttribute @Validated(MiningDto.YLMX.class) MiningDto dto, BindingResult bindingResult, PageVo pageVo){
        if (bindingResult.hasFieldErrors()) {
            log.info("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ResultUtil().setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        PageInfo<StockUserForceInfo> vo= miningService.forceInfo(dto,pageVo);
        return new ResultUtil().setData(vo);
    }

}
