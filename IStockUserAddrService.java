package com.server.service;

import com.dao.entity.StockUserAddr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.entity.StockUserWithdrawAddr;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * </p>
 *
 */
public interface IStockUserAddrService extends IService<StockUserAddr> {

    List<StockUserAddr> selectList(Long id);

    void addAddr(StockUserAddr stockUserAddr);


    StockUserAddr createCoinAddr(StockUserAddr addr);

    /**
     *
     * @param code
     * @param addr
     */
    public Byte checkCoinAddrs(String code, String addr);

    /**
     * @param addr
     * @param type
     * @return
     */
    public String bitcoincashAddrConvert(String addr,int type);
}
