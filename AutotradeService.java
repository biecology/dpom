package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FautotradeDAO;
import com.huagu.vcoin.main.model.Fautotrade;

@Service
public class AutotradeService {
    @Autowired
    private FautotradeDAO autotradeDAO;
    @Autowired
    private HttpServletRequest request;

    public Fautotrade findById(int id) {
        return this.autotradeDAO.findById(id);
    }

    public void saveObj(Fautotrade obj) {
        this.autotradeDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fautotrade obj = this.autotradeDAO.findById(id);
        this.autotradeDAO.delete(obj);
    }

    public void updateObj(Fautotrade obj) {
        this.autotradeDAO.attachDirty(obj);
    }

    public List<Fautotrade> findByProperty(String name, Object value) {
        return this.autotradeDAO.findByProperty(name, value);
    }

    public List<Fautotrade> findAll() {
        return this.autotradeDAO.findAll();
    }

    public List<Fautotrade> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fautotrade> lists = this.autotradeDAO.list(firstResult, maxResults, filter, isFY);
        for (Fautotrade fautotrade : lists) {
            fautotrade.getFtrademapping().getFvirtualcointypeByFvirtualcointype1().getFname();
            fautotrade.getFtrademapping().getFvirtualcointypeByFvirtualcointype2().getFname();
        }
        return lists;
    }
}