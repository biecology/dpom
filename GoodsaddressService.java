package com.huagu.vcoin.main.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FgoodsaddressDAO;
import com.huagu.vcoin.main.model.Fgoodsaddress;

@Service
public class GoodsaddressService {
    @Autowired
    private FgoodsaddressDAO goodsaddressDAO;

    public Fgoodsaddress findById(int id) {
        return this.goodsaddressDAO.findById(id);
    }

    public void saveObj(Fgoodsaddress obj) {
        this.goodsaddressDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fgoodsaddress obj = this.goodsaddressDAO.findById(id);
        this.goodsaddressDAO.delete(obj);
    }

    public void updateObj(Fgoodsaddress obj) {
        this.goodsaddressDAO.attachDirty(obj);
    }

    public List<Fgoodsaddress> findByProperty(String name, Object value) {
        return this.goodsaddressDAO.findByProperty(name, value);
    }

    public List<Fgoodsaddress> findAll() {
        return this.goodsaddressDAO.findAll();
    }

    public List<Fgoodsaddress> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.goodsaddressDAO.list(firstResult, maxResults, filter, isFY);
    }
}