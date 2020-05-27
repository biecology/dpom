package com.server.service;

import com.dao.entity.ContentNotice;
import com.github.pagehelper.PageInfo;
import com.util.pageinfoutil.PageVo;

public interface IContentNoticeService {

    PageInfo<ContentNotice> findAll(PageVo pageVo);
}
