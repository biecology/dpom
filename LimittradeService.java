package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FlimittradeDAO;
import com.huagu.vcoin.main.model.Flimittrade;

@Service
public class LimittradeService {
    @Autowired
    private FlimittradeDAO limittradeDAO;
    @Autowired
    private HttpServletRequest request;

    public Flimittrade findById(int id) {
        Flimittrade flimittrade = this.limittradeDAO.findById(id);
        if (flimittrade.getFtrademapping() != null) {
            flimittrade.getFtrademapping().getFvirtualcointypeByFvirtualcointype1().getFname();
            flimittrade.getFtrademapping().getFvirtualcointypeByFvirtualcointype2().getFname();
        }
        return flimittrade;
    }

    public void saveObj(Flimittrade obj) {
        this.limittradeDAO.save(obj);
    }

    public void deleteObj(int id) {
        Flimittrade obj = this.limittradeDAO.findById(id);
        this.limittradeDAO.delete(obj);
    }

    public void updateObj(Flimittrade obj) {
        this.limittradeDAO.attachDirty(obj);
    }

    public List<Flimittrade> findByProperty(String name, Object value) {
        return this.limittradeDAO.findByProperty(name, value);
    }

    public List<Flimittrade> findAll() {
        return this.limittradeDAO.findAll();
    }

    public List<Flimittrade> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Flimittrade> lists = this.limittradeDAO.list(firstResult, maxResults, filter, isFY);
        for (Flimittrade flimittrade : lists) {
            if (flimittrade.getFtrademapping() != null) {
                flimittrade.getFtrademapping().getFvirtualcointypeByFvirtualcointype1().getFname();
                flimittrade.getFtrademapping().getFvirtualcointypeByFvirtualcointype2().getFname();
            }
        }
        return lists;
    }
}