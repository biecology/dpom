package com.huagu.vcoin.main.service.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FintrolinfoDAO;
import com.huagu.vcoin.main.dao.FmessageDAO;
import com.huagu.vcoin.main.dao.FsubscriptionlogDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fintrolinfo;
import com.huagu.vcoin.main.model.Fsubscriptionlog;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class VirtualWalletService {
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private FmessageDAO messageDAO;
    @Autowired
    private FsubscriptionlogDAO subscriptionlogDAO;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FintrolinfoDAO introlinfoDAO;

    public Fvirtualwallet findById(int id) {
        return this.virtualwalletDAO.findById(id);
    }

    public void saveObj(Fvirtualwallet obj) {
        this.virtualwalletDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fvirtualwallet obj = this.virtualwalletDAO.findById(id);
        this.virtualwalletDAO.delete(obj);
    }

    public void updateObj(Fvirtualwallet obj) {
        this.virtualwalletDAO.attachDirty(obj);
    }

    public List<Fvirtualwallet> findByProperty(String name, Object value) {
        return this.virtualwalletDAO.findByProperty(name, value);
    }

    public List findByTwoProperty(String propertyName1, Object value1, String propertyName2, Object value2) {
        return this.virtualwalletDAO.findByTwoProperty(propertyName1, value1, propertyName2, value2);
    }

    public List<Fvirtualwallet> findAll() {
        return this.virtualwalletDAO.findAll();
    }

    public List<Fvirtualwallet> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualwallet> all = this.virtualwalletDAO.list(firstResult, maxResults, filter, isFY);
        for (Fvirtualwallet fvirtualwallet : all) {
            fvirtualwallet.getFuser().getFlastLoginTime();
            if (fvirtualwallet.getFvirtualcointype() != null) {
                fvirtualwallet.getFvirtualcointype().getFname();
            }
        }
        return all;
    }

    public List<Map> getTotalQty() {
        return this.virtualwalletDAO.getTotalQty();
    }

    public BigDecimal getTotalQty(int vid) {
        return this.virtualwalletDAO.getTotalQty(vid);
    }

    public void updateSendFrozen(Fvirtualwallet fvirtualwallet, Fsubscriptionlog fsubscriptionlog, Fintrolinfo info) {
        try {
            this.virtualwalletDAO.attachDirty(fvirtualwallet);
            this.introlinfoDAO.save(info);
            this.subscriptionlogDAO.attachDirty(fsubscriptionlog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateCanSellQty(double rate, int fid) {
        this.virtualwalletDAO.updateCanSellQty(rate, fid);
    }

    public Object getAllCount(String tableName, String filter) {
        return this.virtualwalletDAO.getAllCount(tableName, filter);
    }
    
    public Object getAllCounts(String tableName1,String tableName2, String filter) {
        return this.virtualwalletDAO.getAllCounts(tableName1,tableName2, filter);
    }

    public Object getAllCount1(String tableName1,String tableName2,String tableName3, String filter) {
        return this.virtualwalletDAO.getAllCount1(tableName1,tableName2,tableName3,filter);
    }
    
    private Object virtualwalletDAO(String tableName, String filter) {
        // TODO Auto-generated method stub
        return null;
    }
}
