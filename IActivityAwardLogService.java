package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.ActivityAwardLog;
import com.util.pageinfoutil.PageVo;

import java.util.Map;

public interface IActivityAwardLogService extends IService<ActivityAwardLog> {
    Map<String,Object> getChristmasAward(Long userId, PageVo pageVo);
}
