package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.ComDealRule;
import com.github.pagehelper.PageInfo;
import com.util.pageinfoutil.PageVo;

/**
 */
public interface IComDealRuleService extends IService<ComDealRule> {

    PageInfo getAllList(Integer type, PageVo pageVo);
}
