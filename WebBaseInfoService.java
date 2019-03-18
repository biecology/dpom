package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FwebbaseinfoDAO;
import com.huagu.vcoin.main.model.Fwebbaseinfo;

@Service
public class WebBaseInfoService {
    @Autowired
    private FwebbaseinfoDAO fwebbaseinfoDAO;
    @Autowired
    private HttpServletRequest request;

    public Fwebbaseinfo findById(int id) {
        return this.fwebbaseinfoDAO.findById(id);
    }

    public void saveObj(Fwebbaseinfo obj) {
        this.fwebbaseinfoDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fwebbaseinfo obj = this.fwebbaseinfoDAO.findById(id);
        this.fwebbaseinfoDAO.delete(obj);
    }

    public void updateObj(Fwebbaseinfo obj) {
        this.fwebbaseinfoDAO.attachDirty(obj);
    }

    public List<Fwebbaseinfo> findByProperty(String name, Object value) {
        return this.fwebbaseinfoDAO.findByProperty(name, value);
    }

    public List<Fwebbaseinfo> findAll() {
        return this.fwebbaseinfoDAO.findAll();
    }
}