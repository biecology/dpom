package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FbalanceDAO;
import com.huagu.vcoin.main.model.Fbalance;

@Service
public class BalanceService {
    @Autowired
    private FbalanceDAO balanceDAO;
    @Autowired
    private HttpServletRequest request;

    public Fbalance findById(int id) {
        return this.balanceDAO.findById(id);
    }

    public void saveObj(Fbalance obj) {
        this.balanceDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fbalance obj = this.balanceDAO.findById(id);
        this.balanceDAO.delete(obj);
    }

    public void updateObj(Fbalance obj) {
        this.balanceDAO.attachDirty(obj);
    }

    public List<Fbalance> findByProperty(String name, Object value) {
        return this.balanceDAO.findByProperty(name, value);
    }

    public List<Fbalance> findAll() {
        return this.balanceDAO.findAll();
    }

    public List<Fbalance> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.balanceDAO.list(firstResult, maxResults, filter, isFY);
    }
}