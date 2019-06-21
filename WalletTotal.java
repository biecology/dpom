package com.huagu.vcoin.main.dao;

import com.huagu.coa.common.cons.AppDB;
import com.huagu.coa.common.cons.MyQuery;

import cn.cerc.jdb.core.IHandle;
import site.jayun.vcoin.bourse.dao.WalletAmount;

public class WalletTotal {
    private IHandle handle;
    public double totalQty;
    public double frozenQty;
    
    public WalletTotal(IHandle handle) {
        this.handle = handle;
    }
    
    public boolean exec(int coinId) {
        MyQuery ds = new MyQuery(handle);
        ds.add("SELECT SUM(ftotal) AS totalQty,SUM(ffrozen) AS frozenQty FROM %s", AppDB.fvirtualwallet);
        ds.add("WHERE fvi_fid=%s AND (ftotal<>0 OR ffrozen<>0)", coinId);
        ds.add("and fuid<>%s", WalletAmount.masterUserId);
        ds.open();
        if(ds.eof())
            return false;
        
        this.totalQty = ds.getDouble("totalQty") + ds.getDouble("frozenQty");
        this.frozenQty = ds.getDouble("frozenQty");
        return true;
    }
}
