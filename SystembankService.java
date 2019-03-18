package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.SystembankinfoDAO;
import com.huagu.vcoin.main.model.Systembankinfo;

@Service
public class SystembankService {
    @Autowired
    private SystembankinfoDAO systembankinfoDAO;
    @Autowired
    private HttpServletRequest request;

    public Systembankinfo findById(int id) {
        return this.systembankinfoDAO.findById(id);
    }

    public void saveObj(Systembankinfo obj) {
        this.systembankinfoDAO.save(obj);
    }

    public void deleteObj(int id) {
        Systembankinfo obj = this.systembankinfoDAO.findById(id);
        this.systembankinfoDAO.delete(obj);
    }

    public void updateObj(Systembankinfo obj) {
        this.systembankinfoDAO.attachDirty(obj);
    }

    public List<Systembankinfo> findByProperty(String name, Object value) {
        return this.systembankinfoDAO.findByProperty(name, value);
    }

    public List<Systembankinfo> findAll() {
        return this.systembankinfoDAO.findAll();
    }

    public List<Systembankinfo> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.systembankinfoDAO.list(firstResult, maxResults, filter, isFY);
    }

}