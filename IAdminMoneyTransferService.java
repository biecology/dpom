package com.server.service;

import com.dao.entity.AdminMoneyTransfer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface IAdminMoneyTransferService extends IService<AdminMoneyTransfer> {

    List<AdminMoneyTransfer> selectByStatus(AdminMoneyTransfer adminMoneyTransfer);

    int updateByPrimaryKeySelective(AdminMoneyTransfer t);
}
