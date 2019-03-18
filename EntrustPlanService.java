package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FentrustplanDAO;
import com.huagu.vcoin.main.model.Fentrustplan;

@Service
public class EntrustPlanService {
    @Autowired
    private FentrustplanDAO entrustplanDAO;
    @Autowired
    private HttpServletRequest request;

    public Fentrustplan findById(int id) {
        return this.entrustplanDAO.findById(id);
    }

    public void saveObj(Fentrustplan obj) {
        this.entrustplanDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fentrustplan obj = this.entrustplanDAO.findById(id);
        this.entrustplanDAO.delete(obj);
    }

    public void updateObj(Fentrustplan obj) {
        this.entrustplanDAO.attachDirty(obj);
    }

    public List<Fentrustplan> findByProperty(String name, Object value) {
        return this.entrustplanDAO.findByProperty(name, value);
    }

    public List<Fentrustplan> findAll() {
        return this.entrustplanDAO.findAll();
    }

    public List<Fentrustplan> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fentrustplan> list = this.entrustplanDAO.list(firstResult, maxResults, filter, isFY);
        for (Fentrustplan fentrustplan : list) {
            if (fentrustplan.getFvirtualcointype() != null) {
                fentrustplan.getFvirtualcointype().getFname();
            }
            if (fentrustplan.getFuser() != null) {
                fentrustplan.getFuser().getFnickName();
            }
        }
        return list;
    }
}