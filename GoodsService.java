package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FgoodsDAO;
import com.huagu.vcoin.main.model.Fgoods;

@Service
public class GoodsService {
    @Autowired
    private FgoodsDAO goodsDAO;
    @Autowired
    private HttpServletRequest request;

    public Fgoods findById(int id) {
        return this.goodsDAO.findById(id);
    }

    public void saveObj(Fgoods obj) {
        this.goodsDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fgoods obj = this.goodsDAO.findById(id);
        this.goodsDAO.delete(obj);
    }

    public void updateObj(Fgoods obj) {
        this.goodsDAO.attachDirty(obj);
    }

    public List<Fgoods> findByProperty(String name, Object value) {
        return this.goodsDAO.findByProperty(name, value);
    }

    public List<Fgoods> findAll() {
        return this.goodsDAO.findAll();
    }

    public List<Fgoods> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.goodsDAO.list(firstResult, maxResults, filter, isFY);
    }
}