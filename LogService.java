package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FlogDAO;
import com.huagu.vcoin.main.model.Flog;

@Service
public class LogService {
    @Autowired
    private FlogDAO logDAO;
    @Autowired
    private HttpServletRequest request;

    public Flog findById(int id) {
        return this.logDAO.findById(id);
    }

    public void saveObj(Flog obj) {
        this.logDAO.save(obj);
    }

    public void deleteObj(int id) {
        Flog obj = this.logDAO.findById(id);
        this.logDAO.delete(obj);
    }

    public void updateObj(Flog obj) {
        this.logDAO.attachDirty(obj);
    }

    public List<Flog> findByProperty(String name, Object value) {
        return this.logDAO.findByProperty(name, value);
    }

    public List<Flog> findAll() {
        return this.logDAO.findAll();
    }

    public List<Flog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.logDAO.list(firstResult, maxResults, filter, isFY);
    }
}