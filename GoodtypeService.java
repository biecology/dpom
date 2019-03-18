package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FgoodtypeDAO;
import com.huagu.vcoin.main.model.Fgoodtype;

@Service
public class GoodtypeService {
    @Autowired
    private FgoodtypeDAO goodtypeDAO;
    @Autowired
    private HttpServletRequest request;

    public Fgoodtype findById(int id) {
        return this.goodtypeDAO.findById(id);
    }

    public void saveObj(Fgoodtype obj) {
        this.goodtypeDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fgoodtype obj = this.goodtypeDAO.findById(id);
        this.goodtypeDAO.delete(obj);
    }

    public void updateObj(Fgoodtype obj) {
        this.goodtypeDAO.attachDirty(obj);
    }

    public List<Fgoodtype> findByProperty(String name, Object value) {
        return this.goodtypeDAO.findByProperty(name, value);
    }

    public List<Fgoodtype> findAll() {
        return this.goodtypeDAO.findAll();
    }

    public List<Fgoodtype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.goodtypeDAO.list(firstResult, maxResults, filter, isFY);
    }
}