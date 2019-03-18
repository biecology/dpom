package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FfeesDAO;
import com.huagu.vcoin.main.model.Ffees;

@Service
public class FfeesService {
    @Autowired
    private FfeesDAO feesDAO;
    @Autowired
    private HttpServletRequest request;

    public Ffees findById(int id) {
        return this.feesDAO.findById(id);
    }

    public void saveObj(Ffees obj) {
        this.feesDAO.save(obj);
    }

    public void deleteObj(int id) {
        Ffees obj = this.feesDAO.findById(id);
        this.feesDAO.delete(obj);
    }

    public void updateObj(Ffees obj) {
        this.feesDAO.attachDirty(obj);
    }

    public List<Ffees> findByProperty(String name, Object value) {
        return this.feesDAO.findByProperty(name, value);
    }

    public List<Ffees> findAll() {
        return this.feesDAO.findAll();
    }

    public List<Ffees> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.feesDAO.list(firstResult, maxResults, filter, isFY);
    }
}