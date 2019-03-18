package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FpoolDAO;
import com.huagu.vcoin.main.model.Fpool;
import com.huagu.vcoin.main.model.Fvirtualcointype;

@Service
public class PoolService {
    @Autowired
    private FpoolDAO poolDAO;
    @Autowired
    private HttpServletRequest request;

    public Fpool findById(int id) {
        return this.poolDAO.findById(id);
    }

    public void saveObj(Fpool obj) {
        this.poolDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fpool obj = this.poolDAO.findById(id);
        this.poolDAO.delete(obj);
    }

    public void updateObj(Fpool obj) {
        this.poolDAO.attachDirty(obj);
    }

    public List<Fpool> findByProperty(String name, Object value) {
        return this.poolDAO.findByProperty(name, value);
    }

    public List<Fpool> findAll() {
        return this.poolDAO.findAll();
    }

    public List list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.poolDAO.list(firstResult, maxResults, filter, isFY);
    }

    public Fpool getOneFpool(Fvirtualcointype fvirtualcointype) {
        return this.poolDAO.getOneFpool(fvirtualcointype);
    }

}