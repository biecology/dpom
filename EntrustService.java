package com.huagu.vcoin.main.service.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FentrustDAO;
import com.huagu.vcoin.main.dao.FintrolinfoDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fentrust;
import com.huagu.vcoin.main.model.Fintrolinfo;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class EntrustService {
    @Autowired
    private FentrustDAO entrustDAO;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FintrolinfoDAO fintrolinfoDAO;
    @Autowired
    private FvirtualwalletDAO fvirtualwalletDAO;

    public Fentrust findById(int id) {
        return this.entrustDAO.findById(id);
    }

    public void saveObj(Fentrust obj) {
        this.entrustDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fentrust obj = this.entrustDAO.findById(id);
        this.entrustDAO.delete(obj);
    }

    public void updateObj(Fentrust obj) {
        this.entrustDAO.attachDirty(obj);
    }

    public List<Fentrust> findByProperty(String name, Object value) {
        return this.entrustDAO.findByProperty(name, value);
    }

    public List<Fentrust> findAll() {
        return this.entrustDAO.findAll();
    }

    public List<Fentrust> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fentrust> all = this.entrustDAO.list(firstResult, maxResults, filter, isFY);
        for (Fentrust fentrust : all) {
            if (fentrust.getFuser() != null) {
                fentrust.getFuser().getFnickName();
            }
            if (fentrust.getFtrademapping() != null) {
                fentrust.getFtrademapping().getFvirtualcointypeByFvirtualcointype1().getFname();
                fentrust.getFtrademapping().getFvirtualcointypeByFvirtualcointype2().getFname();
            }
        }
        return all;
    }

    public List<Map> getTotalQty(int fentrustType) {
        return this.entrustDAO.getTotalQty(fentrustType);
    }

    public List<Map> getTotalQty(int fentrustType, boolean isToady) {
        return this.entrustDAO.getTotalQty(fentrustType, isToady);
    }

    public double getTotalSumFees(int fentrustType, boolean isToday) {
        return this.entrustDAO.getTotalSumFees(fentrustType, isToday);
    }

    public void updateObj(Fentrust fentrust, List<Fvirtualwallet> fvirtualwallets, List<Fintrolinfo> fintrolinfos) {
        try {
            this.entrustDAO.attachDirty(fentrust);
            for (Fintrolinfo fintrolinfo : fintrolinfos) {
                this.fintrolinfoDAO.save(fintrolinfo);
            }

            for (Fvirtualwallet fvirtualwallet : fvirtualwallets) {
                this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}