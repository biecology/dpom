package com.huagu.vcoin.main.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FshareplanlogDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fshareplanlog;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class SharePlanLogService {
    @Autowired
    private FshareplanlogDAO shareplanlogDAO;
    @Autowired
    private FvirtualwalletDAO fvirtualwalletDAO;

    public Fshareplanlog findById(int id) {
        return this.shareplanlogDAO.findById(id);
    }

    public void saveObj(Fshareplanlog obj) {
        this.shareplanlogDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fshareplanlog obj = this.shareplanlogDAO.findById(id);
        this.shareplanlogDAO.delete(obj);
    }

    public void updateObj(Fshareplanlog obj) {
        this.shareplanlogDAO.attachDirty(obj);
    }

    public void updateObj(Fshareplanlog obj, Fvirtualwallet fvirtualwallet) {
        try {
            this.shareplanlogDAO.save(obj);
            this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Fshareplanlog> findByProperty(String name, Object value) {
        return this.shareplanlogDAO.findByProperty(name, value);
    }

    public List<Fshareplanlog> findAll() {
        return this.shareplanlogDAO.findAll();
    }

    public List<Fshareplanlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fshareplanlog> all = this.shareplanlogDAO.list(firstResult, maxResults, filter, isFY);
        for (Fshareplanlog fshareplanlog : all) {
            if (fshareplanlog.getFuser() != null) {
                fshareplanlog.getFuser().getFnickName();
                if (fshareplanlog.getFshareplan() != null) {
                    fshareplanlog.getFshareplan().getFamount();
                    if (fshareplanlog.getFshareplan().getFvirtualcointype() != null) {
                        fshareplanlog.getFshareplan().getFvirtualcointype().getFname();
                    }
                }
            }
        }
        return all;
    }

}