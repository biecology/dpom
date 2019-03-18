package com.huagu.vcoin.main.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FvirtualcointypeDAO;
import com.huagu.vcoin.main.model.Fvirtualcointype;

@Service
public class VirtualCoinService {
    @Autowired
    private FvirtualcointypeDAO virtualcointypeDAO;

    public Fvirtualcointype findById(int id) {
        return this.virtualcointypeDAO.findById(id);
    }

    public void saveObj(Fvirtualcointype obj) {
        this.virtualcointypeDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fvirtualcointype obj = this.virtualcointypeDAO.findById(id);
        this.virtualcointypeDAO.delete(obj);
    }

    public void updateObj(Fvirtualcointype obj) {
        this.virtualcointypeDAO.attachDirty(obj);
    }

    public List<Fvirtualcointype> findByProperty(String name, Object value) {
        return this.virtualcointypeDAO.findByProperty(name, value);
    }

    // selectType 0是=号，1是<>号
    public List<Fvirtualcointype> findAll(int coinType, int selectType) {
        return this.virtualcointypeDAO.findAll(coinType, selectType);
    }

    public List<Fvirtualcointype> findAll() {
        return this.virtualcointypeDAO.findAll();
    }

    public List<Fvirtualcointype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualcointype> all = this.virtualcointypeDAO.list(firstResult, maxResults, filter, isFY);
        return all;
    }

    public boolean updateCoinType(Fvirtualcointype virtualcointype, String password) throws RuntimeException {
        String result = "";
        try {
            result = this.virtualcointypeDAO.startCoinType(virtualcointype.getFid());
            if (!result.equals("SUCCESS")) {
                throw new RuntimeException(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(result);
        }
        this.virtualcointypeDAO.attachDirty(virtualcointype);
        return true;
    }
}