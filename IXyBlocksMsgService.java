package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.XyBlocksMsg;
import com.github.pagehelper.PageInfo;
import com.util.pageinfoutil.PageVo;


public interface IXyBlocksMsgService extends IService<XyBlocksMsg> {

    PageInfo getAllList(PageVo pageVo,boolean en);
}
